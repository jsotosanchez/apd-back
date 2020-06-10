package me.apd.turno;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface TurnoRepository extends CrudRepository<Turno, Long> {
    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.medico.id = :medicoId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<TurnoPacienteView> findByEspecialidadAndMedicoAndHorarioAfterAndPacienteIsNull(@Param("especialidadId") Long especialidadId,
                                                                                        @Param("medicoId") Long medicoId,
                                                                                        @Param("hoy") Timestamp hoy);

    @Query("select t from Turno t " +
            "where t.especialidad.id = :especialidadId and" +
            " t.horario > :hoy and" +
            " t.paciente.id = null")
    List<TurnoPacienteView> findByDisponibleEspecialidad(@Param("especialidadId") Long especialidadId, @Param("hoy") Timestamp hoy);

    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = :usuarioId where t.id = :turnoId")
    void reservarTurno(@Param("usuarioId") Long usuarioId, @Param("turnoId") Long turnoId);


    @Modifying
    @Transactional
    @Query("update Turno t set t.paciente.id = null where t.id = :turnoId")
    void cancelarTurno(Long turnoId);

    @Query("select t from Turno t where t.paciente.id = :pacienteId and t.horario > :hoy")
    List<Turno> findByPaciente(@Param("pacienteId") long pacienteId, @Param("hoy") Timestamp hoy);


    @Query("select t from Turno t where t.medico.id = :medicoId and t.horario > :hoy")
    List<Turno> findByMedico(@Param("medicoId") long id, @Param("hoy") Timestamp hoy);
}
