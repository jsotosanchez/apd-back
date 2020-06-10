package me.apd.agenda;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class AgendaServiceImpl implements AgendaService {

    private final AgendaRepository agendaRepository;

    public AgendaServiceImpl(AgendaRepository agendaRepository) {
        this.agendaRepository = agendaRepository;
    }

    @Override
    public Turno guardarTurno(Turno turno) {
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

    @Override
    public List<Turno> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId) {
        Instant hoy = Instant.now();

        return agendaRepository
                .findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(especialidadId, medicoId, Timestamp
                        .from(hoy));
//                .stream().map(t ->t.getHorario().toLocalDateTime()).collect(Collectors
//                        .toList());
    }

    @Override
    public List<Turno> buscarDisponiblesPorEspecialidad(Long especialidadId) {
        Instant hoy = Instant.now();

        return agendaRepository.findByDisponibleEspecialidad(especialidadId, Timestamp.from(hoy));
    }

    @Override
    public List<Turno> buscarPorPaciente(long pacienteId) {
        Instant hoy = Instant.now();
        return agendaRepository.findByPaciente(pacienteId, Timestamp.from(hoy));
    }

    @Override
    public Long reservarTurno(Long usuarioId, Long turnoId) {
        agendaRepository.reservarTurno(usuarioId, turnoId);
        return turnoId;
    }

    @Override
    public Long cancelarTurno(Long turnoId) {
        agendaRepository.cancelarTurno(turnoId);
        return turnoId;
    }


}
