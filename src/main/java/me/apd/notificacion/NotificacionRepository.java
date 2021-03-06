package me.apd.notificacion;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface NotificacionRepository extends CrudRepository<Notificacion, Long> {

    List<NotificacionBase> findByUsuarioIdAndLeidaFalse(@Param("id") Long id);

    @Modifying
    @Transactional
    @Query("update Notificacion n set n.leida = true where n.id = :id")
    void marcarLeida(@Param("id") Long id);

}



