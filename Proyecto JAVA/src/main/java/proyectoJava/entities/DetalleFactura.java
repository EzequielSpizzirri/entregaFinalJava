package proyectoJava.entities;

import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "detalles_factura")
public class DetalleFactura {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private int id;

    @NonNull
    @ManyToOne // Relación muchos a uno con Producto
    @JoinColumn(name = "producto_id") // Nombre de la columna que referencia a Producto
    private Producto producto;
    @NonNull
    private int cantidad; // Cantidad del producto en la factura

    // Constructor
    public DetalleFactura() { // Constructor vacío requerido por JPA
    }

    public DetalleFactura(Producto producto, int cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
}