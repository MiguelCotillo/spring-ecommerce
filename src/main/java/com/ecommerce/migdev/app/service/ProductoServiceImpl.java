package com.ecommerce.migdev.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.migdev.app.model.Producto;
import com.ecommerce.migdev.app.repository.ProductoRepository;

@Service
public class ProductoServiceImpl implements ProductoService {

	@Autowired
	private ProductoRepository repo;
	
	@Override
	public Producto guardar(Producto producto) {
		return repo.save(producto);
	}

	@Override
	public Optional<Producto> Obtener(Integer id) {
		return repo.findById(id);
	}

	@Override
	public void editar(Producto producto) {
		repo.save(producto);
	}

	@Override
	public void eliminar(Integer id) {
		repo.deleteById(id);
	}

	@Override
	public List<Producto> Listar() {
		return repo.findAll();
	}

}
