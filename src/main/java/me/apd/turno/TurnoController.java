package me.apd.turno;

import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.*;
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

    @DeleteMapping("/medico/{id}/dia/{dia}")
    public void eliminarTurnosDelDia(@PathVariable Long id, @PathVariable String dia) {
        turnoService.eliminarPorDia(id, dia);
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
