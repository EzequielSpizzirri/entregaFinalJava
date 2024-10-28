package proyectoJava.entities;

import javax.persistence.*;
import org.springframework.lang.NonNull;

@Entity
@Table(name = "productos")
public class Producto {
    @NonNull
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private Long id;
    @NonNull
    @Column(name = "nombre") // Mapeo de columna
    private String nombre;
    @NonNull
    @Column(name = "precio") // Mapeo de columna
    private double precio;

    // Constructor
    public Producto() { // Constructor vacío requerido por JPA
    }

    public Producto(String nombre, double precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    // Getters y Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }
}
