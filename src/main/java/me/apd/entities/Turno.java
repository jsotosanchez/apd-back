package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
public class Turno {
    @Id
    private Long id;
    private LocalDateTime horario;
    private LocalDateTime fecha;
    private Boolean confirmado;
    @ManyToOne
    private Usuario medico;
    @ManyToOne
    private Usuario paciente;
    @ManyToOne
    private Especialidad especialidad;
}
