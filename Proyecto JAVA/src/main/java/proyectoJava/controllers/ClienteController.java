package proyectoJava.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import proyectoJava.dtos.ClienteDTO;
import proyectoJava.entities.Cliente;
import proyectoJava.exceptions.ResourceNotFoundException;
import proyectoJava.services.ClienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Clientes", description = "Operaciones relacionadas con los clientes")
@RestController
@RequestMapping("/api/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @Operation(summary = "Obtener todos los clientes", description = "Devuelve una lista de todos los clientes registrados")
    @ApiResponse(responseCode = "200", description = "Operación exitosa")
    @GetMapping
    public ResponseEntity<List<Cliente>> obtenerTodosLosClientes() {
        List<Cliente> clientes = clienteService.obtenerTodosLosClientes();
        return new ResponseEntity<>(clientes, HttpStatus.OK);
    }

    @Operation(summary = "Obtener cliente por ID", description = "Devuelve un cliente específico basado en el ID proporcionado")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente encontrado"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @GetMapping("/{id}")
    public ResponseEntity<ClienteDTO> obtenerClientePorId(
        @Parameter(description = "ID del cliente a obtener", required = true)
        @PathVariable Long id) {
        Cliente cliente = clienteService.obtenerClientePorId(id);
        ClienteDTO clienteDTO = clienteService.convertirAClienteDTO(cliente);
        return ResponseEntity.ok(clienteDTO);
    }

    @Operation(summary = "Crear un nuevo cliente", description = "Crea un nuevo cliente en el sistema")
    @ApiResponse(responseCode = "201", description = "Cliente creado exitosamente")
    @PostMapping
    public ResponseEntity<Cliente> crearCliente(
        @Parameter(description = "Datos del nuevo cliente", required = true)
        @RequestBody Cliente cliente) {
        Cliente nuevoCliente = clienteService.crearCliente(cliente);
        return new ResponseEntity<>(nuevoCliente, HttpStatus.CREATED);
    }

    @Operation(summary = "Actualizar cliente", description = "Actualiza la información de un cliente existente")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "Cliente actualizado exitosamente"),
        @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @PutMapping("/{id}")
    public ResponseEntity<Cliente> actualizarCliente(
        @Parameter(description = "ID del cliente a actualizar", required = true)
        @PathVariable Long id,
        @Parameter(description = "Datos actualizados del cliente", required = true)
        @RequestBody Cliente clienteActualizado) {
        try {
            Cliente cliente = clienteService.actualizarCliente(id, clienteActualizado);
            return new ResponseEntity<>(cliente, HttpStatus.OK);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @Operation(summary = "Eliminar cliente", description = "Elimina un cliente basado en el ID proporcionado")
    @ApiResponses({
    @ApiResponse(responseCode = "204", description = "Cliente eliminado exitosamente"),
    @ApiResponse(responseCode = "404", description = "Cliente no encontrado")
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCliente(
        @Parameter(description = "ID del cliente a eliminar", required = true)
        @PathVariable Long id) {
        try {
            clienteService.eliminarCliente(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (ResourceNotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
