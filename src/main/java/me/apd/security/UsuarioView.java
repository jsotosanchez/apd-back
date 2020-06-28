package me.apd.security;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.Collection;

@AllArgsConstructor
@Getter
public class UsuarioView {
    String usuario;
    Long id;
    Collection<String> roles;
}
