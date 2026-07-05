package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.EvaluacionInsertDTO;
import pe.edu.upc.cubegridlab.dtos.EvaluacionResponseDTO;
import pe.edu.upc.cubegridlab.dtos.EvaluacionUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Curso;
import pe.edu.upc.cubegridlab.entities.Evaluaciones;
import pe.edu.upc.cubegridlab.repositories.ICursoRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IEvaluacionesService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/evaluaciones")
public class EvaluacionesController {

    @Autowired
    private IEvaluacionesService eS;

    @Autowired
    private ICursoRepository cR;

    @GetMapping
    public List<EvaluacionResponseDTO> listar() {
        return eS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody EvaluacionInsertDTO dto) {
        if (dto == null || dto.getTitulo() == null || dto.getTitulo().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Título es obligatorio");
        }

        Evaluaciones evaluacion = new Evaluaciones();
        evaluacion.setTitulo(dto.getTitulo());

        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cR.findById(dto.getIdCurso());
            if (curso.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Curso no encontrado");
            }
            evaluacion.setCurso(curso.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(eS.insert(evaluacion));
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody EvaluacionUpdateDTO dto) {
        if (dto == null || dto.getIdEvaluacion() == null || dto.getIdEvaluacion() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de evaluación invalido");
        }

        Optional<Evaluaciones> existente = eS.listId(dto.getIdEvaluacion());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluación no encontrada");
        }

        Evaluaciones evaluacion = existente.get();
        if (dto.getTitulo() != null && !dto.getTitulo().isBlank()) evaluacion.setTitulo(dto.getTitulo());

        if (dto.getIdCurso() != null) {
            Optional<Curso> curso = cR.findById(dto.getIdCurso());
            if (curso.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
            }
            evaluacion.setCurso(curso.get());
        }

        eS.update(evaluacion);
        return ResponseEntity.ok(evaluacion);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Evaluaciones> evaluacion = eS.listId(id);

        if (evaluacion.isPresent()) {
            return ResponseEntity.ok(evaluacion.get());
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        eS.delete(id);
        return ResponseEntity.ok("Evaluación eliminada correctamente");
    }
    private EvaluacionResponseDTO toResponseDTO(Evaluaciones evaluacion) {
        EvaluacionResponseDTO dto = new EvaluacionResponseDTO();

        dto.setIdEvaluacion(evaluacion.getIdEvaluacion());
        dto.setTitulo(evaluacion.getTitulo());

        if (evaluacion.getCurso() != null) {
            dto.setIdCurso(evaluacion.getCurso().getIdCurso());
        }

        return dto;
    }
}
