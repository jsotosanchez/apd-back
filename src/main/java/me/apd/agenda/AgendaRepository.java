package me.apd.agenda;

import me.apd.usuario.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.time.Instant;
import java.util.List;

@Repository
public interface AgendaRepository extends CrudRepository<Turno, Long> {
//    @Query()
List<Horario> findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(Long especialidadId, Long medicoId, Horario hoy);

    List<Turno> findByPacienteAndHorarioAfter(Usuario paciente, Instant dia);

    List<Horario> findByMedicoAndHorarioAfter(Usuario medico, Instant dia);

    List<Horario> findByMedicoAndHorarioBetween(Usuario medico, Instant desde, Instant hasta);

//    @Query("select TOP 1 especialidad from Turnos t where t.medico_id == medico_id and t.horario >= dia order by id")
//    Especialidad findByMedicoAndHorario(@Param("medico_id")Long medico, @Param("dia")Instant dia);
}
