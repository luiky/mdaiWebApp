package com.mdai.webApp.commandLineRunner;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.mdai.webApp.entities.Direccion;
import com.mdai.webApp.entities.Usuario;
import com.mdai.webApp.repositories.DireccionRepository;
import com.mdai.webApp.repositories.UsuarioRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class MyCommandLineRunner implements CommandLineRunner {
	
	private final UsuarioRepository usuarioRepository;
	private final DireccionRepository direccionRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(MyCommandLineRunner.class);

	public MyCommandLineRunner(UsuarioRepository usuarioRepository, DireccionRepository direccionRepository) {
		// TODO Auto-generated constructor stub
		//System.out.println("\t MyCommandLineRunner Builder ");
		this.usuarioRepository=usuarioRepository;
		this.direccionRepository=direccionRepository;
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("\t MyCommandLineRunner is running! ");
		poblarBD();
	}
	private void poblarBD() {
		//Dos usuarios, luiky y lidia, luiky con dos direcciones, lidia con una.
		/** usuario LUIKY con dos direcciones **/
		Usuario uLuiky = new Usuario();
		uLuiky.setName("Luiky");
		uLuiky.setEmail("luiky@unex.es");

		Direccion d = new Direccion ();
		d.setCalle("Plaza"); d.setCiudad("Caceres");d.setCodigoPostal(10002);
		d.setUsuario(uLuiky);

		Direccion d1 = new Direccion ();
		d1.setCalle("Calle"); d1.setCiudad("Coria");d1.setCodigoPostal(10800);		
		d1.setUsuario(uLuiky);	

		//usando ArrayList
		List<Direccion> direccionesLuiky= new ArrayList<Direccion>();
		direccionesLuiky.add(d);
		direccionesLuiky.add(d1);
		uLuiky.setDirecciones(direccionesLuiky);


		uLuiky=usuarioRepository.save(uLuiky);
		d=direccionRepository.save(d);
		d1=direccionRepository.save(d1);


		/** Usuuario LIDIA con una direccion **/
		Usuario uLidia = new Usuario();
		uLidia.setName("Lidia");		
		uLidia.setEmail("lidia@unex.es");
		Direccion dirLidia = new Direccion ();
		dirLidia.setCalle("Carrer"); dirLidia.setCiudad("Sabadell"); dirLidia.setCodigoPostal(28756);
		dirLidia.setUsuario(uLidia);
				
		List<Direccion> direccionesLidia = new ArrayList<Direccion>();
		direccionesLidia.add(dirLidia);
		uLidia= usuarioRepository.save(uLidia);
		dirLidia=direccionRepository.save(dirLidia);
		
		//registro en el logger el contenido de los usuarios guardados., y los muestro directamente.
		usuarioRepository.findAll().forEach((usuario) -> {
		    logger.info("{}", usuario.toString());
		});
		

	}

}
