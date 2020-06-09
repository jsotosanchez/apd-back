package me.apd.repositories;

import me.apd.entities.Usuario;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
    Optional<Usuario> findByDocumento(String s);

    @Query("select NEW me.apd.repositories.UsuarioBase(u.id, u.nombre) from Usuario u where u.id = :usuario_id")
    Optional<UsuarioBase> findUsuarioBase(@Param("usuario_id") Long id);

    @Query("select NEW me.apd.repositories.UsuarioBase(u.id, u.nombre) from Usuario u join u.especialidades e where e.id = :id")
    List<UsuarioBase> findByEspecialidad(@Param("id") Long id);
}