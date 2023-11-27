package com.mdai.webApp.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.mdai.webApp.entities.Direccion;
import com.mdai.webApp.entities.Usuario;

public interface DireccionRepository extends CrudRepository<Direccion, Long> {
	
	List<Direccion> findByUsuario(Usuario u);

}
