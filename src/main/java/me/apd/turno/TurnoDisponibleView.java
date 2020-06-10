package me.apd.turno;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public interface TurnoDisponibleView {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.horario}")
    Instant getHorario();
}
