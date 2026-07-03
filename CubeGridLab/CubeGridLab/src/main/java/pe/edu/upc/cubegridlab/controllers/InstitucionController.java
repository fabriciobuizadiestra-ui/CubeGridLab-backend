package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.InstitucionDTO;
import pe.edu.upc.cubegridlab.dtos.InstitucionInsertDTO;
import pe.edu.upc.cubegridlab.entities.Institucion;
import pe.edu.upc.cubegridlab.servicesinterfaces.IInstitucionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/instituciones")
public class InstitucionController {
    @Autowired
    private IInstitucionService iS;

    @GetMapping("/listar")
    public ResponseEntity<List<InstitucionDTO>> listar() {
        List<InstitucionDTO> lista = iS.list().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(lista);
    }

    @PostMapping("/añadir")
    public ResponseEntity<?> añadir(@RequestBody InstitucionInsertDTO dto) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nombre es obligatorio");
        }
        try {
            ModelMapper m = new ModelMapper();
            Institucion institucion = m.map(dto, Institucion.class);
            Institucion nueva = iS.insert(institucion);
            InstitucionDTO responseDTO = convertToDTO(nueva);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al añadir institución: " + e.getMessage());
        }
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID inválido");
        }
        try {
            Optional<Institucion> institucion = iS.listId(id);
            if (institucion.isPresent()) {
                iS.delete(id);
                return ResponseEntity.ok("Institución eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Institución no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar institución: " + e.getMessage());
        }
    }

    private InstitucionDTO convertToDTO(Institucion institucion) {
        InstitucionDTO dto = new InstitucionDTO();
        dto.setIdInstitucion(institucion.getIdInstitucion());
        dto.setNombre(institucion.getNombre());
        dto.setTipo(institucion.getTipo());
        return dto;
    }
}
