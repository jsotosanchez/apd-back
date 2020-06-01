package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class ColaDeEspera {
    @Id
    private Long id;
    private Usuario paciente;
    private Usuario medico;
    private Especialidad especialidad;
}
