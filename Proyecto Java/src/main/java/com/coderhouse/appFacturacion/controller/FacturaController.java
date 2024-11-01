package com.coderhouse.appFacturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.appFacturacion.dto.FacturaDto;
import com.coderhouse.appFacturacion.entity.Factura;
import com.coderhouse.appFacturacion.service.FacturaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
public class FacturaController {

    @Autowired
    FacturaService facturaService;

    @PostMapping("/facturarCompraDto")
    @Operation(summary = "Crear una factura a partir de un DTO de compra", 
               description = "Genera una nueva factura basándose en los datos del DTO proporcionado.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura creada correctamente"),
        @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public Factura facturarCompraDto(@RequestBody FacturaDto compra) {
        return facturaService.facturarCompra(compra);
    }

    @GetMapping("/getFacturasList")
    @Operation(summary = "Obtener lista de facturas", 
               description = "Devuelve una lista de todas las facturas generadas.")
    @ApiResponse(responseCode = "200", description = "Lista de facturas obtenida correctamente")
    public ResponseEntity<List<Factura>> getAllFacturas() {
        List<Factura> facturasList = facturaService.obtenerTodasLasFacturas();
        return ResponseEntity.ok().body(facturasList);
    }

    @GetMapping("/getFacturaById/{id}")
    @Operation(summary = "Obtener factura por ID", 
               description = "Devuelve una factura específica basada en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Factura encontrada"),
        @ApiResponse(responseCode = "404", description = "Factura no encontrada")
    })
    public ResponseEntity<Factura> getFacturaById(@Parameter(description = "ID de la factura a obtener") 
                                                   @PathVariable(value = "id") Long facturaId) {
        Factura factura = facturaService.obtenerFacturaPorId(facturaId);
        return ResponseEntity.ok().body(factura);
    }
}
