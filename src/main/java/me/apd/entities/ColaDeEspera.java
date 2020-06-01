package me.apd.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ColaDeEspera {
    @Id
    private Long id;
    private Usuario paciente;
    private Usuario medico;
    private Especialidad especialidad;
}
