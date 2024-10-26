package proyectoJava.services;

import proyectoJava.Ext.ClienteExternoService;
import proyectoJava.entities.Cliente;
import proyectoJava.exceptions.ResourceNotFoundException;
import proyectoJava.repositories.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

      public Optional<Cliente> obtenerClientePorId(int id) {
        Optional<Cliente> cliente = clienteRepository.findById(id);

        if (cliente.isPresent()) {
            // Obtener datos adicionales desde la API externa
            Optional<String> detallesExterno = ClienteExternoService.obtenerDetallesClienteExterno(id);
            if (detallesExterno.isPresent()) {
                System.out.println("Detalles adicionales obtenidos de la API externa: " + detallesExterno.get());
            }
        }

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