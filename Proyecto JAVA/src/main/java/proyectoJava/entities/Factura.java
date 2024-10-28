package proyectoJava.entities;

import javax.persistence.*;

import org.springframework.lang.NonNull;

import java.util.List;

@Entity
@Table(name = "facturas")
public class Factura {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;
    @NonNull
    @ManyToOne // Relación muchos a uno con Cliente
    @JoinColumn(name = "cliente_id") // Nombre de la columna que referencia a Cliente
    private Cliente cliente;
    @NonNull
    @OneToMany(mappedBy = "factura", cascade = CascadeType.ALL) // Relación uno a muchos con DetalleFactura
    private List<DetalleFactura> detalles;

    // Constructor
    public Factura() { // Constructor vacío requerido por JPA
    }

    public Factura(Cliente cliente, List<DetalleFactura> detalles) {
        this.cliente = cliente;
        this.detalles = detalles;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<DetalleFactura> getDetalles() {
        return detalles;
    }

    public void setDetalles(List<DetalleFactura> detalles) {
        this.detalles = detalles;
    }
}
