package proyectoJava.repositories;

import proyectoJava.entities.DetalleFactura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface DetalleFacturaRepository extends JpaRepository<DetalleFactura, Long> {

}
