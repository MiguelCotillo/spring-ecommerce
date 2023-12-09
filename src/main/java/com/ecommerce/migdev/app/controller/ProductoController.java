package com.ecommerce.migdev.app.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	public String show(Model model) {
		model.addAttribute("productos", serv.Listar());
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
	
	@GetMapping("/edit/{id}")
	public String editar(@PathVariable Integer id, Model model) {
		Producto producto = new Producto();
		Optional<Producto> opProducto = serv.Obtener(id);
		producto = opProducto.get();
		LOGGER.info("Producto Guardado {} ", producto);
		model.addAttribute("producto", producto);
		
		return "productos/edit";
	}
	
	@PostMapping("/actualizar")
	public String actualizar(Producto producto) {
		serv.editar(producto);
		return "redirect:/productos";
	}
	
}
