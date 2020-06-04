package me.apd.services;

import me.apd.entities.Usuario;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarMedicoPorId(long id);

    Optional<Usuario> buscarPacientePorId(long id);

    Optional<Usuario> buscarPorDocumento(String s);
}
