package me.apd.controllers;

import me.apd.repositories.EspecialidadBase;
import me.apd.services.EspecialidadService;
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

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping("")
    public List<EspecialidadBase> buscarTodos() {
        return especialidadService.buscarTodos();
    }

    @GetMapping("/medico/{id}")
    public List<EspecialidadBase> especialidadesPorMedico(@PathVariable Long id) {
        return especialidadService.buscarPorUsuarioId(id);
    }
}
