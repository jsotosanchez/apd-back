package me.apd.turno;

import java.util.List;
import java.util.Optional;

public interface TurnoService {
    Turno guardarTurno(Turno turno);

    void eliminarPorId(Long id);

    Optional<Turno> buscarPorId(long id);

    Iterable<Turno> guardarTodos(List<Turno> turnos);

    List<TurnoPacienteView> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId);

    List<Turno> buscarPorPaciente(long parseLong);

    Long reservarTurno(Long usuarioId, Long turnoId);

    Long cancelarTurno(Long turnoId);

    List<TurnoPacienteView> buscarDisponiblesPorEspecialidad(Long especialidadId);

    List<Turno> buscarPorMedico(Long id);
}
