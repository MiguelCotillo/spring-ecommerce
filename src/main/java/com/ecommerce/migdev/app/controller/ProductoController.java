package com.ecommerce.migdev.app.controller;

import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.migdev.app.model.Producto;
import com.ecommerce.migdev.app.model.Usuario;
import com.ecommerce.migdev.app.service.ProductoService;
import com.ecommerce.migdev.app.service.UploadFileService;

@Controller
@RequestMapping("productos")
public class ProductoController {
	
	private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);
	
	@Autowired
	private ProductoService serv;
	
	@Autowired
	private UploadFileService i;

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
	public String guardar( Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
		LOGGER.info("Este es el objeto producto {} ", producto);
		Usuario u = new Usuario(1, "", "", "", "", "", "", "");
		producto.setUsuario(u);
		
		if (producto.getId() == null) { 
			String nImagen = i.guardarImagen(file);
			producto.setImagen(nImagen);
		}else {
			if (file.isEmpty()) {
				Producto p = new Producto();
				p = serv.Obtener(producto.getId()).get();
				producto.setImagen(p.getImagen());
			}else {
				String nImagen = i.guardarImagen(file);
				producto.setImagen(nImagen);
			}
		}
		
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
	
	@GetMapping("/eliminar/{id}")
	public String eliminar(@PathVariable Integer id) {
		serv.eliminar(id);
		return "redirect:/productos";
	}
	
}
