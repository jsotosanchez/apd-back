package me.apd.services;

import me.apd.entities.Turno;

import java.util.Optional;

public interface Agenda {
    Turno guardarAgenda(Turno turno);

    void eliminarPorId(Long id);
    Optional<Turno> buscarPorId(long id);

}
