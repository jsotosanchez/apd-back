package me.apd.services;

import me.apd.entities.Usuario;
import me.apd.repositories.UsuarioBase;
import me.apd.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Usuario> buscarPorId(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPorDocumento(String s) {
        return usuarioRepository.findByDocumento(s);
    }

    @Override
    public Optional<UsuarioBase> buscarUsuarioBasePorId(long id) {
        return usuarioRepository.findUsuarioBase(id);
    }


}
