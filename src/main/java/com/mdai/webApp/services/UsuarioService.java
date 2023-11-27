package com.mdai.webApp.services;

import java.util.List;
import java.util.Optional;

import com.mdai.webApp.entities.Usuario;

public interface UsuarioService {
	
	
	public void actualizarUsuario (Usuario usuario);

	public void crearUsuario(Usuario usuario);
		
	public void deleteUsuarioById(Long usuarioId);
	
	public List <Usuario> findAllUsers();
	
	public Optional<Usuario> findUsuarioById (Long usuarioId);
	
	
}
