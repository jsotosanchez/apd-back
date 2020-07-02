package me.apd.coladeespera;

import java.util.Optional;

public interface ColaDeEsperaService {
    ColaDeEspera agregar(Long especialidadId, Long paciente, Long medicoId);

    Optional<ColaDeEspera> buscar(Long especialdiadId, Long medicoId);

    void delete(ColaDeEspera colaDeEspera);
}
