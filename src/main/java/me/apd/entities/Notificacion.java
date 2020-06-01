package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Notificacion {

    @Id
    private Long id;
    private String mensaje;
    private Boolean leida;
    private Usuario usuario;
}
