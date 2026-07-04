package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.ModuloInsertDTO;
import pe.edu.upc.cubegridlab.dtos.ModuloResponseDTO;
import pe.edu.upc.cubegridlab.dtos.ModuloUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Curso;
import pe.edu.upc.cubegridlab.entities.Modulo;
import pe.edu.upc.cubegridlab.repositories.ICursoRepository;
import pe.edu.upc.cubegridlab.repositories.IModuloRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/modulos")
public class ModuloController {

    @Autowired
    private IModuloRepository mR;

    @Autowired
    private ICursoRepository cR;

    @GetMapping("/Listar")
    public List<ModuloResponseDTO> listar() {
        return mR.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/curso/{id}")
    public List<ModuloResponseDTO> listarPorCurso(@PathVariable int id) {
        return mR.findByCurso_IdCurso(id)
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> registrar(@RequestBody ModuloInsertDTO dto) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre es obligatorio");
        }

        Modulo modulo = new Modulo();
        modulo.setNombre(dto.getNombre());

        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cR.findById(dto.getIdCurso());
            if (curso.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
            }
            modulo.setCurso(curso.get());
        } else {
            modulo.setCurso(null);
        }

        Modulo saved = mR.save(modulo);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Modulo> modulo = mR.findById(id);
        if (modulo.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(modulo.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody ModuloUpdateDTO dto) {
        if (dto == null || dto.getIdModulo() == null || dto.getIdModulo() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de modulo invalido");
        }

        Optional<Modulo> existente = mR.findById(dto.getIdModulo());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Modulo no encontrado");
        }

        Modulo modulo = existente.get();
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) modulo.setNombre(dto.getNombre());

        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cR.findById(dto.getIdCurso());
            if (curso.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
            }
            modulo.setCurso(curso.get());
        }

        mR.save(modulo);
        return ResponseEntity.ok(toResponseDTO(modulo));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        mR.deleteById(id);
        return ResponseEntity.ok("Modulo eliminado correctamente");
    }

    private ModuloResponseDTO toResponseDTO(Modulo modulo) {
        ModuloResponseDTO dto = new ModuloResponseDTO();
        dto.setIdModulo(modulo.getIdModulo());
        dto.setNombre(modulo.getNombre());
        if (modulo.getCurso() != null) dto.setIdCurso(modulo.getCurso().getIdCurso());
        return dto;
    }
}

