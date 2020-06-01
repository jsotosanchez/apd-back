package me.apd.services;

import me.apd.entities.Notificacion;

import java.util.List;
import java.util.Optional;

public interface NotificacionService {
    Optional<Notificacion> buscarPorId(long parseLong);

    Notificacion marcarLeida(Notificacion notificacion);

    List<Notificacion> buscarTodas();
}
