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

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
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
    public ResponseView generarAgenda(@RequestBody AgendaBody body) {

        Usuario medico = usuarioService.buscarPorId(body.medicoId).orElseThrow(UsuarioNotFoundException::new);
        Especialidad especialidad = especialidadService.buscarPorId(body.especialidadId)
                .orElseThrow(EspecialidadNotFoundException::new);
        Instant fechaFin = Instant.from(formatter.parse(body.getFechaFin() + " " + body.getHoraFin()));
        Instant fechaTurno = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraInicio()));
        Instant horaLimite = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraFin()));
        Instant horaInicio = Instant.from(formatter.parse(body.getFechaInicio() + " " + body.getHoraInicio()));

        List<Turno> turnosCreados = new ArrayList<>();

        while (fechaTurno.isBefore(fechaFin)) {
            while (fechaTurno.isBefore(horaLimite)) {
                Turno turnoNuevo = Turno.builder()
                        .medico(medico)
                        .especialidad(especialidad)
                        .horario(Timestamp.from(fechaTurno))
                        .confirmado(false)
                        .build();
                turnosCreados.add(turnoNuevo);
                fechaTurno = fechaTurno.plus(1, ChronoUnit.HOURS);
            }
            horaLimite = horaLimite.plus(1, ChronoUnit.DAYS);
            horaInicio = horaInicio.plus(1, ChronoUnit.DAYS);
            fechaTurno = horaInicio;
        }
        turnoService.guardarTodos(turnosCreados);
        return new ResponseView("Se ha cargado tu agenda exitosamente");
    }

    @DeleteMapping("{id}")
    public Long eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarPorId(id);
        return id;
    }

    @DeleteMapping("/medico/{id}/dia/{dia}")
    public void eliminarTurnosDelDia(@PathVariable Long id, @PathVariable String dia) {
        turnoService.eliminarPorDia(id, dia);
    }

    @PatchMapping("{id}/reservar")
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
    public Long cancelarTurno(@PathVariable(name = "id") Long turnoId) {
        notificationService.send(to, "Se cancelo tu turno subject", "Se cancelo tu turno body");
        return turnoService.cancelarTurno(turnoId);
    }

    @PatchMapping("{id}/confirmar")
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
    public List<TurnoPacienteView> buscarPorPaciente(@PathVariable Long id) {
        return turnoService.buscarPorPaciente(id);
    }

    @GetMapping("medico/{id}/dias")
    public Set<String> buscarDiasPorMedico(@PathVariable Long id) {
        return turnoService.buscarPorMedico(id).stream().map(this::getDate).collect(Collectors.toSet());
    }

    @GetMapping("medico/{id}/dia/{dia}")
    public List<TurnoMedicoView> buscarPorMedicoyDia(@PathVariable Long id, @PathVariable String dia) {
        return turnoService.buscarPorMedicoEntreFechas(id, dia);
    }
}
