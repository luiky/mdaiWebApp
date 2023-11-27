package com.mdai.webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mdai.webApp.entities.Direccion;
import com.mdai.webApp.entities.Usuario;
import com.mdai.webApp.repositories.UsuarioRepository;
import com.mdai.webApp.services.DireccionService;
import com.mdai.webApp.services.UsuarioService;


@Controller
public class DireccionController {
	
	
	private final DireccionService direccionService;
	private final UsuarioService usuarioService;

	public DireccionController(DireccionService direccionService, UsuarioService usuarioService) {
		this.direccionService = direccionService;
		// TODO Auto-generated constructor stub
		this.usuarioService = usuarioService;
	}
	

	
	 /**
    ADD DIRECCION     
   */
    // Metodo para mostrar el formulario de aniadir usuario
	@GetMapping("/addDireccion/{usuarioId}")
	public String mostrarFormularioAddDireccion(@PathVariable Long usuarioId, Model model) {
	    // Logica para mostrar el formulario de aniadir direccion
		System.out.println("DireccionController "+ " @GetMapping(\"/addDireccion/{usuarioId}\")");
		
		model.addAttribute("usuario",usuarioService.findUsuarioById(usuarioId).get() );
		
	    return "addDireccion";
	}

    // Método para procesar la solicitud de aniadir usuario
    @PostMapping("/guardarDireccion/{usuarioId}")
    public String guardarDireccion(@PathVariable Long usuarioId, Direccion direccion, Model model) {
        // Aqui deberias implementar la lógica para guardar la direccion en tu base de datos o servicio
        // Podemos asumir que tienes un servicio llamado direccionService para gestionar las direcciones
    	System.out.println("\tDireccionController" + "@PostMapping(\"/guardarDireccion\")");
    	
        // Asociar la direccion al usuario
    	direccion.setUsuario(usuarioService.findUsuarioById(usuarioId).get());
    	// Persistir la direccion.  
    	direccionService.crearDireccion(direccion);;       
    	
        // Redirigir a la lista de usuarios despues de agregar uno nuevo. Se mostrara la nueva direccion para el usuario.
        return "redirect:/listarUsuarios";
    }
    
    /**
     * REMOVE USUARIO
     * 
     */
    
    @DeleteMapping ("deleteDireccion/{id}")
    public String deleteDireccion (@PathVariable Long id) {
    	System.out.println("\t direccionController::deleteDireccion");
    	direccionService.deleteDireccionById(id);
    	
    	return "redirect:/listarUsuarios";
    }
    
    
    /**
     * ACTUALIZAR DIRECCION
     * 
     */
    
    
    // Metodo para mostrar el formulario de actualización de usuario
//    http://localhost:8080/actualizarDireccion/1?usuarioId=1    
    @GetMapping("/actualizarDireccion/{id}")
    public String mostrarFormularioActualizarDireccion(@PathVariable Long id, @RequestParam("usuarioId") Long usuarioId , Model model) {
    	
    	System.out.println("\t @GetMapping DireccionController::mostrarFormularioActualizarDireccion");
    	
    	//para acceder a la direccion en la vista.
    	model.addAttribute("direccion",direccionService.findDireccionById(id).get() );
    	
    	System.out.println("@RequestParam Long usuarioId: " + usuarioId);    	
    	model.addAttribute("usuario",usuarioService.findUsuarioById(usuarioId).get());    	
    	return "actualizarDireccion";
    	
    }
    
    // Método para procesar la solicitud de actualización de usuario
    @PostMapping("/actualizarDireccion/{id}")
    public String actualizarDireccion(@PathVariable Long id, @ModelAttribute Usuario usuario, @ModelAttribute Direccion direccion ) {
    	
    	System.out.println("\t @PostMapping DireccionController::actualizarDireccion");
    	
    	System.out.println(direccion.toString());
    	
    	// le asociamos nuevamente el usuario?
    	direccion.setUsuario(usuario);

        direccionService.actualizarDireccion(direccion);
        return "redirect:/listarUsuarios";
    	
    }

}
