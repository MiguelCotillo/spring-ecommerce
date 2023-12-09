package com.ecommerce.migdev.app.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ecommerce.migdev.app.model.Producto;
import com.ecommerce.migdev.app.model.Usuario;
import com.ecommerce.migdev.app.service.ProductoService;

@Controller
@RequestMapping("productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService serv;

	@GetMapping("")
	public String show() {
		return "productos/show";
	}
	
	@GetMapping("/agregar")
	public String crear() {
		return "productos/create";
	}
	
	@PostMapping("/guardar")
	public String guardar( Producto producto) {
		LOGGER.info("Este es el objeto producto {} ", producto);
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(u);
		serv.guardar(producto);
		return "redirect:/productos";
	}
}
