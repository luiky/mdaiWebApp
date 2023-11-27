package com.mdai.webApp.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mdai.webApp.entities.Direccion;
import com.mdai.webApp.entities.Usuario;
import com.mdai.webApp.repositories.DireccionRepository;
import com.mdai.webApp.repositories.UsuarioRepository;

@Service
public class UsuarioServiceImpl implements UsuarioService {

	private final UsuarioRepository usuarioRepository;
	private final DireccionRepository direccionRepository;

	@Autowired //No es necesario, pero ayuda a recordarlo
	public UsuarioServiceImpl(UsuarioRepository usuarioRepository, DireccionRepository direccionRepository) {
		// TODO Auto-generated constructor stub
		System.out.println("\t Constructor UsuarioServiceImpl");
		this.usuarioRepository=usuarioRepository;
		this.direccionRepository = direccionRepository;
				
	}
	
	@Override
	public List<Usuario> findAllUsers() {
				
		return (List<Usuario>) usuarioRepository.findAll();
	}

	@Override
	public void crearUsuario(Usuario usuario) {
		usuario=usuarioRepository.save(usuario);
	}

	@Override
	public Optional<Usuario> findUsuarioById(Long usuarioId) {
		return usuarioRepository.findById(usuarioId);
	}

	@Override
	public void deleteUsuarioById(Long usuarioId) {
		//Algo de logica de control, de como ha ido la operacion, no estaria mal.
		Usuario usuario= usuarioRepository.findById(usuarioId).get();
		for (Direccion d : usuario.getDirecciones() ) {
			d.setUsuario(null);
			direccionRepository.delete(d);
		}		
		usuarioRepository.deleteById(usuarioId);		
	}

	@Override
	public void actualizarUsuario(Usuario usuario) {
		
		//Algo de logica de control, de como ha ido la operacion, no estaria mal.
		//recordad el usuario viene sin direcciones, pero solo se van a actualizar los campos actualizados. 
		//Hibernate salida sql: Hibernate: update usuario set email=?,name=? where id=?
		usuarioRepository.save(usuario);
		
		// ---- otra opcion ---
		//podemos pedir al repositorio el usuario existente que comparte ese usuario.id y actualizar sus campos, por si en algún caso es necesario:
			// Obtener el usuario existente de la base de datos
			//		Usuario usuarioExistente = usuarioRepository.findById(usuario.getId());
			//
			//		// Actualizar solo los campos que se pueden actualizar
			//		usuarioExistente.setNombre(usuario.getNombre());
			//		usuarioExistente.setEmail(usuario.getEmail());
			//
			//	   usuarioRepository.save(usuarioExistente);

	}
	
	
}
