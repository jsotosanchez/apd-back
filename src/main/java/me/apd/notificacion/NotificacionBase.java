package me.apd.notificacion;

import lombok.Value;

@Value
public class NotificacionBase {
    Long id;
    String mensaje;
    Boolean leida;
}
