package com.example.webserviceEjercicio.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.Optional;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.webserviceEjercicio.models.Productos;
import com.example.webserviceEjercicio.repositories.ProductoRepository;
import com.example.webserviceEjercicio.exception.BadRequestException;
import com.example.webserviceEjercicio.exception.ResourceNotFoundException;


// Se utiliza para integrar Mockito con JUnit 5. Hace que el marco de pruebas sepa que utilizará objetos simulados (mocks) sin necesidad de interactuar con la base de datos.
@ExtendWith(MockitoExtension.class)

public class ProductoServiceMockito_Test {

    // Esta anotación se usa para simular la clase ProductoRepository. De esta forma, no se realiza ninguna interacción real con la base de datos.
    @Mock
    private ProductoRepository productoRepository;

    // Inyecta los mocks definidos con @Mock en el objeto productoService para que se pueda probar.
    @InjectMocks
    private ProductoService productoService;

    private Productos productoEjemplo;

    // Método que se ejecuta antes de cada prueba. Se usa para preparar los datos de prueba.
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        productoEjemplo = new Productos("Laptop", "Laptop potente", 1200.50, 10);
    }

    // Simula que el método findAll() de productoRepository devuelve una lista con un solo producto y luego, se verifica que la lista de productos no esté vacía y que contenga un solo elemento.
    @Test
    void testObtenerTodosLosProductos() {
        when(productoRepository.findAll()).thenReturn(Arrays.asList(productoEjemplo));

        List<Productos> productos = productoService.listaProductos();
        assertFalse(productos.isEmpty());
        assertEquals(1, productos.size());
    }

    // Simula que el método findById("1") de productoRepository devuelve un producto con ID "1" y se verifica que el producto obtenido no sea nulo y que su nombre sea "Laptop".
    @Test
    void testObtenerProductoPorId() {
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoEjemplo));

        Productos producto = productoService.infoProducto("1");
        assertNotNull(producto);
        assertEquals("Laptop", producto.getNombre());
    }

    // Simula que al buscar un producto con un ID que no existe ("ID_NO_EXISTE"), el repositorio devuelve un Optional.empty() y se verifica que se lance la excepción ResourceNotFoundException.
    @Test
    void testObtenerProductoPorId_NoExiste() {
        when(productoRepository.findById("ID_NO_EXISTE")).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> {
            productoService.infoProducto("ID_NO_EXISTE");
        });
    }

    // Simula que el método save() de productoRepository guarda un nuevo producto y se crea un nuevo producto y se verifica que el producto guardado no sea nulo y que su nombre sea "Laptop".
    @Test
    void testCrearProducto() {
        when(productoRepository.save(any(Productos.class))).thenReturn(productoEjemplo);

        Productos nuevoProducto = new Productos("Mouse", "Mouse gamer", 45.99, 5);
        Productos guardado = productoService.nuevoProducto(nuevoProducto);

        assertNotNull(guardado);
        assertEquals("Laptop", guardado.getNombre());
    }

    // Se crea un producto con campos inválidos (nombre vacío y precio negativo) y se verifica que se lance una excepción BadRequestException al intentar guardarlo.
    @Test
    void testCrearProducto_CamposInvalidos() {
        Productos nuevoProducto = new Productos("", "", -10.0, -5);
        assertThrows(BadRequestException.class, () -> productoService.nuevoProducto(nuevoProducto));
    }

    // Simula que se actualiza un producto existente. Primero, se simula que findById("1") devuelve un producto, y luego, que save() guarda el producto modificado y se verifica que el nombre del producto actualizado sea "Laptop Modificada".
    @Test
    void testActualizarProducto() {
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoEjemplo));
        when(productoRepository.save(any(Productos.class))).thenReturn(productoEjemplo);

        Productos productoModificado = new Productos("Laptop Modificada", "Mejorada", 1300.75, 8);
        Productos actualizado = productoService.actualizarProducto("1", productoModificado);

        assertEquals("Laptop Modificada", actualizado.getNombre());
    }

    // Simula que al intentar actualizar un producto con un ID que no existe, el método findById devuelve un Optional.empty() y se verifica que se lance una excepción ResourceNotFoundException.
    @Test
    void testActualizarProducto_NoExiste() {
        when(productoRepository.findById("ID_NO_EXISTE")).thenReturn(Optional.empty());

        Productos productoModificado = new Productos("No Existe", "Descripcion", 500, 2);
        assertThrows(ResourceNotFoundException.class, () -> productoService.actualizarProducto("ID_NO_EXISTE", productoModificado));
    }

    // Simula la eliminación de un producto. Se verifica que el método deleteById del repositorio se llame una vez con el ID "1".
    @Test
    void testEliminarProducto() {
        when(productoRepository.findById("1")).thenReturn(Optional.of(productoEjemplo));
        doNothing().when(productoRepository).deleteById("1");

        productoService.eliminarProducto("1");

        verify(productoRepository, times(1)).deleteById("1");
    }
}
