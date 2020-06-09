package me.apd.coladeespera;

import org.springframework.stereotype.Service;

@Service
public class ColaDeEsperaServiceImpl implements ColaDeEsperaService {

    private final ColaDeEsperaRepository colaDeEsperaRepository;

    public ColaDeEsperaServiceImpl(ColaDeEsperaRepository colaDeEsperaRepository) {
        this.colaDeEsperaRepository = colaDeEsperaRepository;
    }


    @Override
    public ColaDeEspera agregar(Long especialidadId, Long paciente, Long medicoId) {
        ColaDeEspera nuevaEntrada = ColaDeEspera.builder().especialidadId(especialidadId).medicoId(medicoId)
                .pacienteId(paciente).build();
        return colaDeEsperaRepository.save(nuevaEntrada);
    }

}
