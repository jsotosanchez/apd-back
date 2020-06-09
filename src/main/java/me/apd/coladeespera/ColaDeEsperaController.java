package me.apd.coladeespera;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaDeEspera")
public class ColaDeEsperaController {

    private final ColaDeEsperaService colaDeEsperaService;

    public ColaDeEsperaController(ColaDeEsperaService colaDeEsperaService) {
        this.colaDeEsperaService = colaDeEsperaService;
    }

    @PostMapping("")
    public Long agregar(@RequestBody ColaDeEsperaBody body) {
        return colaDeEsperaService.agregar(body.getEspecialidadId(), body.getPacienteId(), body.getMedicoId()).getId();
    }

}