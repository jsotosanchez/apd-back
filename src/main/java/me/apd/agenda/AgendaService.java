package me.apd.agenda;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    Turno guardarTurno(Turno turno);

    void eliminarPorId(Long id);

    Optional<Turno> buscarPorId(long id);

    Iterable<Turno> guardarTodos(List<Turno> turnos);

    List<Horario> buscarDisponiblesPorEspecialidadYMedico(String especialidadId, String medicoId);

    List<Turno> buscarPorPaciente(long parseLong);
}
