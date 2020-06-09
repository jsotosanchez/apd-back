package me.apd.usuario;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarPorId(long id);

    Optional<Usuario> buscarPorDocumento(String s);

    Optional<UsuarioBase> buscarUsuarioBasePorId(Long id);

    List<UsuarioBase> buscarMedicosPorEspecialidadId(Long id);
}
