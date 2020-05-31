package me.apd.services;

import me.apd.entities.Turno;

import java.util.List;
import java.util.Optional;

public interface AgendaService {
    Turno guardarAgenda(Turno turno);

    void eliminarPorId(Long id);

    Optional<Turno> buscarPorId(long id);

    Iterable<Turno> guardarTodos(List<Turno> turnos);
}
