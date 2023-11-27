package com.mdai.webApp.services;

import java.util.Optional;

import com.mdai.webApp.entities.Direccion;

public interface DireccionService {
	
	public void actualizarDireccion(Direccion direccion);

	public Direccion crearDireccion (Direccion direccion);
	
	public void deleteDireccionById(Long id);
	
	public Optional<Direccion> findDireccionById(Long id);
}
