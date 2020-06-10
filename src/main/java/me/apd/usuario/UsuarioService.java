package me.apd.usuario;

import me.apd.especialidad.Especialidad;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarPorId(long id);

    Optional<Usuario> buscarPorDocumento(String s);

    Optional<UsuarioBase> buscarUsuarioBasePorId(Long id);

    List<UsuarioBase> buscarMedicosPorEspecialidadId(Long id);

    List<Especialidad> buscarEspecialidadesPorMedico(Long id);
}


