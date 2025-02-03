package com.example.webserviceEjercicio;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class ProductoController {

    @Autowired 
    private ProductoService productoService;

    @GetMapping("/obtenerProductos")
    public List <Productos> obtenerProductos(){
        return productoService.listaProductos();
    }

    @GetMapping("/obtenerProductosId")
    public Optional <Productos> obtenerProductosId(@RequestParam("id") Long id){
        return productoService.infoProducto(id);
    }

    @PostMapping("/crearProducto")
    public Productos crearProducto(@RequestBody Productos crearProducto ){       
        return productoService.nuevoProducto(crearProducto);
    }

    @PutMapping("/modificarProducto/{id}")
    public Productos modificarProducto(@PathVariable Long id, @RequestBody Productos productoModificado){
        Productos productoActualizado = productoService.actualizarProducto(id, productoModificado);
        return productoActualizado;
    }

    @DeleteMapping("/borrarProducto/{id}")
    public void borrarProducto(@PathVariable Long id){
        productoService.eliminarProducto(id);
    }
}
