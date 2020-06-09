package me.apd.coladeespera;

import me.apd.especialidad.Especialidad;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaDeEsperaRepository extends CrudRepository<ColaDeEspera, Long> {
    ColaDeEspera findByEspecialidad(Especialidad especialidad);
}
