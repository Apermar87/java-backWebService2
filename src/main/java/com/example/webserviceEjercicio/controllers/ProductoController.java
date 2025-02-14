package com.example.webserviceEjercicio.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.services.ProductoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@Api(value = "Productos", tags = "API para gestionar productos")

public class ProductoController {

    @Autowired 
    private ProductoService productoService;

    @ApiOperation(value = "Obtener la lista de todos los productos", response = List.class)
    @GetMapping("/obtenerProductos")
    public List <Productos> obtenerProductos(){
        return productoService.listaProductos();
    }

    @ApiOperation(value = "Obtener un producto por su ID", response = Productos.class)
    @GetMapping("/obtenerProductos/{id}")
    public Productos obtenerProductosId(@PathVariable String id){
        return productoService.infoProducto(id);
    }

    @ApiOperation(value = "Crear un nuevo producto", response = Productos.class)
    @PostMapping("/crearProducto")
    public Productos crearProducto(@RequestBody Productos crearProducto ){       
        return productoService.nuevoProducto(crearProducto);
    }

    @ApiOperation(value = "Modificar un producto existente", response = Productos.class)
    @PutMapping("/modificarProducto/{id}")
    public Productos modificarProducto(@PathVariable String id, @RequestBody Productos productoModificado){
        return productoService.actualizarProducto(id, productoModificado);
    }

    @ApiOperation(value = "Eliminar un producto por su ID")
    @DeleteMapping("/borrarProducto/{id}")
    public void borrarProducto(@PathVariable String id){
        productoService.eliminarProducto(id);
    }
}
