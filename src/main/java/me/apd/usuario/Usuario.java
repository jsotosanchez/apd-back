package me.apd.usuario;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.apd.especialidad.Especialidad;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Builder
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long id;
    private String nombre;
    private String documento;
    private String password;
    private Boolean pagoAlDia;
    private String role;
    @ManyToMany
    @JoinTable(
            name = "especialidades_de_medico",
            joinColumns = @JoinColumn(name = "medico_id"),
            inverseJoinColumns = @JoinColumn(name = "especialidad_id"))
    private List<Especialidad> especialidades;
}