package me.apd.services;

import me.apd.entities.Especialidad;

import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    Optional<Especialidad> buscarPorId(long id);

    List<Especialidad> buscarTodos();
}
