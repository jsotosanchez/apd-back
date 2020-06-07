package me.apd.services;

import me.apd.entities.Especialidad;
import me.apd.repositories.EspecialidadBase;
import me.apd.repositories.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<EspecialidadBase> buscarTodos() {
        return new ArrayList<>(repository.findAllEspecialidadBase());
    }

    @Override
    public List<EspecialidadBase> buscarPorUsuarioId(Long id) {
        return repository.findByMedico(id);
    }
}
