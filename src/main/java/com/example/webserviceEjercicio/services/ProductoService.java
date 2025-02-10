package com.example.webserviceEjercicio.services;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.repositories.ProductoRepository;
import com.example.webserviceEjercicio.exception.ResourceNotFoundException;
import com.example.webserviceEjercicio.exception.BadRequestException;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    // Obtener todos los productos
    public List<Productos> listaProductos() {
        return productoRepository.findAll();
    }

    // Obtener un producto por ID
    public Productos infoProducto(String id) {
        return productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));
    }

    // Crear un nuevo producto
    public Productos nuevoProducto(Productos nuevoProducto) {
        if (nuevoProducto.getNombre() == null || nuevoProducto.getNombre().trim().isEmpty() ||
                nuevoProducto.getDescripcion() == null || nuevoProducto.getDescripcion().trim().isEmpty() ||
                (Objects.isNull(nuevoProducto.getPrecio()) || nuevoProducto.getPrecio() <= 0.0) ||
                (Objects.isNull(nuevoProducto.getStock()) || nuevoProducto.getStock() < 0)) {

            throw new BadRequestException("Todos los campos son obligatorios y deben tener valores válidos.");
        }

        return productoRepository.save(nuevoProducto);
    }

    // Actualizar un producto
    public Productos actualizarProducto(String id, Productos productoActualizado) {
        Productos productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        if (productoActualizado.getNombre() == null || productoActualizado.getNombre().trim().isEmpty() ||
                productoActualizado.getDescripcion() == null || productoActualizado.getDescripcion().trim().isEmpty() ||
                (Objects.isNull(productoActualizado.getPrecio()) || productoActualizado.getPrecio() <= 0.0) ||
                (Objects.isNull(productoActualizado.getStock()) || productoActualizado.getStock() < 0)) {

            throw new BadRequestException("Todos los campos son obligatorios y deben tener valores válidos.");
        }

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());

        return productoRepository.save(productoExistente);
    }

    // Eliminar un producto
    public void eliminarProducto(String id) {
        productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productoRepository.deleteById(id);
    }

}
