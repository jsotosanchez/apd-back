package me.apd.turno;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    private final TurnoService turnoService;

    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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

    @GetMapping("especialidades/{especialidadId}/medicos/{medicoId}")
    public Map<String, List<TurnoDisponibleView>> buscarTurnosDisponibles(@PathVariable Long especialidadId, @PathVariable Long medicoId) {
        List<TurnoDisponibleView> listaDisponibles = turnoService
                .buscarDisponiblesPorEspecialidadYMedico(especialidadId, medicoId);
        return listaDisponibles.stream()
                .collect(Collectors.groupingBy(this::getDate));
    }

    private String getDate(TurnoDisponibleView t) {

        return sdf.format(Date.from(t.getHorario()));
    }

    @GetMapping("especialidades/{especialidadId}")
    public Map<String, List<TurnoDisponibleView>> buscarTurnosDisponibles(@PathVariable Long especialidadId) {
        List<TurnoDisponibleView> listaDisponibles = turnoService
                .buscarDisponiblesPorEspecialidad(especialidadId);
        return listaDisponibles.stream()
                .collect(Collectors.groupingBy(this::getDate));
    }

    @GetMapping("paciente/{id}")
    public List<TurnoPacienteView> buscarPorPaciente(@PathVariable Long id) {
        return turnoService.buscarPorPaciente(id);
    }

    @GetMapping("medico/{id}/dia/{dia}")
    public List<TurnoMedicoView> buscarPorMedicoYDia(@PathVariable Long id, @PathVariable String dia) {
        return turnoService.buscarPorMedicoEntreFechas(id, dia);
    }
}
