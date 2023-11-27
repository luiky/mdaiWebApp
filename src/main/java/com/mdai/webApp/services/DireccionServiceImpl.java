package com.mdai.webApp.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.mdai.webApp.entities.Direccion;
import com.mdai.webApp.repositories.DireccionRepository;

@Service
public class DireccionServiceImpl implements DireccionService {

	private final DireccionRepository direccionRepository;
	
	public DireccionServiceImpl(DireccionRepository direccionRepository) {
		
		this.direccionRepository = direccionRepository;
	}

	@Override
	public Direccion crearDireccion(Direccion direccion) {

		return direccionRepository.save(direccion);
		
	}

	@Override
	public void deleteDireccionById(Long id) {
		
		direccionRepository.deleteById(id);
		
	}

	@Override
	public Optional<Direccion> findDireccionById(Long id) {

		return direccionRepository.findById(id);
	}

	@Override
	public void actualizarDireccion(Direccion direccion) {
		
		Direccion auxDir= direccionRepository.save(direccion);
		
	}

}
