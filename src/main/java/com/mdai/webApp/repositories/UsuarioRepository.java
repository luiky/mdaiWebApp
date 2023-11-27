package com.mdai.webApp.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.mdai.webApp.entities.Usuario;

public interface UsuarioRepository extends CrudRepository<Usuario, Long> {
	
	@Query("SELECT DISTINCT u FROM Usuario u LEFT JOIN FETCH u.direcciones")
    Iterable<Usuario> findAllWithDirecciones();
	
	

}
