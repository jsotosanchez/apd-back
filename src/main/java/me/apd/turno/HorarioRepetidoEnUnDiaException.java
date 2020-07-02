package me.apd.turno;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.FORBIDDEN, reason = "ya tiene un turno con ese horario ese dia")
public class HorarioRepetidoEnUnDiaException extends RuntimeException {
}
