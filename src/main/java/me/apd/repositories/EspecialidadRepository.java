package me.apd.repositories;

import me.apd.entities.Especialidad;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.time.Instant;
import java.util.List;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {
    @Query("select e from Especialidad e join e.medico m where m.id = :medico_id")
    List<Especialidad> findByMedico(@Param("medico_id") Long usuarioId);

    List<Especialidad> findByMedicoAndDiaBetween(@Param("medico_id") Long medicoId, @Param("desde") Instant desde, @Param("hasta") Instant hasta);
}
