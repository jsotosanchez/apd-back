package me.apd.services;

import me.apd.entities.Medico;
import me.apd.entities.Paciente;
import me.apd.repositories.MedicoRepository;
import me.apd.repositories.PacienteRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioImpl implements Usuario {
    private final MedicoRepository medicoRepository;
    private final PacienteRepository pacienteRepository;

    public UsuarioImpl(MedicoRepository repository, PacienteRepository pacienteRepository) {
        this.medicoRepository = repository;
        this.pacienteRepository = pacienteRepository;
    }

    @Override
    public Optional<Medico> buscarMedicoPorId(long id) {
        return medicoRepository.findById(id);
    }

    @Override
    public Optional<Paciente> buscarPacientePorId(long id) {
        return pacienteRepository.findById(id);
    }

}
