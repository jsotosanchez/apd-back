package me.apd.services;

import me.apd.entities.Turno;
import me.apd.repositories.AgendaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public Turno guardarAgenda(Turno turno) {
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

    @Override
    public Iterable<Turno> guardarTodos(List<Turno> turnos) {
        return agendaRepository.saveAll(turnos);
    }


}
