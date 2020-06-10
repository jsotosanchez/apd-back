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

//    @PatchMapping("{id}/cancelar")
//    public Long cancelarTurno(@PathVariable String id, @RequestBody UsuarioView usuario) throws IllegalAccessException {
//        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
//                IllegalAccessException::new
//        );
//        turno.setPaciente(null);
//
//        if (usuario.getMatricula().isEmpty()) {
//            ColaDeEspera colaDeEspera = colaDeEsperaService.buscarPorEspecialidad(turno.getEspecialidad());
//            turno.setPaciente(colaDeEspera.getPaciente());
//            agendaService.guardarTurno(turno);
//            //TO DO: enviar notificacion
//        } else {
//            agendaService.eliminarPorId(turno.getId());
//        }
//
//        return turno.getId();
//    }

    @PatchMapping("{id}/confirmar")
    public Long confirmarTurno(@PathVariable Long id) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(id).orElseThrow(
                IllegalAccessException::new
        );
        turno.setConfirmado(true);
        Turno confirmado = agendaService.guardarTurno(turno);

        return confirmado.getId();
    }

    @GetMapping("/{especialidadId}/{medicoId}")
    public List<Horario> buscarTurnosDisponibles(@PathVariable Long especialidadId, @PathVariable Long medicoId) {
        return agendaService.buscarDisponiblesPorEspecialidadYMedico(especialidadId, medicoId);
    }

    @GetMapping("/{id}")
    public List<Turno> buscarMisTurnos(@PathVariable Long id) {
        return agendaService.buscarPorPaciente(id);
    }

}
