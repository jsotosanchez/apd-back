package me.apd.turno;

import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Service
public class TurnoServiceImpl implements TurnoService {

    private final TurnoRepository turnoRepository;

    public TurnoServiceImpl(TurnoRepository turnoRepository) {
        this.turnoRepository = turnoRepository;
    }

    @Override
    public Turno guardarTurno(Turno turno) {
        turnoRepository.save(turno);
        return turno;
    }

    @Override
    public void eliminarPorId(Long id) {
        turnoRepository.deleteById(id);
    }

    @Override
    public Optional<Turno> buscarPorId(long id) {
        return turnoRepository.findById(id);
    }

    @Override
    public Iterable<Turno> guardarTodos(List<Turno> turnos) {
        return turnoRepository.saveAll(turnos);
    }

    @Override
    public List<TurnoPacienteView> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId) {
        Instant hoy = Instant.now();

        return turnoRepository
                .findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(especialidadId, medicoId, Timestamp
                        .from(hoy));
//                .stream().map(t ->t.getHorario().toLocalDateTime()).collect(Collectors
//                        .toList());
    }

    @Override
    public List<TurnoPacienteView> buscarDisponiblesPorEspecialidad(Long especialidadId) {
        Instant hoy = Instant.now();

        return turnoRepository.findByDisponibleEspecialidad(especialidadId, Timestamp.from(hoy));
    }

    @Override
    public List<Turno> buscarPorMedico(Long id) {
        Instant hoy = Instant.now();
        return turnoRepository.findByMedico(id, Timestamp.from(hoy));
    }

    @Override
    public List<Turno> buscarPorPaciente(long pacienteId) {
        Instant hoy = Instant.now();
        return turnoRepository.findByPaciente(pacienteId, Timestamp.from(hoy));
    }

    @Override
    public Long reservarTurno(Long usuarioId, Long turnoId) {
        turnoRepository.reservarTurno(usuarioId, turnoId);
        return turnoId;
    }

    @Override
    public Long cancelarTurno(Long turnoId) {
        turnoRepository.cancelarTurno(turnoId);
        return turnoId;
    }


}
