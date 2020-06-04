package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Data
@Builder
@Table(name = "notificaciones")
public class Notificacion {

    @Id
    private Long id;
    private String mensaje;
    private Boolean leida;
    @OneToOne
    private Usuario usuario;
}
