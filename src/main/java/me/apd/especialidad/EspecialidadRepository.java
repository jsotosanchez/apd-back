package me.apd.especialidad;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface EspecialidadRepository extends CrudRepository<Especialidad, Long> {
    @Query("select NEW me.apd.especialidad.EspecialidadBase(e.id, e.descripcion) from Especialidad e")
    List<EspecialidadBase> findAllEspecialidadBase();
}
