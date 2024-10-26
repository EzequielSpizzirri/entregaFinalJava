package proyectoJava.Ext;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.RestClientException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class ClienteExternoService {

    private final RestTemplate restTemplate;

    public ClienteExternoService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    // Método para obtener los detalles adicionales de un cliente desde un servicio externo
    public Optional<String> obtenerDetallesClienteExterno(int idCliente) {
        String url = "https://api.clientesexterna.com/clientes/" + idCliente;

        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            return Optional.ofNullable(response.getBody()); // Devuelve los detalles del cliente
        } catch (RestClientException e) {
            System.out.println("Error al consumir la API externa de cliente: " + e.getMessage());
            return Optional.empty(); // Si hay un error, devuelve vacío
        }
    }
}
