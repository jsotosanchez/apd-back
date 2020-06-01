package me.apd.controllers;

import me.apd.entities.Especialidad;
import me.apd.entities.Usuario;
import me.apd.exceptions.MedicoNotFoundException;
import me.apd.services.EspecialidadService;
import me.apd.services.UsuarioService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@Validated
@RequestMapping("/especialidad")
public class EspecialidadController {
    private final EspecialidadService especialidadService;
    private final UsuarioService usuarioService;

    public EspecialidadController(EspecialidadService especialidadService, UsuarioService usuarioService) {
        this.especialidadService = especialidadService;
        this.usuarioService = usuarioService;
    }

    @GetMapping("")
    public List<Especialidad> buscarTodos() {
        return especialidadService.buscarTodos();
    }

    @GetMapping("/medico/{id}")
    public List<Especialidad> especialidadesPorMedico(@PathVariable String medicoId) {
        Optional<Usuario> medico = usuarioService.buscarMedicoPorId(Long.parseLong(medicoId));
        if (medico.isPresent()) {
            especialidadService.buscarPorMedico(medico.get());
        } else {
            throw new MedicoNotFoundException();
        }
        return especialidadService.buscarTodos();
    }
}
