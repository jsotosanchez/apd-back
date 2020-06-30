package me.apd.turno;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "turno no encontrado")
public class TurnoNotFoundException extends RuntimeException {
}
