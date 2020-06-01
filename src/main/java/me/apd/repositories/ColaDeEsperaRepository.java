package me.apd.repositories;

import me.apd.entities.ColaDeEspera;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaDeEsperaRepository extends CrudRepository<ColaDeEspera, Long> {
}
