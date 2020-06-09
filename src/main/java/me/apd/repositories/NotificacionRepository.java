package me.apd.repositories;

import me.apd.entities.Notificacion;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {

    @Query("select NEW me.apd.repositories.NotificacionBase(n.id, n.mensaje, n.leida) from Notificacion n where n.leida = 0 and n.usuario.id = :id")
    List<NotificacionBase> buscarNoLeidaPorUsuario(@Param("id") Long id);
}



