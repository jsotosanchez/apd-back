package me.apd.agenda;

import me.apd.usuario.Usuario;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;

@Repository
public interface AgendaRepository extends CrudRepository<Turno, Long> {
    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.medico.id = :medicoId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<Turno> findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(@Param("especialidadId") Long especialidadId,
                                                                            @Param("medicoId") Long medicoId,
                                                                            @Param("hoy") Timestamp hoy);

    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<Turno> findByDisponibleEspecialidad(@Param("especialidadId") Long especialidadId, @Param("hoy") Timestamp hoy);

    List<Turno> findByPacienteAndHorarioAfter(Usuario paciente, Instant dia);

    List<Horario> findByMedicoAndHorarioAfter(Usuario medico, Instant dia);

    List<Horario> findByMedicoAndHorarioBetween(Usuario medico, Instant desde, Instant hasta);

    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = :usuarioId where t.id = :turnoId")
    void reservarTurno(@Param("usuarioId") Long usuarioId, @Param("turnoId") Long turnoId);


    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = null where t.id = :turnoId")
    void cancelarTurno(Long turnoId);

}
