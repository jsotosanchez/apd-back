package me.apd.coladeespera;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping("/colaDeEspera")
public class ColaDeEsperaController {

    private final ColaDeEsperaService colaDeEsperaService;

    public ColaDeEsperaController(ColaDeEsperaService colaDeEsperaService) {
        this.colaDeEsperaService = colaDeEsperaService;
    }

    @PostMapping("")
    @RolesAllowed({"MEDICO", "PACIENTE"})
    public Long agregar(@RequestBody ColaDeEsperaBody body) {
        return colaDeEsperaService.agregar(body.getEspecialidadId(), body.getPacienteId(), body.getMedicoId()).getId();
    }

}