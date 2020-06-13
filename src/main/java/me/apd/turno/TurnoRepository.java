package me.apd.turno;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Long> {
    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.medico.id = :medicoId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<TurnoDisponibleView> findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(@Param("especialidadId") Long especialidadId,
                                                                                          @Param("medicoId") Long medicoId,
                                                                                          @Param("hoy") Timestamp hoy);

    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<TurnoDisponibleView> findByDisponibleEspecialidad(@Param("especialidadId") Long especialidadId, @Param("hoy") Timestamp hoy);

    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = :usuarioId where t.id = :turnoId")
    void reservarTurno(@Param("usuarioId") Long usuarioId, @Param("turnoId") Long turnoId);

    @Modifying
    @Transactional
    @Query("update Turno t set t.confirmado = 1 where t.id = :turnoId")
    void confirmarTurno(@Param("turnoId") Long turnoId);

    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = null where t.id = :turnoId")
    void cancelarTurno(@Param("turnoId") Long turnoId);

    @Query("select t from Turno t where t.paciente.id = :pacienteId and t.horario > :hoy")
    List<TurnoPacienteView> findByPaciente(@Param("pacienteId") long pacienteId, @Param("hoy") Timestamp hoy);

    @Query("select t from Turno t left join t.paciente where t.medico.id = :medicoId and t.horario > :dia and t.horario < :diaSiguiente")
    List<TurnoMedicoView> findByMedicoDatesBetween(@Param("medicoId") long id, @Param("dia") Date hoy, @Param("diaSiguiente") Date diaSiguiente);

    @Query("select t from Turno t where t.medico.id = :medicoId and t.horario > :hoy")
    List<DiaMedicoView> findHorariosByMedico(@Param("medicoId") Long id, @Param("hoy") Timestamp hoy);

    @Modifying
    @Query("delete from Turno t where t.medico.id = :medicoId and t.horario > :dia and t.horario < :diaSiguiente")
    void deleteByMedicoAndDia(@Param("medicoId") long id, @Param("dia") Date hoy, @Param("diaSiguiente") Date diaSiguiente);
}
