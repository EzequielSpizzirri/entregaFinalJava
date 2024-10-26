package proyectoJava.entities;
import javax.persistence.*;

import org.springframework.lang.NonNull;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID
    private int id;

    @Column(name = "nombre") // Mapeo de columna
    @NonNull
    private String nombre;

    @NonNull
    @Column(name = "direccion") // Mapeo de columna
    private String direccion;

    @NonNull
    @Column(name = "telefono") // Mapeo de columna
    private String telefono;

    // Constructor
    public Cliente() { // Constructor vacío requerido por JPA
    }

    public Cliente(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
}