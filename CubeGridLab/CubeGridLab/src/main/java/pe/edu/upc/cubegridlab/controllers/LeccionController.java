package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.LeccionInsertDTO;
import pe.edu.upc.cubegridlab.dtos.LeccionResponseDTO;
import pe.edu.upc.cubegridlab.dtos.LeccionUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Leccion;
import pe.edu.upc.cubegridlab.entities.Modulo;
import pe.edu.upc.cubegridlab.repositories.ILeccionRepository;
import pe.edu.upc.cubegridlab.repositories.IModuloRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/lecciones")
public class LeccionController {

    @Autowired
    private ILeccionRepository lR;

    @Autowired
    private IModuloRepository mR;

    @GetMapping("/Listar")
    public List<LeccionResponseDTO> listar() {
        return lR.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/modulo/{id}")
    public List<LeccionResponseDTO> listarPorModulo(@PathVariable int id) {
        return lR.findByModulo_IdModulo(id)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> registrar(@RequestBody LeccionInsertDTO dto) {
        if (dto == null || dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Titulo es obligatorio");
        }
        if (dto.getIdModulo() == null || dto.getIdModulo() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Modulo es obligatorio");
        }

        Optional<Modulo> modulo = mR.findById(dto.getIdModulo());
        if (modulo.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modulo no encontrado");
        }

        Leccion leccion = new Leccion();
        leccion.setTitulo(dto.getTitulo());
        leccion.setContenido(dto.getContenido());
        leccion.setModulo(modulo.get());

        Leccion saved = lR.save(leccion);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Leccion> leccion = lR.findById(id);
        if (leccion.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(leccion.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody LeccionUpdateDTO dto) {
        if (dto == null || dto.getIdLeccion() == null || dto.getIdLeccion() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de leccion invalido");
        }

        Optional<Leccion> existente = lR.findById(dto.getIdLeccion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Leccion no encontrada");
        }

        Leccion leccion = existente.get();
        if (dto.getTitulo() != null && !dto.getTitulo().isBlank()) leccion.setTitulo(dto.getTitulo());
        if (dto.getContenido() != null) leccion.setContenido(dto.getContenido());

        if (dto.getIdModulo() != null) {
            Optional<Modulo> modulo = mR.findById(dto.getIdModulo());
            if (modulo.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modulo no encontrado");
            }
            leccion.setModulo(modulo.get());
        } else if (leccion.getModulo() == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Modulo es obligatorio");
        }

        lR.save(leccion);
        return ResponseEntity.ok(toResponseDTO(leccion));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        lR.deleteById(id);
        return ResponseEntity.ok("Leccion eliminada correctamente");
    }

    private LeccionResponseDTO toResponseDTO(Leccion leccion) {
        LeccionResponseDTO dto = new LeccionResponseDTO();
        final String moduloNoAsignado = "Sin modulo asignado";

        dto.setIdLeccion(leccion.getIdLeccion());
        dto.setTitulo(leccion.getTitulo());
        dto.setContenido(leccion.getContenido());
        if (leccion.getModulo() != null) {
            dto.setIdModulo(leccion.getModulo().getIdModulo());
            String nombreModulo = leccion.getModulo().getNombre() != null ? leccion.getModulo().getNombre().trim() : "";
            dto.setModulo(nombreModulo.isBlank() ? moduloNoAsignado : nombreModulo);
        } else {
            dto.setIdModulo(0);
            dto.setModulo(moduloNoAsignado);
        }
        return dto;
    }
}
