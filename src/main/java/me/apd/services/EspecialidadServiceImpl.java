package me.apd.services;

import me.apd.entities.Especialidad;
import me.apd.entities.Usuario;
import me.apd.repositories.EspecialidadBase;
import me.apd.repositories.EspecialidadRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

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
        List<EspecialidadBase> list = StreamSupport.stream(repository.findAllEspecialidadBase().spliterator(), false)
                .collect(Collectors.toList());
        return list;
    }

    @Override
    public List<Especialidad> buscarPorMedico(Usuario medico) {
        return repository.findByMedico(medico.getId());
    }
}
