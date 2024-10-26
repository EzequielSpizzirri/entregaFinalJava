package proyectoJava.controllers;

import proyectoJava.entities.DetalleFactura;
import proyectoJava.services.DetalleFacturaService;
import proyectoJava.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/detalles")
public class DetalleFacturaController {

    @Autowired
    private DetalleFacturaService detalleFacturaService;

    @GetMapping
    public List<DetalleFactura> obtenerTodosLosDetalles() {
        return detalleFacturaService.obtenerTodosLosDetalles();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DetalleFactura> obtenerDetallePorId(@PathVariable Long id) {
        try {
            DetalleFactura detalle = detalleFacturaService.obtenerDetallePorId(id);
            return ResponseEntity.ok(detalle);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public DetalleFactura crearDetalle(@RequestBody DetalleFactura detalle) {
        return detalleFacturaService.crearDetalle(detalle);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DetalleFactura> actualizarDetalle(@PathVariable Long id, @RequestBody DetalleFactura detalleActualizado) {
        try {
            DetalleFactura detalle = detalleFacturaService.actualizarDetalle(id, detalleActualizado);
            return ResponseEntity.ok(detalle);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarDetalle(@PathVariable Long id) {
        try {
            detalleFacturaService.eliminarDetalle(id);
            return ResponseEntity.noContent().build();
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
