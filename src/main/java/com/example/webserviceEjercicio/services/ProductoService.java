package com.example.webserviceEjercicio.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.repositories.ProductoRepository;

import com.example.webserviceEjercicio.exception.ResourceNotFoundException;

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
     public Productos nuevoProducto(Productos nuevoProducto){
        return productoRepository.save(nuevoProducto);
    }

    // Actualizar un producto
    public Productos actualizarProducto(String id, Productos productoActualizado) {
        Productos productoExistente = productoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

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
