package proyectoJava.services;

import proyectoJava.entities.DetalleFactura;
import proyectoJava.exceptions.ResourceNotFoundException;
import proyectoJava.repositories.DetalleFacturaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DetalleFacturaService {

    @Autowired
    private DetalleFacturaRepository detalleFacturaRepository;

    public List<DetalleFactura> obtenerTodosLosDetalles() {
        return detalleFacturaRepository.findAll();
    }

    public DetalleFactura obtenerDetallePorId(Long id) {
        Optional<DetalleFactura> detalle = detalleFacturaRepository.findById(id);
        if (detalle.isPresent()) {
            return detalle.get();
        } else {
            throw new ResourceNotFoundException("Detalle de factura no encontrado con ID: " + id);
        }
    }

    public DetalleFactura crearDetalle(DetalleFactura detalle) {
        return detalleFacturaRepository.save(detalle);
    }

    public DetalleFactura actualizarDetalle(Long id, DetalleFactura detalleActualizado) {
        DetalleFactura detalle = obtenerDetallePorId(id);
        detalle.setCantidad(detalleActualizado.getCantidad());
        detalle.setProducto(detalleActualizado.getProducto());
        return detalleFacturaRepository.save(detalle);
    }

    public void eliminarDetalle(Long id) {
        DetalleFactura detalle = obtenerDetallePorId(id);
        detalleFacturaRepository.delete(detalle);
    }
}
