package me.apd.turno;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public interface TurnoPacienteView {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.horario}")
    Instant getHorario();
}
