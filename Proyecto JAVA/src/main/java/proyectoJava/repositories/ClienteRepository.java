package proyectoJava.repositories;

import proyectoJava.entities.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository; 

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
