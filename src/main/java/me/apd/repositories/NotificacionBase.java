package me.apd.repositories;

import lombok.Value;

@Value
public class NotificacionBase {
    Long id;
    String mensaje;
    Boolean leida;
}
