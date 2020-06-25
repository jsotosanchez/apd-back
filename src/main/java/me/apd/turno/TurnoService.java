package me.apd.turno;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Turno guardarTurno(Turno turno);

    void eliminarPorId(Long id);

    Optional<Turno> buscarPorId(long id);

    Iterable<Turno> guardarTodos(List<Turno> turnos);

    List<TurnoDisponibleView> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId);

    List<TurnoPacienteView> buscarPorPaciente(long parseLong);

    Long reservarTurno(Long usuarioId, Long turnoId);

    Long cancelarTurno(Long turnoId);

    List<TurnoDisponibleView> buscarDisponiblesPorEspecialidad(Long especialidadId);

    List<TurnoMedicoView> buscarPorMedicoEntreFechas(Long id, String fecha);

    Long confirmarTurno(Long id);

    List<DiaMedicoView> buscarPorMedico(Long id);

    void eliminarPorDia(Long id, String dia);

    List<TurnoPacienteView> buscarPorPacienteYDia(Long id, Instant horario);
}
