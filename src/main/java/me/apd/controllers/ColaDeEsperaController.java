package me.apd.controllers;

import me.apd.controllers.bodies.ColaDeEsperaBody;
import me.apd.entities.ColaDeEspera;
import me.apd.entities.Especialidad;
import me.apd.entities.Usuario;
import me.apd.services.ColaDeEsperaService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Validated
@RequestMapping("/colaDeEspera")
public class ColaDeEsperaController {

    private final ColaDeEsperaService colaDeEsperaService;

    public ColaDeEsperaController(ColaDeEsperaService colaDeEsperaService) {
        this.colaDeEsperaService = colaDeEsperaService;
    }

    @PostMapping("")
    public Long agregar(@RequestBody ColaDeEsperaBody body) {
        ColaDeEspera nuevo = ColaDeEspera.builder()
                .especialidad(Especialidad.builder().id(Long.parseLong(body.getId())).build())
                .medico(Usuario.builder().id(Long.parseLong(body.getMedicoId())).build())
                .paciente(Usuario.builder().id(Long.parseLong(body.getPacienteId())).build())
                .build();
        return colaDeEsperaService.agregar(nuevo).getId();
    }

}