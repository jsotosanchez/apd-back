package me.apd.notificacion;

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

    public Long marcarLeida(Long id) {
        repository.marcarLeida(id);
        return id;
    }

    @Override
    public List<Notificacion> buscarTodas() {
        return StreamSupport.stream(repository.findAll().spliterator(), false).collect(Collectors.toList());
    }

    @Override
    public List<NotificacionBase> buscarPorUsuario(Long id) {
        return repository.findByUsuarioIdAndLeidaFalse(id);
    }

    @Override
    public Notificacion crear(Notificacion notificacion) {
        return repository.save(notificacion);
    }
}
