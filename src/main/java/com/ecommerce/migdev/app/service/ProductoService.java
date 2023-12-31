package com.ecommerce.migdev.app.service;

import java.util.List;
import java.util.Optional;

import com.ecommerce.migdev.app.model.Producto;

public interface ProductoService {
	public List<Producto> Listar();
	public Producto guardar(Producto producto);
	public Optional<Producto> Obtener(Integer id);
	public void editar(Producto producto);
	public void eliminar(Integer id);
}
