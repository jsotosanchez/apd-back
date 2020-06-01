package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String documento;
    private Boolean pagoAlDia;
    private String matricula;
    @ManyToMany
    @JoinTable(
            name = "especialidadesDeMedico",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "medico_id"))
    private List<Especialidad> especialidades;
}