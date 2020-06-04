package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "cola_de_espera")
public class ColaDeEspera {
    @Id
    private Long id;
    @OneToOne
    private Usuario paciente;
    @OneToOne
    private Usuario medico;
    @ManyToOne
    private Especialidad especialidad;
}
