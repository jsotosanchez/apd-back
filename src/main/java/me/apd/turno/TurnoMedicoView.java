package me.apd.turno;

import org.springframework.beans.factory.annotation.Value;

import java.time.Instant;

public interface TurnoMedicoView {
    @Value("#{target.id}")
    Long getId();

    @Value("#{target.horario}")
    Instant getHorario();

    @Value("#{target.paciente  == null ? null : target.paciente.nombre}")
    String getPaciente();

    @Value("#{target.especialidad.descripcion}")
    String getEspecialidad();
}
