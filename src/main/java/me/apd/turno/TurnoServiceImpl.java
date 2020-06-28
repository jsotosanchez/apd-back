package me.apd.turno;

import me.apd.especialidad.Especialidad;
import me.apd.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;
import java.time.temporal.ChronoUnit;
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
    public List<TurnoDisponibleView> buscarDisponiblesPorEspecialidadYMedico(Long especialidadId, Long medicoId) {
        Instant hoy = Instant.now();

        return turnoRepository
                .findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(especialidadId, medicoId, Timestamp
                        .from(hoy));
    }

    @Override
    public List<TurnoDisponibleView> buscarDisponiblesPorEspecialidad(Long especialidadId) {
        Instant hoy = Instant.now();

        return turnoRepository.findByDisponibleEspecialidad(especialidadId, Timestamp.from(hoy));
    }

    @Override
    public List<TurnoMedicoView> buscarPorMedicoEntreFechas(Long id, String fecha) {
        Date dia = Date.valueOf(fecha);
        Date diaSiguiente = new java.sql.Date(dia.getTime() + 24 * 60 * 60 * 1000);

        return turnoRepository.findByMedicoDatesBetween(id, dia, diaSiguiente);
    }

    @Override
    public Long confirmarTurno(Long id) {
        turnoRepository.confirmarTurno(id);
        return id;
    }

    @Override
    public List<DiaMedicoView> buscarPorMedico(Long id) {
        Instant hoy = Instant.now();
        return turnoRepository.findHorariosByMedico(id, Timestamp.from(hoy));
    }

    @Override
    public void eliminarPorDia(Long id, String fecha) {

        Date dia = Date.valueOf(fecha);
        Date diaSiguiente = new java.sql.Date(dia.getTime() + 24 * 60 * 60 * 1000);

        turnoRepository.deleteByMedicoAndDia(id, dia, diaSiguiente);
    }

    @Override
    public List<TurnoPacienteView> buscarPorPacienteYDia(Long id, Instant horario) {
        Timestamp time = Timestamp.from(LocalDateTime.ofInstant(horario, ZoneOffset.ofHours(0)).plusDays(1)
                .toInstant(ZoneOffset.ofHours(0)).truncatedTo(ChronoUnit.DAYS));
        return turnoRepository.findByPacienteAndDia(id, Timestamp.from(horario.truncatedTo(ChronoUnit.DAYS)), time);
    }

    @Override
    public List<TurnoPacienteView> buscarPorPaciente(long pacienteId) {
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

    @Override
    public void cargarHorarios(Usuario medico, Especialidad especialidad, Instant fechaFin, Instant fechaTurno, Instant horaLimite, Instant horaInicio, List<Turno> turnosCreados, TurnoController turnoController) {
        while (fechaTurno.isBefore(fechaFin)) {
            DayOfWeek day = fechaTurno.atZone(ZoneId.systemDefault()).getDayOfWeek();
            if (!(day.equals(DayOfWeek.SATURDAY) || day.equals(DayOfWeek.SUNDAY)))
                while (fechaTurno.isBefore(horaLimite)) {
                    Turno turnoNuevo = Turno.builder()
                            .medico(medico)
                            .especialidad(especialidad)
                            .horario(Timestamp.from(fechaTurno))
                            .confirmado(false)
                            .build();
                    turnosCreados.add(turnoNuevo);
                    fechaTurno = fechaTurno.plus(1, ChronoUnit.HOURS);
                }
            horaLimite = horaLimite.plus(1, ChronoUnit.DAYS);
            horaInicio = horaInicio.plus(1, ChronoUnit.DAYS);
            fechaTurno = horaInicio;
        }
        guardarTodos(turnosCreados);
    }
}
