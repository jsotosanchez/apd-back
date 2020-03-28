package me.apd.controllers;

import me.apd.controllers.dto.TurnoNuevoView;
import me.apd.controllers.dto.TurnoView;
import me.apd.entities.Especialidad;
import me.apd.entities.Medico;
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
    public TurnoView nuevoTurno(@RequestBody TurnoNuevoView turno) {

        Turno modelo = Turno.builder()
                .id(turno.getId())
                .medico(Medico.builder().id(turno.getMedicoId()).build())
                .horario(turno.getHorario())
                .confirmado(false)
                .especialidad(Especialidad.builder().id(turno.getEspecialidadId()).build()).build();

        Turno nuevoTurno = agendaService.cargarAgenda(modelo);

        return TurnoView.builder().id(nuevoTurno.getId()).build();
    }

    @PutMapping("{id}")
    public TurnoView modificarTurno(@PathVariable String id, @RequestBody TurnoView turno) {
        Turno modelo = Turno.builder().id(Long.parseLong(id)).build();
        Turno nuevoTurno = agendaService.modificarAgenda(modelo);

        return TurnoView.builder().id(nuevoTurno.getId()).build();
    }

    @DeleteMapping("{id}")
    public void eliminarTurno(@PathVariable String id) {
        agendaService.eliminarPorId(Long.parseLong(id));
    }

//    @PutMapping("{id}/reservar")
//    public TurnoView reservarTurno(@PathVariable String id, @RequestBody PacienteView paciente){
//        Optional<Turno> turno = agenda.buscarPorId(Long.parseLong(id));
//        if(paciente.getPagoAlDia()){
//
//        }
////        return que no tiene permiso
////        return ResponseEntity<>
//    }
}
