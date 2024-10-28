package proyectoJava.services;

import proyectoJava.Ext.ClienteExternoService;
import proyectoJava.dtos.ClienteDTO;
import proyectoJava.entities.Cliente;
import proyectoJava.exceptions.ResourceNotFoundException;
import proyectoJava.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    
    private final ClienteExternoService clienteExternoService;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository, ClienteExternoService clienteExternoService) {
        this.clienteRepository = clienteRepository;
        this.clienteExternoService = clienteExternoService; // Inyección de ClienteExternoService
    }

    public Optional<Cliente> obtenerClientePorIdExt(Long id) { // Cambiado a Long
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            // Obtener datos adicionales desde la API externa
            Optional<String> detallesExterno = clienteExternoService.obtenerDetallesClienteExterno(id.intValue()); // Conversión a int
            if (detallesExterno.isPresent()) {
                System.out.println("Detalles adicionales obtenidos de la API externa: " + detallesExterno.get());
            }
        }

        return cliente; // Asegúrate de devolver el cliente
    }

    public ClienteDTO convertirAClienteDTO(Cliente cliente) {
        return new ClienteDTO(cliente.getNombre(), cliente.getDireccion(), cliente.getTelefono());
    }
    
    public Cliente convertirDesdeDTO(ClienteDTO clienteDTO) {
        Cliente cliente = new Cliente();
        cliente.setNombre(clienteDTO.getNombre());
        cliente.setDireccion(clienteDTO.getDireccion());
        cliente.setTelefono(clienteDTO.getTelefono());
        return cliente;
    }

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> obtenerTodosLosClientes() {
        return clienteRepository.findAll();
    }

    public Cliente obtenerClientePorId(Long id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);
        if (cliente.isPresent()) {
            return cliente.get();
        } else {
            throw new ResourceNotFoundException("Cliente no encontrado con ID: " + id);
        }
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente clienteActualizado) {
        Cliente cliente = obtenerClientePorId(id);
        cliente.setNombre(clienteActualizado.getNombre());
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        Cliente cliente = obtenerClientePorId(id);
        clienteRepository.delete(cliente);
    }
}