package me.apd.controllers;

import me.apd.controllers.dto.TurnoNuevoView;
import me.apd.controllers.dto.TurnoView;
import me.apd.entities.Especialidad;
import me.apd.entities.Medico;
import me.apd.entities.Turno;
import me.apd.services.Agenda;
import me.apd.services.EspecialidadService;
import me.apd.services.Usuario;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/agenda")
public class AgendaController {
    private final Agenda agendaService;
    private final Usuario usuarioService;
    private final EspecialidadService especialidadService;

    public AgendaController(Agenda agenda, Usuario usuario, EspecialidadService especialidad) {
        this.agendaService = agenda;
        this.usuarioService = usuario;
        this.especialidadService = especialidad;
    }

    @PostMapping("/")
    public TurnoView nuevoTurno(@RequestBody TurnoNuevoView turno) {

        Medico medico = usuarioService.buscarMedicoPorId(turno.getMedicoId()).orElseThrow(
                IllegalArgumentException::new
        );
        Especialidad especialidad = especialidadService.buscarPorId(turno.getEspecialidadId()).orElseThrow(
                IllegalArgumentException::new
        );

        Turno modelo = Turno.builder()
                .id(turno.getId())
                .medico(medico)
                .horario(turno.getHorario())
                .confirmado(false)
                .especialidad(especialidad).build();

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
