package me.apd.agenda;

import me.apd.especialidad.Especialidad;
import me.apd.usuario.Usuario;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
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
    public List<Horario> buscarDisponiblesPorEspecialidadYMedico(String especialidadId, String medicoId) {
        Usuario medico = Usuario.builder().id(Long.parseLong(medicoId)).build();
        Especialidad especialidad = Especialidad.builder().id(Long.parseLong(especialidadId)).build();
        Horario hoy = Horario.builder().horario(LocalDateTime.now()).build();

        return agendaRepository.findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(especialidad, medico, hoy);
    }

    @Override
    public List<Turno> buscarPorPaciente(long id) {
        Usuario paciente = Usuario.builder().id(id).build();
        Instant hoy = Instant.now();
        return agendaRepository.findByPacienteAndHorarioAfter(paciente, hoy);
    }


}
