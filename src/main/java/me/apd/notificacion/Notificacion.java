package me.apd.notificacion;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.apd.usuario.Usuario;

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
    private Integer leida;
    @OneToOne
    private Usuario usuario;
}
