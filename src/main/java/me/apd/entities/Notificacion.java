package me.apd.entities;

import javax.persistence.Id;

public class Notificacion {

    @Id
    private Long id;
    private String mensaje;
    private Boolean leida;
    private Usuario usuario;
}
