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

import com.coderhouse.appFacturacion.entity.Cliente;
import com.coderhouse.appFacturacion.entity.Factura;
import com.coderhouse.appFacturacion.dto.ClienteDto;
import com.coderhouse.appFacturacion.service.ClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.Data;

@Data
@RestController
public class ClienteController {
    @Autowired
    ClienteService clienteService;

    @GetMapping("/getCliente/id/{id}")
    @Operation(summary = "Obtener un cliente por ID", description = "Devuelve un cliente específico basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public ResponseEntity<Cliente> getCliente(@Parameter(description = "ID del cliente a obtener") @PathVariable(value = "id") Long clienteId) {
        Cliente cliente = clienteService.obtenerClientePorId(clienteId);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping("/getCliente/nombre")
    @Operation(summary = "Obtener un cliente por nombre", description = "Devuelve un cliente específico basado en su nombre.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    public Cliente getClienteByNombre(@Parameter(description = "Nombre del cliente a buscar") @Param("nombre") String nombre) {
        return clienteService.obtenerClientePorNombre(nombre);
    }

    @GetMapping("/getClienteDni")
    @Operation(summary = "Obtener un cliente por DNI", description = "Devuelve un cliente específico basado en su DNI.")
    @ApiResponse(responseCode = "200", description = "Cliente encontrado")
    public Cliente getClienteByDni(@RequestBody Cliente cliente) {
        return clienteService.obtenerClientePorDni(cliente);
    }

    @GetMapping("/getClientes")
    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes.")
    @ApiResponse(responseCode = "200", description = "Lista de clientes obtenida correctamente")
    public ResponseEntity<List<Cliente>> getAllClientes() {
        List<Cliente> clienteList = clienteService.obtenerTodosLosClientes();
        return ResponseEntity.ok().body(clienteList);
    }

    @PostMapping("/createCliente")
    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente y devuelve su información.")
    @ApiResponse(responseCode = "200", description = "Cliente creado correctamente")
    public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return ResponseEntity.ok().body(nuevoCliente);
    }

    @PutMapping("/updateClienteTelefono")
    @Operation(summary = "Actualizar teléfono del cliente", description = "Actualiza el número de teléfono de un cliente existente.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Teléfono actualizado correctamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public void updateClienteTelefono(@Parameter(description = "ID del cliente a actualizar") @Param("id") Long id, @Parameter(description = "Nuevo teléfono") @Param("tel") String tel) {
        clienteService.modificarTelefonoCliente(id, tel);
    }

    @DeleteMapping("/deleteCliente/{id}")
    @Operation(summary = "Eliminar un cliente", description = "Elimina un cliente basado en su ID.")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204", description = "Cliente eliminado correctamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    public void deleteCliente(@Parameter(description = "ID del cliente a eliminar") @PathVariable(value = "id") Long clienteId) {
        clienteService.borrarCliente(clienteId);
    }

    @GetMapping("/getEdadCliente/{id}")
    @Operation(summary = "Obtener la edad de un cliente", description = "Devuelve la edad de un cliente específico basado en su ID.")
    @ApiResponse(responseCode = "200", description = "Edad del cliente obtenida correctamente")
    public ResponseEntity<ClienteDto> getClienteDto(@Parameter(description = "ID del cliente") @PathVariable(value = "id") Long clienteId) {
        ClienteDto clienteDto = clienteService.obtenerEdadClienteDto(clienteId);
        return ResponseEntity.ok().body(clienteDto);
    }

    @GetMapping("/getFacturasPorCliente/{id}")
    @Operation(summary = "Obtener facturas de un cliente", description = "Devuelve todas las facturas asociadas a un cliente.")
    @ApiResponse(responseCode = "200", description = "Facturas obtenidas correctamente")
    public ResponseEntity<List<Factura>> getAllFacturasByCliente(@Parameter(description = "ID del cliente") @PathVariable(value = "id") Long clienteId) {
        List<Factura> facturasCliente = clienteService.obtenerFacturasPorIdCliente(clienteId);
        return ResponseEntity.ok().body(facturasCliente);
    }
}
