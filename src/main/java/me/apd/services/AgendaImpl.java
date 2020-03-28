package me.apd.services;

import me.apd.entities.Turno;
import me.apd.repositories.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgendaImpl implements Agenda {

    private final AgendaRepository agendaRepository;

    public AgendaImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public Turno cargarAgenda(Turno turno) {
        agendaRepository.save(turno);
        return turno;
    }

    @Override
    public Turno modificarAgenda(Turno turno) {
        agendaRepository.save(turno);
        return turno;
    }

    @Override
    public void eliminarPorId(Long id) {
        agendaRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> buscarPorId(long id) {
        return agendaRepository.findById(id);
    }


}
