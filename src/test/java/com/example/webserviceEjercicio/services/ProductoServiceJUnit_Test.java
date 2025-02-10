package com.example.webserviceEjercicio.services;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.repositories.ProductoRepository;
import com.example.webserviceEjercicio.exception.BadRequestException;
import com.example.webserviceEjercicio.exception.ResourceNotFoundException;

// Este archivo usa una base de datos real para probar la lógica de ProductoService.

// Carga todo el contexto de la aplicación de Spring Boot.
@SpringBootTest

public class ProductoServiceJUnit_Test {


    //Inyectamos las dependencias para interactuar con la base de datos y probar el servicio.
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ProductoService productoService;

    private Productos productoEjemplo;


    // Antes de cada test, guardamos un producto de prueba en la base de datos.
    @BeforeEach
    void setUp() {
        productoEjemplo = new Productos("Laptop", "Laptop potente", 1200.50, 10);
        productoEjemplo = productoRepository.save(productoEjemplo);
    }

    // Borrar todos los productos después de cada test para evitar conflictos entre pruebas.
    @AfterEach
    void tearDown() {
        productoRepository.deleteAll();
    }

    // Que la lista de productos no esté vacía después de haber guardado uno en setUp().
    @Test
    void testObtenerTodosLosProductos() {
        List<Productos> productos = productoService.listaProductos();
        assertFalse(productos.isEmpty());
    }

    // Que infoProducto(id) devuelva un objeto y no sea null y que el producto recuperado tenga el nombre esperado ("Laptop").
    @Test
    void testObtenerProductoPorId() {
        Productos producto = productoService.infoProducto(productoEjemplo.getId());
        assertNotNull(producto);
        assertEquals("Laptop", producto.getNombre());
    }

    // Que si el ID no existe, el servicio lanza una ResourceNotFoundException.
    @Test
    void testObtenerProductoPorId_NoExiste() {
        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.infoProducto("ID_NO_EXISTE");
        });
    }

    // Que al crear un producto nuevo, se le asigna un ID (no es null).
    @Test
    void testCrearProducto() {
        Productos nuevoProducto = new Productos("Mouse", "Mouse gamer", 45.99, 5);
        Productos guardado = productoService.nuevoProducto(nuevoProducto);
        assertNotNull(guardado.getId());
    }

    // Que si los datos son inválidos (nombre vacío, precio negativo), se lanza una BadRequestException.
    @Test
    void testCrearProducto_CamposInvalidos() {
        Productos nuevoProducto = new Productos("", "", -10.0, -5);
        assertThrows(BadRequestException.class, () -> productoService.nuevoProducto(nuevoProducto));
    }

    // Que actualizarProducto(id, datos) modifica correctamente los datos en la base de datos.
    @Test
    void testActualizarProducto() {
        Productos productoModificado = new Productos("Laptop Modificada", "Mejorada", 1300.75, 8);
        Productos actualizado = productoService.actualizarProducto(productoEjemplo.getId(), productoModificado);
        assertEquals("Laptop Modificada", actualizado.getNombre());
    }

    // Que si el producto no existe, se lanza una ResourceNotFoundException.
    @Test
    void testActualizarProducto_NoExiste() {
        Productos productoModificado = new Productos("No Existe", "Descripcion", 500, 2);
        assertThrows(ResourceNotFoundException.class, () -> productoService.actualizarProducto("ID_NO_EXISTE", productoModificado));
    }

    // Que después de llamar a eliminarProducto(id), el producto ya no existe y si intentamos buscarlo, debe lanzar una ResourceNotFoundException.
    @Test
    void testEliminarProducto() {
        productoService.eliminarProducto(productoEjemplo.getId());
        assertThrows(ResourceNotFoundException.class, () -> productoService.infoProducto(productoEjemplo.getId()));
    }
}
