package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Date;

@Entity
@Data
@Builder
public class Turno {
    @Id
    private Long id;
    private Date horario;
    private Boolean confirmado;
    @ManyToOne
    private Medico medico;
    @ManyToOne
    private Paciente paciente;
    @ManyToOne
    private Especialidad especialidad;
}
