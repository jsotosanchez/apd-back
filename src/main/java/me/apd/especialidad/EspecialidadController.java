package me.apd.especialidad;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@RestController
@RequestMapping("/especialidades")
public class EspecialidadController {
    private final EspecialidadService especialidadService;

    public EspecialidadController(EspecialidadService especialidadService) {
        this.especialidadService = especialidadService;
    }

    @GetMapping("")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public List<EspecialidadBase> buscarTodos() {
        return especialidadService.buscarTodos();
    }

}
