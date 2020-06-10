package me.apd.agenda;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    Turno guardarTurno(Turno turno);

    void eliminarPorId(Long id);

    Optional<Turno> buscarPorId(long id);

    Iterable<Turno> guardarTodos(List<Turno> turnos);

    List<Turno> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId);

    List<Turno> buscarPorPaciente(long parseLong);

    Long reservarTurno(Long usuarioId, Long turnoId);

    Long cancelarTurno(Long turnoId);

    List<Turno> buscarDisponiblesPorEspecialidad(Long especialidadId);

    List<Turno> buscarPorMedico(Long id);
}
