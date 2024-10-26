package proyectoJava.services;

import proyectoJava.entities.Factura;
import proyectoJava.exceptions.ResourceNotFoundException;
import proyectoJava.repositories.FacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FacturaService {

    @Autowired
    private FacturaRepository facturaRepository;

    public List<Factura> obtenerTodasLasFacturas() {
        return facturaRepository.findAll();
    }

    public Factura obtenerFacturaPorId(Long id) {
        Optional<Factura> factura = facturaRepository.findById(id);
        if (factura.isPresent()) {
            return factura.get();
        } else {
            throw new ResourceNotFoundException("Factura no encontrada con ID: " + id);
        }
    }

    public Factura crearFactura(Factura factura) {
        return facturaRepository.save(factura);
    }

    public Factura actualizarFactura(Long id, Factura facturaActualizada) {
        Factura factura = obtenerFacturaPorId(id);
        factura.setCliente(facturaActualizada.getCliente());
        factura.setDetalles(facturaActualizada.getDetalles());
        return facturaRepository.save(factura);
    }

    public void eliminarFactura(Long id) {
        Factura factura = obtenerFacturaPorId(id);
        facturaRepository.delete(factura);
    }
}
