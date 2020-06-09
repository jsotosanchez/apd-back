package me.apd.usuario;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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

    @GetMapping("especialidades/{id}")
    public List<UsuarioBase> medicosPorEspecialidad(@PathVariable Long id) {
        return usuarioService.buscarMedicosPorEspecialidadId(id);
    }
}
