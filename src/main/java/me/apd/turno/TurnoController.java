package me.apd.turno;

import org.springframework.web.bind.annotation.*;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    public TurnoController(TurnoService agenda) {
        this.turnoService = agenda;
    }

//    @PostMapping("")
//    public void generarAgenda(@RequestBody AgendaView body) {
//
//        LocalDateTime fechaFin = LocalDateTime.parse(body.getFechaFin() + "T" + body.getHoraInicio());
//        LocalDateTime fechaInicial = LocalDateTime.parse(body.getFechaInicio() + "T" + body.getHoraInicio());
//        LocalDateTime fechaTurno = LocalDateTime.parse(body.getFechaInicio() + "T" + body.getHoraInicio());
//        LocalDateTime horaFin = LocalDateTime.parse(body.getHoraFin());
//        List<Turno> turnosCreados = new ArrayList<>();
//        while (fechaTurno.isBefore(fechaFin)) {
//            Turno turnoNuevo = Turno.builder()
//                    .medico(Usuario.builder()
//                            .id(Long.parseLong(body.getMedicoId()))
//                            .build())
//                    .especialidad(Especialidad.builder()
//                            .id(Long.parseLong(body.getEspecialidadId()))
//                            .build())
//                    .horario(fechaTurno)
//                    .build();
//            turnosCreados.add(turnoNuevo);
//
//            fechaTurno.plusMinutes(30);
//            if (fechaTurno.getHour() > horaFin.getHour() && fechaTurno.getMinute() > horaFin.getMinute()) {
//                fechaTurno = fechaInicial;
//                fechaTurno.plusDays(1);
//            }
//        }
//        turnoService.guardarTodos(turnosCreados);
//    }

    @DeleteMapping("{id}")
    public Long eliminarTurno(@PathVariable Long id) {
        turnoService.eliminarPorId(id);
        return id;
    }

    @PatchMapping("{id}/reservar")
    public Long reservarTurno(@PathVariable(name = "id") Long turnoId, @RequestBody UsuarioBody paciente) {
        return turnoService.reservarTurno(paciente.id, turnoId);
    }

    @PatchMapping("{id}/cancelar")
    public Long cancelarTurno(@PathVariable(name = "id") Long turnoId) {
        return turnoService.cancelarTurno(turnoId);
    }

    @PatchMapping("{id}/confirmar")
    public Long confirmarTurno(@PathVariable Long id) {
        return turnoService.confirmarTurno(id);
    }

    @GetMapping("especialidad/{especialidadId}/medico/{medicoId}")
    public Map<Instant, List<TurnoDisponibleView>> buscarTurnosDisponibles(@PathVariable Long especialidadId, @PathVariable Long medicoId) {
        List<TurnoDisponibleView> listaDisponibles = turnoService
                .buscarDisponiblesPorEspecialidadYMedico(especialidadId, medicoId);
        Map<Instant, List<TurnoDisponibleView>> map = listaDisponibles.stream()
                .collect(Collectors.groupingBy(this::getDate));
        return map;
    }

    private Instant getDate(TurnoDisponibleView t) {

        return t.getHorario().truncatedTo(ChronoUnit.DAYS);
    }

    @GetMapping("especialidad/{especialidadId}")
    public List<TurnoDisponibleView> buscarTurnosDisponibles(@PathVariable Long especialidadId) {
        return turnoService.buscarDisponiblesPorEspecialidad(especialidadId);
    }

    @GetMapping("paciente/{id}")
    public List<TurnoPacienteView> buscarPorPaciente(@PathVariable Long id) {
        return turnoService.buscarPorPaciente(id);
    }

    @GetMapping("medico/{id}")
    public List<TurnoMedicoView> buscarPorMedico(@PathVariable Long id) {
        return turnoService.buscarPorMedico(id);
    }
}
