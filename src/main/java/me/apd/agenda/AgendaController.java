package me.apd.agenda;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agenda) {
        this.agendaService = agenda;
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
//        agendaService.guardarTodos(turnosCreados);
//    }

    @DeleteMapping("{id}")
    public Long eliminarTurno(@PathVariable Long id) {
        agendaService.eliminarPorId(id);
        return id;
    }

    @PatchMapping("{id}/reservar")
    public Long reservarTurno(@PathVariable(name = "id") Long turnoId, @RequestBody UsuarioBody paciente) {
        return agendaService.reservarTurno(paciente.id, turnoId);
    }

    @PatchMapping("{id}/cancelar")
    public Long cancelarTurno(@PathVariable(name = "id") Long turnoId) {
        return agendaService.cancelarTurno(turnoId);
    }

    @PatchMapping("{id}/confirmar")
    public Long confirmarTurno(@PathVariable Long id) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(id).orElseThrow(
                IllegalAccessException::new
        );
        turno.setConfirmado(true);
        Turno confirmado = agendaService.guardarTurno(turno);

        return confirmado.getId();
    }

    @GetMapping("turnos/especialidad/{especialidadId}/medico/{medicoId}")
    public List<Turno> buscarTurnosDisponibles(@PathVariable Long especialidadId, @PathVariable Long medicoId) {
        if (medicoId == null) {
            return agendaService.buscarDisponiblesPorEspecialidad(especialidadId);
        }
        return agendaService.buscarDisponiblesPorEspecialidadYMedico(especialidadId, medicoId);
    }

    @GetMapping("turnos/especialidad/{especialidadId}")
    public List<Turno> buscarTurnosDisponibles(@PathVariable Long especialidadId) {
        return agendaService.buscarDisponiblesPorEspecialidad(especialidadId);
    }

    @GetMapping("turnos/paciente/{id}")
    public List<Turno> buscarPorPaciente(@PathVariable Long id) {
        return agendaService.buscarPorPaciente(id);
    }

    @GetMapping("turnos/medico/{id}")
    public List<Turno> buscarPorMedico(@PathVariable Long id) {
        return agendaService.buscarPorMedico(id);
    }
}
