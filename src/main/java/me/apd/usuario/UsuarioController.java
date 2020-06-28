package me.apd.usuario;

import me.apd.especialidad.Especialidad;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import javax.validation.constraints.NotNull;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {
    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    //    @GetMapping("/{documento}")
//    public Usuario datosUsuario(@PathVariable String documento) {
//        return usuarioService.buscarPorDocumento(documento).orElseThrow(UsuarioNotFoundException::new);
//    }
    @RolesAllowed("MEDICO")
    @GetMapping("/{id}/especialidades")
    public List<Especialidad> especialidadesDeMedico(@Validated @NotNull @PathVariable Long id) {
        return usuarioService.buscarEspecialidadesPorMedico(id);
    }

    @RolesAllowed({"PACIENTE", "MEDICO"})
    @GetMapping("/especialidades/{id}")
    public List<UsuarioBase> medicosPorEspecialidad(@PathVariable Long id) {
        return usuarioService.buscarMedicosPorEspecialidadId(id);
    }

}
