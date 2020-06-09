package me.apd.controllers;

import me.apd.entities.Usuario;
import me.apd.exceptions.UsuarioNotFoundException;
import me.apd.services.UsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @GetMapping("/{documento}")
    public Usuario especialidadesPorMedico(@PathVariable String documento) {
        return usuarioService.buscarPorDocumento(documento).orElseThrow(UsuarioNotFoundException::new);
    }
}
