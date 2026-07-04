package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.ResultadoEvaluacionInsertDTO;
import pe.edu.upc.cubegridlab.dtos.ResultadoEvaluacionResponseDTO;
import pe.edu.upc.cubegridlab.dtos.ResultadoEvaluacionUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Evaluaciones;
import pe.edu.upc.cubegridlab.entities.ResultadoEvaluacion;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.servicesinterfaces.IEvaluacionesService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IResultadoEvaluacionesService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/resultado-evaluacion")
public class ResultadoEvaluacionController {

    @Autowired
    private IResultadoEvaluacionesService rS;

    @Autowired
    private IUserService uS;

    @Autowired
    private IEvaluacionesService eS;

    @GetMapping
    public List<ResultadoEvaluacionResponseDTO> listar() {
        return rS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/reporte-resultados-usuario")
    public List<Object[]> reporteResultadosPorUsuario() {
        return rS.quantityResultsByUser();
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody ResultadoEvaluacionInsertDTO dto) {
        if (dto == null || dto.getPuntaje() == null || dto.getPuntaje().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Puntaje es obligatorio");
        }

        ResultadoEvaluacion resultado = new ResultadoEvaluacion();
        resultado.setPuntaje(dto.getPuntaje());
        resultado.setDescripcion(dto.getDescripcion());

        if (dto.getIdUser() != null) {
            Optional<User> user = uS.listId(dto.getIdUser());
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
            resultado.setUser(user.get());
        }

        if (dto.getIdEvaluacion() != null) {
            Optional<Evaluaciones> evaluacion = eS.listId(dto.getIdEvaluacion());
            if (evaluacion.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Evaluacion no encontrada");
            }
            resultado.setEvaluaciones(evaluacion.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(rS.insert(resultado)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<ResultadoEvaluacion> resultado = rS.listId(id);

        if (resultado.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(resultado.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        rS.delete(id);
        return ResponseEntity.ok("Resultado eliminado correctamente");
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody ResultadoEvaluacionUpdateDTO dto) {
        if (dto == null || dto.getIdResultado() == null || dto.getIdResultado() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de resultado invalido");
        }

        Optional<ResultadoEvaluacion> existente = rS.listId(dto.getIdResultado());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resultado no encontrado");
        }

        ResultadoEvaluacion resultado = existente.get();
        if (dto.getPuntaje() != null && !dto.getPuntaje().isBlank()) resultado.setPuntaje(dto.getPuntaje());
        if (dto.getDescripcion() != null) resultado.setDescripcion(dto.getDescripcion());

        if (dto.getIdUser() != null) {
            Optional<User> user = uS.listId(dto.getIdUser());
            if (user.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuario no encontrado");
            }
            resultado.setUser(user.get());
        }

        if (dto.getIdEvaluacion() != null) {
            Optional<Evaluaciones> evaluacion = eS.listId(dto.getIdEvaluacion());
            if (evaluacion.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Evaluacion no encontrada");
            }
            resultado.setEvaluaciones(evaluacion.get());
        }

        rS.update(resultado);
        return ResponseEntity.ok(toResponseDTO(resultado));
    }

    private ResultadoEvaluacionResponseDTO toResponseDTO(ResultadoEvaluacion resultado) {
        ResultadoEvaluacionResponseDTO dto = new ResultadoEvaluacionResponseDTO();
        dto.setIdResultado(resultado.getIdResultado());
        dto.setPuntaje(resultado.getPuntaje());
        dto.setDescripcion(resultado.getDescripcion());

        if (resultado.getUser() != null) {
            dto.setIdUser(resultado.getUser().getIdUser());
            dto.setNameUser(resultado.getUser().getNameUser());
        }

        if (resultado.getEvaluaciones() != null) {
            dto.setIdEvaluacion(resultado.getEvaluaciones().getIdEvaluacion());
            dto.setTituloEvaluacion(resultado.getEvaluaciones().getTitulo());
        }

        return dto;
    }
}
