package me.apd.services;

import me.apd.entities.Notificacion;
import me.apd.repositories.NotificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class NotificacionServiceImpl implements NotificacionService {
    private final NotificacionRepository repository;

    public NotificacionServiceImpl(NotificacionRepository repository) {
        this.repository = repository;
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
}
