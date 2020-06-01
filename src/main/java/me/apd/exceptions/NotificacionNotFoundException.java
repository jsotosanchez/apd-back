package me.apd.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "notificacion no encontrada")
public class NotificacionNotFoundException extends RuntimeException {
}