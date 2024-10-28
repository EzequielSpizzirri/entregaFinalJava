package proyectoJava.dtos;

import org.springframework.lang.NonNull;

public class ClienteDTO {

    @NonNull
    private String nombre;

    @NonNull
    private String direccion;

    @NonNull
    private String telefono;

    // Constructor sin parámetros
    public ClienteDTO() {}

    // Constructor con parámetros
    public ClienteDTO(String nombre, String direccion, String telefono) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.telefono = telefono;
    }

    // Getters y Setters
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
