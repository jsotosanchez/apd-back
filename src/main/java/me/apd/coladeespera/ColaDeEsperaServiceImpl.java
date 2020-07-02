package me.apd.coladeespera;

import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.Optional;

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

    @Override
    public Optional<ColaDeEspera> buscar(Long especialdiadId, Long medicoId) {
        Iterator<ColaDeEspera> cursor = colaDeEsperaRepository
                .findByEspecialidadIdAndMedicoIdOrMedicoIdNull(especialdiadId, medicoId).iterator();
        return cursor.hasNext() ? Optional.of(cursor.next()) : Optional.empty();
    }

    @Override
    public void delete(ColaDeEspera colaDeEspera) {
        colaDeEsperaRepository.delete(colaDeEspera);
    }

}
