package me.apd.entities;

import javax.persistence.Id;

public class ColaDeEspera {
    @Id
    private Long id;
    private Usuario paciente;
    private Usuario medico;
    private Especialidad especialidad;
}
