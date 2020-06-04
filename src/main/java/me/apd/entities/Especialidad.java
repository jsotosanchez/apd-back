package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Collections;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "especialidades")
public class Especialidad {
    @Id
    private Long id;
    private String descripcion;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "especialidades_de_medico",
            joinColumns = @JoinColumn(name = "id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id")
    )
    private List<Usuario> medico = Collections.EMPTY_LIST;

    public Especialidad(Long id, String descripcion, List<Usuario> medico) {
        this.id = id;
        this.descripcion = descripcion;
        this.medico = medico;
    }

    public Especialidad() {
    }
}
