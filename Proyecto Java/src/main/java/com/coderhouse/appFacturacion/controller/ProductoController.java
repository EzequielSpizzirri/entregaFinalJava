package com.coderhouse.appFacturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.appFacturacion.entity.Producto;
import com.coderhouse.appFacturacion.service.ProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class ProductoController {

    @Autowired
    ProductoService productoService;

    @GetMapping("/getProductoByNombre")
    @Operation(summary = "Obtener productos por nombre", 
               description = "Devuelve una lista de productos que coinciden con el nombre proporcionado.")
    public ResponseEntity<List<Producto>> getProductoByNombre(@Parameter(description = "Nombre del producto a buscar") 
                                                                @Param("nombre") String nombre) {
        List<Producto> producto = productoService.obtenerProductosPorNombre(nombre);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/getProductoById/{id}")
    @Operation(summary = "Obtener producto por ID", 
               description = "Devuelve un producto específico basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Producto encontrado"),
        @ApiResponse(responseCode = "404", description = "Producto no encontrado")
    })
    public ResponseEntity<Producto> getProductoById(@Parameter(description = "ID del producto a obtener") 
                                                      @PathVariable(value = "id") Long productoId) throws Exception {
        Producto producto = productoService.obtenerProductoPorId(productoId);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/getProductoByNombreYPlataforma")
    @Operation(summary = "Obtener producto por nombre y plataforma", 
               description = "Devuelve un producto específico basado en el nombre y la plataforma.")
    public ResponseEntity<Producto> getProductoByNombrePlataforma(@Parameter(description = "Nombre del producto a buscar") 
                                                                   @Param("nombre") String nombre, 
                                                                   @Parameter(description = "Plataforma del producto") 
                                                                   @Param("plataforma") String plataforma) throws Exception {
        Producto producto = productoService.obtenerProductoPorNombreYPlataforma(nombre, plataforma);
        return ResponseEntity.ok().body(producto);
    }

    @GetMapping("/getProductos")
    @Operation(summary = "Obtener todos los productos", 
               description = "Devuelve una lista de todos los productos disponibles.")
    public ResponseEntity<List<Producto>> getAllProductos() {
        List<Producto> productoList = productoService.obtenerTodosLosProductos();
        return ResponseEntity.ok().body(productoList);
    }

    @GetMapping("/getProductosPlataforma")
    @Operation(summary = "Obtener productos por plataforma", 
               description = "Devuelve una lista de productos que pertenecen a una plataforma específica.")
    public ResponseEntity<List<Producto>> getAllProductosPlataforma(@Parameter(description = "Plataforma de los productos a buscar") 
                                                                      @Param("plataforma") String plataforma) {
        List<Producto> productoList = productoService.obtenerProductosPorPlataforma(plataforma);
        return ResponseEntity.ok().body(productoList);
    }

    @GetMapping("/getProductosCategoria")
    @Operation(summary = "Obtener productos por categoría", 
               description = "Devuelve una lista de productos que pertenecen a una categoría específica.")
    public ResponseEntity<List<Producto>> getAllProductosCategoria(@Parameter(description = "Categoría de los productos a buscar") 
                                                                   @Param("categoria") String categoria) {
        List<Producto> productoList = productoService.obtenerProductosPorCategoria(categoria);
        return ResponseEntity.ok().body(productoList);
    }

    @PostMapping("/createProducto")
    @Operation(summary = "Crear un nuevo producto", 
               description = "Crea un nuevo producto y devuelve su información.")
    public ResponseEntity<Producto> createProducto(@RequestBody Producto producto) {
        Producto nuevoProducto = productoService.crearProducto(producto);
        return ResponseEntity.ok().body(nuevoProducto);
    }

    @PutMapping("/updatePrecioProducto")
    @Operation(summary = "Actualizar el precio de un producto", 
               description = "Modifica el precio de un producto específico basado en su ID.")
    public void updatePrecioProducto(@Parameter(description = "ID del producto a actualizar") 
                                      @Param("id") Long id, 
                                      @Parameter(description = "Nuevo precio del producto") 
                                      @Param("precio") double precio) {
        productoService.modificarPrecioProducto(id, precio);
    }

    @DeleteMapping("/deleteProducto/{id}")
    @Operation(summary = "Eliminar un producto", 
               description = "Elimina un producto específico basado en su ID.")
    public void deleteProducto(@Parameter(description = "ID del producto a eliminar") 
                                @PathVariable(value = "id") Long productoId) {
        productoService.borrarProducto(productoId);
    }

    @PutMapping("/updateStockProducto")
    @Operation(summary = "Actualizar el stock de un producto", 
               description = "Reduce el stock de un producto específico basado en su ID.")
    public void restarStockProducto(@Parameter(description = "ID del producto a actualizar") 
                                     @Param("id") Long id, 
                                     @Parameter(description = "Cantidad a restar del stock") 
                                     @Param("cant") int cant) {
        productoService.restarStock(id, cant);
    }
}
