package me.apd.repositories;

import me.apd.entities.ColaDeEspera;
import me.apd.entities.Especialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaDeEsperaRepository extends CrudRepository<ColaDeEspera, Long> {
    ColaDeEspera findByEspecialidad(Especialidad especialidad);
}
