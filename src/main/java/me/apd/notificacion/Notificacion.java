package me.apd.notificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.apd.usuario.Usuario;
import org.hibernate.annotations.Type;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "notificaciones")
@AllArgsConstructor
@NoArgsConstructor
public class Notificacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String mensaje;
    @Type(type = "yes_no")
    private Boolean leida;
    @OneToOne
    private Usuario usuario;
}
