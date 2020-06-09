package me.apd.agenda;

import lombok.Builder;
import lombok.Data;
import me.apd.especialidad.Especialidad;
import me.apd.usuario.Usuario;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Entity
@Data
@Builder
@Table(name = "turnos")
public class Turno {
    @Id
    private Long id;
    private LocalDateTime horario;
    private Boolean confirmado;
    @ManyToOne
    private Usuario medico;
    @ManyToOne
    private Usuario paciente;
    @ManyToOne
    private Especialidad especialidad;
}
