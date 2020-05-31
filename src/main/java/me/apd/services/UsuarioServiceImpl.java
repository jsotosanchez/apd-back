package me.apd.services;

import me.apd.entities.Usuario;
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
    public Optional<Usuario> buscarMedicoPorId(long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    public Optional<Usuario> buscarPacientePorId(long id) {
        return usuarioRepository.findById(id);
    }

}
