package proyectoJava.repositories;

import proyectoJava.entities.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {
}