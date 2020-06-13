package me.apd.turno;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public interface DiaMedicoView {
    @Value("#{target.horario}")
    Instant getHorario();
}
