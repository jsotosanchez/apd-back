package me.apd.services;

import me.apd.entities.Medico;
import me.apd.entities.Paciente;

import java.util.Optional;

public interface Usuario {
    Optional<Medico> buscarMedicoPorId(long id);

    Optional<Paciente> buscarPacientePorId(long id);
}
