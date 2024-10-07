package com.example.webserviceEjercicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class ProductoService {

    @Autowired
    private ProductoRepository productoRepository;

    public List <Productos> listaProductos(){
        return productoRepository.findAll();
    }

    public Optional <Productos> infoProducto(Long id){
        return productoRepository.findById(id);
    }

    public Productos nuevoProducto(Productos nuevoProducto){
        return productoRepository.save(nuevoProducto);
    }

    public Productos actualizarProducto(Long id, Productos productoActualizado ){
        Productos productoExistente = productoRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Producto no encontrado con ID: " + id));

        productoExistente.setNombre(productoActualizado.getNombre());
        productoExistente.setDescripcion(productoActualizado.getDescripcion());
        productoExistente.setPrecio(productoActualizado.getPrecio());
        productoExistente.setStock(productoActualizado.getStock());

        return productoRepository.save(productoExistente);
    }

    public void borrarProducto(){
        
    }

}
