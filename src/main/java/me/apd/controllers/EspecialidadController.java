package me.apd.controllers;

import me.apd.entities.Especialidad;
import me.apd.exceptions.MedicoNotFoundException;
import me.apd.repositories.EspecialidadBase;
import me.apd.services.EspecialidadService;
import me.apd.services.UsuarioService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

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
    public List<EspecialidadBase> buscarTodos() {
        return especialidadService.buscarTodos();
    }

    @GetMapping("/medico/{id}")
    public List<Especialidad> especialidadesPorMedico(@PathVariable Long medicoId) {
        return usuarioService.buscarMedicoPorId(medicoId)
                .map(especialidadService::buscarPorMedico)
                .orElseThrow(MedicoNotFoundException::new);
    }
}
