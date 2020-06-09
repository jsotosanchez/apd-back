package me.apd.coladeespera;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ColaDeEsperaRepository extends CrudRepository<ColaDeEspera, Long> {
}
