package me.apd.coladeespera;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/colaDeEspera")
public class ColaDeEsperaController {

    private final ColaDeEsperaService colaDeEsperaService;

    public ColaDeEsperaController(ColaDeEsperaService colaDeEsperaService) {
        this.colaDeEsperaService = colaDeEsperaService;
    }

//    @PostMapping("")
//    public Long agregar(@RequestBody ColaDeEsperaBody body) {
//        return colaDeEsperaService
//                .agregar(Long.parseLong(body.getEspecialidadId()), Long.parseLong(body.getMedicoId()), Long
//                        .parseLong(body.getPacienteId())).getId();
//    }

}