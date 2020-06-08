package me.apd.services;

import me.apd.entities.Notificacion;
import me.apd.entities.Usuario;
import me.apd.exceptions.UsuarioNotFoundException;
import me.apd.repositories.NotificacionRepository;
import me.apd.repositories.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    private final NotificacionRepository repository;
    private final UsuarioRepository usuarioRepository;

    public NotificacionServiceImpl(NotificacionRepository repository, UsuarioRepository usuarioRepository) {
        this.repository = repository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Optional<Notificacion> buscarPorId(long id) {
        return repository.findById(id);
    }

    public Notificacion marcarLeida(Notificacion notificacion) {
        notificacion.setLeida(true);
        return repository.save(notificacion);
    }

    @Override
    public List<Notificacion> buscarTodas() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<Notificacion> buscarPorUsuario(Long id) {
        Usuario usuario = usuarioRepository.findById(id).orElseThrow(UsuarioNotFoundException::new);
        return repository.findByUsuarioAndLeidaFalse(usuario);
    }
}
