package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.CursoInsertDTO;
import pe.edu.upc.cubegridlab.dtos.CursoResponseDTO;
import pe.edu.upc.cubegridlab.dtos.CursoUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Curso;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.repositories.ICursoRepository;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cursos")
public class CursoController {

    @Autowired
    private ICursoRepository cR;

    @Autowired
    private IUserRepository uR;
    
    @Autowired
    private IUser_RoleService urS;

    @GetMapping("/Listar")
    public List<CursoResponseDTO> listar() {
        return cR.findAll()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/docente/{id}")
    public List<CursoResponseDTO> listarPorDocente(@PathVariable int id) {
        return cR.findAll()
                .stream()
                .filter(c -> c.getDocente() != null && c.getDocente().getIdUser() == id)
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/docentes")
    public List<UserDTO> listarDocentes() {
        // Obtener todas las asignaciones user_role, filtrar por rol TEACHER y mapear a usuarios únicos
        return urS.list()
                .stream()
                .filter(ur -> ur.getRole() != null && "TEACHER".equalsIgnoreCase(ur.getRole().getNameRole()))
                .map(ur -> {
                    User u = ur.getUser();
                    UserDTO dto = new UserDTO();
                    dto.setIdUser(u.getIdUser());
                    dto.setNameUser(u.getNameUser());
                    dto.setLastNameUser(u.getLastNameUser());
                    dto.setEmailUser(u.getEmailUser());
                    dto.setRoleUser(ur.getRole().getNameRole());
                    return dto;
                })
                .distinct()
                .collect(Collectors.toList());
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> registrar(@RequestBody CursoInsertDTO dto) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Nombre es obligatorio");
        }

        Curso curso = new Curso();
        curso.setNombre(dto.getNombre());
        curso.setDescripcion(dto.getDescripcion());

        if (dto.getIdDocente() != null) {
            Optional<User> docente = uR.findById(dto.getIdDocente());
            if (docente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Docente no encontrado");
            }
            // Validar que el usuario tenga el rol TEACHER
            boolean isTeacher = urS.findByUserId(docente.get().getIdUser())
                    .stream()
                    .anyMatch(ur -> ur.getRole() != null && "TEACHER".equalsIgnoreCase(ur.getRole().getNameRole()));
            if (!isTeacher) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no tiene asignado el rol TEACHER");
            }
            curso.setDocente(docente.get());
        } else {
            curso.setDocente(null);
        }

        Curso saved = cR.save(curso);
        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(saved));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Curso> curso = cR.findById(id);
        if (curso.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(curso.get()));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody CursoUpdateDTO dto) {
        if (dto == null || dto.getIdCurso() == null || dto.getIdCurso() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ID de curso invalido");
        }

        Optional<Curso> existente = cR.findById(dto.getIdCurso());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Curso no encontrado");
        }

        Curso curso = existente.get();
        if (dto.getNombre() != null && !dto.getNombre().isBlank()) curso.setNombre(dto.getNombre());
        if (dto.getDescripcion() != null) curso.setDescripcion(dto.getDescripcion());

        if (dto.getIdDocente() != null) {
            Optional<User> docente = uR.findById(dto.getIdDocente());
            if (docente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Docente no encontrado");
            }
            boolean isTeacher = urS.findByUserId(docente.get().getIdUser())
                    .stream()
                    .anyMatch(ur -> ur.getRole() != null && "TEACHER".equalsIgnoreCase(ur.getRole().getNameRole()));
            if (!isTeacher) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("El usuario no tiene asignado el rol TEACHER");
            }
            curso.setDocente(docente.get());
        }

        cR.save(curso);
        return ResponseEntity.ok(toResponseDTO(curso));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        cR.deleteById(id);
        return ResponseEntity.ok("Curso eliminado correctamente");
    }

    private CursoResponseDTO toResponseDTO(Curso curso) {
        CursoResponseDTO dto = new CursoResponseDTO();
        dto.setIdCurso(curso.getIdCurso());
        dto.setNombre(curso.getNombre());
        dto.setDescripcion(curso.getDescripcion());
        if (curso.getDocente() != null) dto.setIdDocente(curso.getDocente().getIdUser());
        return dto;
    }
}


