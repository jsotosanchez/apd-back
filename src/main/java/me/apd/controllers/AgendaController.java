package me.apd.controllers;

import me.apd.controllers.dto.*;
import me.apd.entities.Especialidad;
import me.apd.entities.Turno;
import me.apd.entities.Usuario;
import me.apd.services.AgendaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@Validated
@RequestMapping("/agenda")
public class AgendaController {
    private final AgendaService agendaService;

    public AgendaController(AgendaService agenda) {
        this.agendaService = agenda;
    }

    @GetMapping("/test")
    public String test() {
        return "hola!";
    }

    @PostMapping("")
    public void generarAgenda(@RequestBody AgendaView body) {

        LocalDateTime fechaFin = LocalDateTime.parse(body.getFechaFin() + "T" + body.getHoraInicio());
        LocalDateTime fechaTurno = LocalDateTime.parse(body.getFechaInicio() + "T" + body.getHoraInicio());
        LocalTime horaFin = LocalTime.parse(body.getHoraFin());
        List<Turno> turnosCreados = new ArrayList<>();
        Long minutosAgregados = Long.parseLong("0");
        while (fechaTurno.isBefore(fechaFin)) {
            Turno turnoNuevo = Turno.builder()
                    .medico(Usuario.builder()
                            .id(Long.parseLong(body.getMedicoId()))
                            .build())
                    .especialidad(Especialidad.builder()
                            .id(Long.parseLong(body.getEspecialidadId()))
                            .build())
                    .horario(fechaTurno)
                    .build();
            turnosCreados.add(turnoNuevo);
            fechaTurno.plusMinutes(Long.parseLong(body.getDuracion()));
            minutosAgregados += Long.parseLong(body.getDuracion());
            if (fechaTurno.getHour() > horaFin.getHour() && fechaTurno.getMinute() > horaFin.getMinute()) {
                fechaTurno.plusDays(1);
                fechaTurno.minusMinutes(minutosAgregados);
            }
        }
        agendaService.guardarTodos(turnosCreados);
    }

    @PatchMapping("{id}")
    public Long modificarTurno(@RequestBody TurnoNuevoView turno) {

        Turno modelo = Turno.builder()
                .id(Long.parseLong(turno.getId()))
                .medico(Usuario.builder().id(Long.parseLong(turno.getMedicoId())).build())
                .horario(LocalDateTime.parse(turno.getHorario()))
                .confirmado(turno.getConfirmado())
                .especialidad(Especialidad.builder().id(Long.parseLong(turno.getEspecialidadId())).build())
                .build();
        Turno nuevoTurno = agendaService.guardarAgenda(modelo);

        return nuevoTurno.getId();
    }

    @DeleteMapping("{id}")
    public Long eliminarTurno(@PathVariable String id) {
        agendaService.eliminarPorId(Long.parseLong(id));
        return Long.parseLong(id);
    }

    @PatchMapping("{id}/reservar")
    public Long reservarTurno(@PathVariable String id, @RequestBody PacienteView paciente) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setPaciente(Usuario.builder().id(paciente.getId()).build());
        Turno reserva = agendaService.guardarAgenda(turno);

        return reserva.getId();
    }

    @PatchMapping("{id}/cancelar")
    public Long cancelarTurno(@PathVariable String id, @RequestBody UsuarioView usuario) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setPaciente(null);

        if (usuario.getMatricula().isEmpty()) {
            //buscar otro en cola de espera;
            //turno.setPaciente(resultadoDeBusqueda)
            Turno cancelado = agendaService.guardarAgenda(turno);
        } else {
            agendaService.eliminarPorId(turno.getId());
        }

        return turno.getId();
    }

    @PatchMapping("{id}/confirmar")
    public Long confirmarTurno(@PathVariable String id) throws IllegalAccessException {
        Turno turno = agendaService.buscarPorId(Long.parseLong(id)).orElseThrow(
                IllegalAccessException::new
        );
        turno.setConfirmado(true);
        Turno confirmado = agendaService.guardarAgenda(turno);

        return confirmado.getId();
    }

    @GetMapping("/{especialidadId}/{medicoId}")
    public /*List<TurnoView>*/void buscarTurnosDisponibles(@PathVariable String especialidadId, @PathVariable String medicoId) {

//      return  agendaService.buscarPorEspecialidadYMedico(especialidadId, medicoId)

    }

    @GetMapping("/{id}")
    public /*List<TurnoView>*/void buscarMisTurnos(@PathVariable String id) {

//        return agendaService.buscarPorPacienteId(Long.parseLong(id));
    }

    @PostMapping("/cola")
    public int agregarAColaDeEspera(@RequestBody ColaEsperaView colaEspera) {

//        return colaEsperaService.save(colaEspera);
        return 1;
    }
}
