package me.apd.controllers.dto;


import lombok.Value;

import java.util.Collection;

@Value
public class UsuarioView {
    Long id;
    String nombre;
    String documento;
    String matricula;
    Boolean pagoAlDia;
    Collection<EspecialidadView> especialidad;
}
