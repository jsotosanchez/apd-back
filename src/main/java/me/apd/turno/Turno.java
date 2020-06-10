package me.apd.turno;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.apd.especialidad.Especialidad;
import me.apd.usuario.Usuario;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Data
@Builder
@Table(name = "turnos")
@NoArgsConstructor
@AllArgsConstructor
public class Turno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp horario;
    private Boolean confirmado;
    @ManyToOne
    private Usuario medico;
    @ManyToOne
    private Usuario paciente;
    @ManyToOne
    private Especialidad especialidad;
}
