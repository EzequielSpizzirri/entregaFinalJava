package com.coderhouse.appFacturacion.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.appFacturacion.entity.Empresa;
import com.coderhouse.appFacturacion.service.EmpresaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;

@Data
@RestController
public class EmpresaController {

    @Autowired
    EmpresaService empresaService;

    @GetMapping("/getEmpresaById/{id}")
    @Operation(summary = "Obtener empresa por ID", description = "Devuelve una empresa específica basada en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Empresa encontrada"),
        @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public ResponseEntity<Empresa> getEmpresa(@Parameter(description = "ID de la empresa a obtener") @PathVariable(value = "id") Long empresaId) throws Exception {
        Empresa empresa = empresaService.obtenerEmpresaPorId(empresaId);
        return ResponseEntity.ok().body(empresa);
    }

    @GetMapping("/getEmpresasList")
    @Operation(summary = "Obtener lista de empresas", description = "Devuelve una lista de todas las empresas.")
    @ApiResponse(responseCode = "200", description = "Lista de empresas obtenida correctamente")
    public ResponseEntity<List<Empresa>> getAllEmpresas() {
        List<Empresa> empresaList = empresaService.obtenerTodasLasEmpresas();
        return ResponseEntity.ok().body(empresaList);
    }

    @PostMapping("/createEmpresa")
    @Operation(summary = "Crear nueva empresa", description = "Crea una nueva empresa y devuelve su información.")
    @ApiResponse(responseCode = "200", description = "Empresa creada correctamente")
    public ResponseEntity<Empresa> createEmpresa(@RequestBody Empresa empresa) {
        Empresa nuevaEmpresa = empresaService.crearEmpresa(empresa);
        return ResponseEntity.ok().body(nuevaEmpresa);
    }

    @DeleteMapping("/deleteEmpresa/{id}")
    @Operation(summary = "Eliminar empresa", description = "Elimina una empresa basada en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Empresa eliminada correctamente"),
        @ApiResponse(responseCode = "404", description = "Empresa no encontrada")
    })
    public void deleteEmpresa(@Parameter(description = "ID de la empresa a eliminar") @PathVariable(value = "id") Long empresaId) throws Exception {
        empresaService.borrarEmpresa(empresaId);
    }

}
