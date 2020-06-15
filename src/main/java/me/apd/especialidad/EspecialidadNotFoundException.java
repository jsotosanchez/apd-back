package me.apd.especialidad;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "especialidad no encontrada")
public class EspecialidadNotFoundException extends RuntimeException {
}
