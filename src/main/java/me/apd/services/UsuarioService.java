package me.apd.services;

import me.apd.entities.Usuario;
import me.apd.repositories.UsuarioBase;

import java.util.Optional;

public interface UsuarioService {
    Optional<Usuario> buscarPorId(long id);

    Optional<Usuario> buscarPorDocumento(String s);

    Optional<UsuarioBase> buscarUsuarioBasePorId(long id);

}
