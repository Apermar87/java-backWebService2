package com.example.webserviceEjercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.services.ProductoService;

@RestController
@CrossOrigin(origins = "http://localhost:8080")

public class ProductoController {

    @Autowired 
    private ProductoService productoService;

    @GetMapping("/obtenerProductos")
    public List <Productos> obtenerProductos(){
        return productoService.listaProductos();
    }

    @GetMapping("/obtenerProductos/{id}")
    public Productos obtenerProductosId(@PathVariable String id){
        return productoService.infoProducto(id);
    }

    @PostMapping("/crearProducto")
    public Productos crearProducto(@RequestBody Productos crearProducto ){       
        return productoService.nuevoProducto(crearProducto);
    }

    @PutMapping("/modificarProducto/{id}")
    public Productos modificarProducto(@PathVariable String id, @RequestBody Productos productoModificado){
        return productoService.actualizarProducto(id, productoModificado);
    }

    @DeleteMapping("/borrarProducto/{id}")
    public void borrarProducto(@PathVariable String id){
        productoService.eliminarProducto(id);
    }
}
