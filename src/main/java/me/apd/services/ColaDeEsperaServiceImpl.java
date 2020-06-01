package me.apd.services;


import me.apd.entities.ColaDeEspera;
import me.apd.repositories.ColaDeEsperaRepository;

public class ColaDeEsperaServiceImpl implements ColaDeEsperaService {

    private final ColaDeEsperaRepository colaDeEsperaRepository;

    public ColaDeEsperaServiceImpl(ColaDeEsperaRepository colaDeEsperaRepository) {
        this.colaDeEsperaRepository = colaDeEsperaRepository;
    }

    @Override
    public ColaDeEspera agregar(ColaDeEspera cola) {
        return colaDeEsperaRepository.save(cola);
    }
}
