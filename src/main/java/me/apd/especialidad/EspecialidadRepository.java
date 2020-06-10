package me.apd.especialidad;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {
    @Query("select NEW me.apd.especialidad.EspecialidadBase(e.id, e.descripcion) from Especialidad e join e.medico m where m.id = :medico_id")
    List<EspecialidadBase> findByMedico(@Param("medico_id") Long id);

    @Query("select NEW me.apd.especialidad.EspecialidadBase(e.id, e.descripcion) from Especialidad e")
    List<EspecialidadBase> findAllEspecialidadBase();
}
