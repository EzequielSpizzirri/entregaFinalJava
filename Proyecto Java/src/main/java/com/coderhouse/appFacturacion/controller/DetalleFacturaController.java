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

import com.coderhouse.appFacturacion.entity.DetalleFactura;
import com.coderhouse.appFacturacion.service.DetalleFacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;

@Data
@RestController
public class DetalleFacturaController {

    @Autowired
    DetalleFacturaService itemService;

    @GetMapping("/getItemById/{id}")
    @Operation(summary = "Obtener detalle de factura por ID", description = "Devuelve un detalle de factura específico basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Detalle de factura encontrado"),
        @ApiResponse(responseCode = "404", description = "Detalle de factura no encontrado")
    })
    public ResponseEntity<DetalleFactura> getItem(@Parameter(description = "ID del detalle de factura a obtener") @PathVariable(value = "id") Long itemId) throws Exception {
        DetalleFactura item = itemService.obtenerDetalleFacturaPorId(itemId);
        return ResponseEntity.ok().body(item);
    }

    @GetMapping("/getItemsList")
    @Operation(summary = "Obtener lista de detalles de factura", description = "Devuelve una lista de todos los detalles de factura.")
    @ApiResponse(responseCode = "200", description = "Lista de detalles de factura obtenida correctamente")
    public ResponseEntity<List<DetalleFactura>> getAllItems() {
        List<DetalleFactura> itemList = itemService.obtenerTodosLosDetalleFactura();
        return ResponseEntity.ok().body(itemList);
    }

    @PostMapping("/createItem")
    @Operation(summary = "Crear un nuevo detalle de factura", description = "Crea un nuevo detalle de factura y devuelve su información.")
    @ApiResponse(responseCode = "200", description = "Detalle de factura creado correctamente")
    public ResponseEntity<DetalleFactura> createItem(@RequestBody DetalleFactura item) {
        DetalleFactura nuevoItem = itemService.crearDetalleFactura(item);
        return ResponseEntity.ok().body(nuevoItem);
    }

    @PutMapping("/updateCantidadItem")
    @Operation(summary = "Actualizar cantidad de detalle de factura", description = "Actualiza la cantidad de un detalle de factura existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cantidad actualizada correctamente"),
        @ApiResponse(responseCode = "404", description = "Detalle de factura no encontrado")
    })
    public void updateCantidadItem(
        @Parameter(description = "ID del detalle de factura a actualizar") @Param("id") Long id, 
        @Parameter(description = "Nueva cantidad") @Param("cant") int cant) throws Exception {
        itemService.modificarCantidadDetalleFacturaById(id, cant);
    }

    @DeleteMapping("/deleteItem/{id}")
    @Operation(summary = "Eliminar detalle de factura", description = "Elimina un detalle de factura basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Detalle de factura eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Detalle de factura no encontrado")
    })
    public void deleteItem(@Parameter(description = "ID del detalle de factura a eliminar") @PathVariable(value = "id") Long itemId) throws Exception {
        itemService.borrarDetalleFactura(itemId);
    }
}
