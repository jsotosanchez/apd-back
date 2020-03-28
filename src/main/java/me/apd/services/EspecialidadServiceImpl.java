package me.apd.services;

import me.apd.entities.Especialidad;
import me.apd.repositories.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EspecialidadServiceImpl implements EspecialidadService {
    private final EspecialidadRepository repository;

    public EspecialidadServiceImpl(EspecialidadRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<Especialidad> buscarPorId(long id) {
        return repository.findById(id);
    }
}
