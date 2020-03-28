package me.apd.entities;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Builder
public class Medico {

    @Id
    private Long id;
    private String nombre;
    private String documento;
}
