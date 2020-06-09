package me.apd.especialidad;


import java.util.List;
import java.util.Optional;

public interface EspecialidadService {
    Optional<Especialidad> buscarPorId(long id);

    List<EspecialidadBase> buscarTodos();

    List<EspecialidadBase> buscarPorUsuarioId(Long id);
}
