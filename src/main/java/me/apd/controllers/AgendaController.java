package me.apd.controllers;

import me.apd.controllers.dto.PacienteView;
import me.apd.controllers.dto.TurnoNuevoView;
import me.apd.entities.Especialidad;
import me.apd.entities.Medico;
import me.apd.entities.Paciente;
import me.apd.entities.Turno;
import me.apd.services.Agenda;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/agenda")
public class AgendaController {
    private final Agenda agendaService;

    public AgendaController(Agenda agenda) {
        this.agendaService = agenda;
    }

    @PostMapping("/")
    public Long nuevoTurno(@RequestBody TurnoNuevoView turno) {

        Turno modelo = Turno.builder()
                .id(turno.getId())
                .medico(Medico.builder().id(turno.getMedicoId()).build())
                .horario(turno.getHorario())
                .confirmado(false)
                .especialidad(Especialidad.builder().id(turno.getEspecialidadId()).build()).build();

        Turno nuevoTurno = agendaService.guardarAgenda(modelo);

        return nuevoTurno.getId();
    }

    @PutMapping("{id}")
    public Long modificarTurno(@RequestBody TurnoNuevoView turno) {

        Turno modelo = Turno.builder()
                .id(turno.getId())
                .medico(Medico.builder().id(turno.getMedicoId()).build())
                .horario(turno.getHorario())
                .confirmado(false)
                .especialidad(Especialidad.builder().id(turno.getEspecialidadId()).build()).build();
        Turno nuevoTurno = agendaService.guardarAgenda(modelo);

        return nuevoTurno.getId();
    }

    @DeleteMapping("{id}")
    public Long eliminarTurno(@PathVariable String id) {
        agendaService.eliminarPorId(Long.parseLong(id));
        return Long.parseLong(id);
    }

    @PutMapping("{id}/reservar")
    public Long reservarTurno(@PathVariable String id, @RequestBody PacienteView paciente) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setPaciente(Paciente.builder().id(paciente.getId()).build());
        Turno reserva = agendaService.guardarAgenda(turno);

        return reserva.getId();
    }

    @PutMapping("{id}/cancelar")
    public Long cancelarTurno(@PathVariable String id, @RequestBody PacienteView paciente) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setPaciente(null);
        Turno cancelado = agendaService.guardarAgenda(turno);

        return cancelado.getId();
    }

    @PutMapping("{id}/confirmar")
    public Long confirmarTurno(@PathVariable String id, @RequestBody PacienteView paciente) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setConfirmado(true);
        Turno confirmado = agendaService.guardarAgenda(turno);

        return confirmado.getId();
    }
}
