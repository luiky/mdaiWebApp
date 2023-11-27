package com.mdai.webApp.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;

@Controller
public class HomeController {

	@GetMapping("/holaTh")
	public String holaPage (Model model) {
		String texto = "Hola mundo con Thymeleaf + Spring";
		model.addAttribute("Bienvenida",texto);
		return "holaTh";
	}


	@GetMapping("/")
	public String index() {
		System.out.println("\t Recogo la peticion. "
				+ "Devuelvo la vista index; "
				+ "index.html esta ubicado en Templates");
		return "index";
	}


	public HomeController() {
		// TODO Auto-generated constructor stub
		System.out.println("\t Builder of " + this.getClass().getSimpleName());
	}

}
