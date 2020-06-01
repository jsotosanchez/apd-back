package me.apd.repositories;

import me.apd.entities.Notificacion;
import me.apd.entities.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {
    List<Notificacion> findByUsuarioAndLeidaTrue(Usuario usuario);
}



