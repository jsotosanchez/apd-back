package me.apd.notificacion;

import java.util.List;
import java.util.Optional;

public interface NotificacionService {
    Optional<Notificacion> buscarPorId(long parseLong);

    Long marcarLeida(Long notificacion);

    List<Notificacion> buscarTodas();

    List<NotificacionBase> buscarPorUsuario(Long id);


    Notificacion crear(Notificacion notificacion);
}
