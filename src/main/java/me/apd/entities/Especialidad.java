package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
public class Especialidad {

    @Id
    private Long id;
    private String descripcion;
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "especialidadesDeMedico",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    private Usuario medico;
}
