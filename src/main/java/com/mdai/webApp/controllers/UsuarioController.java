package com.mdai.webApp.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mdai.webApp.entities.Usuario;
import com.mdai.webApp.services.UsuarioService;

@Controller
public class UsuarioController {
	
	
	@Autowired
	private final UsuarioService usuarioService;
	
	@GetMapping("/listarUsuarios")
	public String listarUsuariosPage(Model model) {
		System.out.println("@GetMapping(\"/listarUsuarios\")");		
		
		model.addAttribute("listaUsuarios",usuarioService.findAllUsers());
		return "listarUsuariosChatGPT";
	}
		
	public UsuarioController(UsuarioService usuarioService) {
		// TODO Auto-generated constructor stub
		System.out.println("\t Builder of " + this.getClass().getSimpleName());
		this.usuarioService=usuarioService;
	}
	
	 /**
    ADD USUARIO     
   */
    // Método para mostrar el formulario de añadir usuario
    @GetMapping("/addUsuario")
    public String mostrarFormularioAddUsuario() {
    	System.out.println("@GetMapping(\"/addUsuario\")");
        return "addUsuario"; // Esto coincide con el nombre de tu plantilla Thymeleaf (addUsuario.html)
    }

    // Método para procesar la solicitud de añadir usuario
    @PostMapping("/guardarUsuario")
    public String guardarUsuario(Usuario usuario, Model model) {
        // Aquí deberías implementar la lógica para guardar el usuario en tu base de datos o servicio
        // Podemos asumir que tienes un servicio llamado usuarioService para gestionar los usuarios
    	System.out.println("@PostMapping(\"/guardarUsuario\")");
    	usuarioService.crearUsuario(usuario);
        // Redirigir a la lista de usuarios después de agregar uno nuevo
        return "redirect:/listarUsuarios";
    }
    
    /**
     * REMOVE USUARIO
     * 
     */
    
    @DeleteMapping("/deleteUsuario/{id}")
    public String deleteUsuario(@PathVariable Long id) {
    	System.out.println("\t usuarioController::deleteUsuario");
    	usuarioService.deleteUsuarioById(id);
    	// Redirigir a la lista de usuarios despues de borrarlo
        return "redirect:/listarUsuarios";
    }
    
    
    /**
     * Actualizar USUARIO
     * 
     */
    
    // Metodo para mostrar el formulario de actualización de usuario
    @GetMapping("/actualizarUsuario/{id}")
    public String mostrarFormularioActualizarUsuario(@PathVariable Long id, Model model) {
    	System.out.println("\t UsuarioController::mostrarFormularioActualizarUsuario");
    	
    	//para acceder al usuario en la vista
    	model.addAttribute("usuario",usuarioService.findUsuarioById(id).get() );
    	return "actualizarUsuario";
    	
    }
    
    // Método para procesar la solicitud de actualización de usuario
    @PostMapping("/actualizarUsuario/{id}")
    public String actualizarUsuario(@PathVariable Long id, @ModelAttribute Usuario usuario) {
    	
    	// Podemos asumir que tienes un servicio llamado usuarioService para gestionar los usuarios
    	//usuario viene sin direcciones.
//    	ChatGPT Explicacion:
//    	Si el objeto Usuario tiene una lista de direcciones, pero no se incluyen campos correspondientes 
//    	a la lista de direcciones en el formulario de actualización de usuario, la lista de direcciones 
//    	en el objeto Usuario recibido por el controlador será null. 
//    	Sin embargo, al realizar la operación de actualización y guardar en el repositorio, la lista de direcciones no se actualizará a null.
//
//    	La razón es que la vinculación automática (@ModelAttribute) solo afecta a las propiedades del objeto 
//    	que tienen campos correspondientes en el formulario. 
//    	Si no hay campos en el formulario para la lista de direcciones, 
//    	esa propiedad en el objeto Usuario permanecerá null si no se inicializa explícitamente.
        usuarioService.actualizarUsuario(usuario);
        return "redirect:/listarUsuarios";
    	
    }
    

    

}
