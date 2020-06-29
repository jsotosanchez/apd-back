package me.apd.turno;

import me.apd.especialidad.Especialidad;
import me.apd.especialidad.EspecialidadNotFoundException;
import me.apd.especialidad.EspecialidadService;
import me.apd.push.NotificacionService;
import me.apd.usuario.Usuario;
import me.apd.usuario.UsuarioNotFoundException;
import me.apd.usuario.UsuarioService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.RolesAllowed;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;
    final DateTimeFormatter formatter = DateTimeFormatter
            .ofPattern("yyyy-MM-dd HH:mm")
            .withZone(ZoneId.systemDefault());
    private final UsuarioService usuarioService;
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    private final EspecialidadService especialidadService;
    private final NotificacionService notificationService;
    @Value("${twilio.to}")
    private String to;

    public TurnoController(TurnoService agenda, UsuarioService usuarioService, EspecialidadService especialidadService, NotificacionService notificationService) {
        this.turnoService = agenda;
        this.usuarioService = usuarioService;
        this.especialidadService = especialidadService;
        this.notificationService = notificationService;
    }

    @PostMapping("")
    @RolesAllowed("MEDICO")
    public ResponseView generarAgenda(@RequestBody AgendaBody body) {

        Usuario medico = usuarioService.buscarPorId(body.medicoId).orElseThrow(UsuarioNotFoundException::new);
        Especialidad especialidad = especialidadService.buscarPorId(body.especialidadId)
                .orElseThrow(EspecialidadNotFoundException::new);
        Instant fechaFin = Instant.from(formatter.parse(body.getFechaFin() + " " + body.getHoraFin()));
        Instant fechaTurno = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraInicio()));
        Instant horaLimite = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraFin()));
        Instant horaInicio = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraInicio()));

        List<Turno> turnosCreados = new ArrayList<>();

        turnoService
                .cargarHorarios(medico, especialidad, fechaFin, fechaTurno, horaLimite, horaInicio, turnosCreados, this);
        return new ResponseView("Se ha cargado tu agenda exitosamente");
    }

    @DeleteMapping("{id}")
    @RolesAllowed("MEDICO")
    public Long eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarPorId(id);
        return id;
    }

    @DeleteMapping("/medico/{id}/dia/{dia}")
    @RolesAllowed("MEDICO")
    public void eliminarTurnosDelDia(@PathVariable Long id, @PathVariable String dia) {
        turnoService.eliminarPorDia(id, dia);
    }

    @PatchMapping("{id}/reservar")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public Long reservarTurno(@PathVariable(name = "id") Long turnoId, @RequestBody UsuarioBody paciente) {
        Turno turno = turnoService.buscarPorId(turnoId).orElseThrow(TurnoNotFoundException::new);
        List<TurnoPacienteView> turnoPacienteViews = turnoService
                .buscarPorPacienteYDia(paciente.id, turno.getHorario().toInstant());

        turnoPacienteViews.forEach(t -> {
            if (t.getEspecialidad().equals(turno.getEspecialidad().getDescripcion())) {
                throw new EspecialidadRepetidaEnUnDiaException();
            }
        });

        return turnoService.reservarTurno(paciente.id, turnoId);
    }

    @PatchMapping("{id}/cancelar")
    @RolesAllowed({"MEDICO", "PACIENTE", "ADMIN"})
    public Long cancelarTurno(@PathVariable(name = "id") Long turnoId) {
        Turno turno = turnoService.buscarPorId(turnoId).orElseThrow(TurnoNotFoundException::new);

//        buscar id del que hace el request
        if (turno.getPaciente().getId().equals(1L))
            return turnoService.cancelarTurno(turnoId);
//        si el que manda el request no es el paciente
        notificationService
                .send(to, "Healthy - Se ha cancelado tu turno", "Estimado, te escribimos de Healthy para avisarte que se ha cancelado tu turno. Nos pondremos en contacto para darte uno nuevo");
        return turnoService.cancelarTurno(turnoId);
    }

    @PatchMapping("{id}/confirmar")
    @RolesAllowed("PACIENTE")
    public Long confirmarTurno(@PathVariable Long id) {
        return turnoService.confirmarTurno(id);
    }

    private String getDate(TurnoDisponibleView t) {
        return sdf.format(Date.from(t.getHorario()));
    }

    private String getDate(DiaMedicoView t) {
        return sdf.format(Date.from(t.getHorario()));
    }

    @GetMapping("especialidades/{especialidadId}")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public Map<Long, Map<String, List<TurnoDisponibleView>>> buscarTurnosDisponibles(@PathVariable Long especialidadId) {
        List<TurnoDisponibleView> listaDisponibles = turnoService
                .buscarDisponiblesPorEspecialidad(especialidadId);
        Map<Long, List<TurnoDisponibleView>> agrupadosMedico = listaDisponibles.stream()
                .collect(Collectors.groupingBy(TurnoDisponibleView::getMedicoId));
        return agrupadosMedico
                .entrySet().stream().map(e -> {
                    Map<String, List<TurnoDisponibleView>> fechas = e.getValue().stream()
                            .collect(Collectors.groupingBy(this::getDate));
                    return new AbstractMap.SimpleImmutableEntry<>(e.getKey(), fechas);
                }).collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue, (a, b) -> a, LinkedHashMap::new));
    }

    @GetMapping("paciente/{id}")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public List<TurnoPacienteView> buscarPorPaciente(@PathVariable Long id) {
        return turnoService.buscarPorPaciente(id);
    }

    @GetMapping("medico/{id}/dias")
    @RolesAllowed("MEDICO")
    public Set<String> buscarDiasPorMedico(@PathVariable Long id) {
        return turnoService.buscarPorMedico(id).stream().map(this::getDate).collect(Collectors.toSet());
    }

    @GetMapping("medico/{id}/dia/{dia}")
    @RolesAllowed("MEDICO")
    public List<TurnoMedicoView> buscarPorMedicoyDia(@PathVariable Long id, @PathVariable String dia) {
        return turnoService.buscarPorMedicoEntreFechas(id, dia);
    }
}
