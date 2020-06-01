package me.apd.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Notificacion {

    @Id
    private Long id;
    private String mensaje;
    private Boolean leida;
    private Usuario usuario;
}
