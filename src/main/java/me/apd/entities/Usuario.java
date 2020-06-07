package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "usuarios")
public class Usuario {
    @Id
    private Long id;
    private String nombre;
    private String documento;
    private String password;
    private Boolean pagoAlDia;
    private String matricula;
    @ManyToMany
    @JoinTable(
            name = "especialidades_de_medico",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    private List<Especialidad> especialidades;

    public Usuario() {
    }

    public Usuario(Long id, String nombre, String documento, String password, Boolean pagoAlDia, String matricula, List<Especialidad> especialidades) {
        this.id = id;
        this.nombre = nombre;
        this.documento = documento;
        this.password = password;
        this.pagoAlDia = pagoAlDia;
        this.matricula = matricula;
        this.especialidades = especialidades;
    }
}