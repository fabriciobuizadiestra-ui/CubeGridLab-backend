package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.CubesatInsertDTO;
import pe.edu.upc.cubegridlab.dtos.CubesatResponseDTO;
import pe.edu.upc.cubegridlab.dtos.CubesatUpdateDTO;
import pe.edu.upc.cubegridlab.entities.Cubesat;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.servicesinterfaces.ICubesatService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/cubesats")
public class CubesatController {

    @Autowired
    private ICubesatService cS;

    @Autowired
    private IUserService uS;

    @GetMapping("/Listar")
    public List<CubesatResponseDTO> listar() {
        return cS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/total-utilizados-simulaciones")
    public Long contarCubesatsUtilizadosEnSimulaciones() {
        return cS.contarCubesatsUtilizadosEnSimulaciones();
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> registrar(@RequestBody CubesatInsertDTO dto) {
        if (dto == null || dto.getNombre() == null || dto.getNombre().isBlank() || dto.getMasa() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nombre y masa son obligatorios");
        }

        Cubesat cubesat = new Cubesat();
        cubesat.setNombre(dto.getNombre());
        cubesat.setMasa(dto.getMasa());
        cubesat.setDimensiones(dto.getDimensiones());
        cubesat.setFrecuencia(dto.getFrecuencia());
        cubesat.setEstado(dto.getEstado());

        if (dto.getIdUsuario() != null) {
            Optional<User> usuario = uS.listId(dto.getIdUsuario());
            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
            cubesat.setUsuario(usuario.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(cS.insert(cubesat)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Cubesat> cubesat = cS.listId(id);

        if (cubesat.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(cubesat.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizar(@RequestBody CubesatUpdateDTO dto) {
        if (dto == null || dto.getIdCubesat() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de CubeSat invalido");
        }

        Optional<Cubesat> existente = cS.listId(dto.getIdCubesat());
        if (existente.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("CubeSat no encontrado");
        }

        Cubesat cubesat = existente.get();

        if (dto.getNombre() != null && !dto.getNombre().isBlank()) {
            cubesat.setNombre(dto.getNombre());
        }
        if (dto.getMasa() > 0) {
            cubesat.setMasa(dto.getMasa());
        }
        if (dto.getDimensiones() != null) {
            cubesat.setDimensiones(dto.getDimensiones());
        }
        if (dto.getFrecuencia() != null) {
            cubesat.setFrecuencia(dto.getFrecuencia());
        }
        if (dto.getEstado() != null) {
            cubesat.setEstado(dto.getEstado());
        }
        if (dto.getIdUsuario() != null) {
            Optional<User> usuario = uS.listId(dto.getIdUsuario());
            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
            cubesat.setUsuario(usuario.get());
        }

        cS.update(cubesat);
        return ResponseEntity.ok(toResponseDTO(cubesat));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        cS.delete(id);
        return ResponseEntity.ok("CubeSat eliminado correctamente");
    }

    private CubesatResponseDTO toResponseDTO(Cubesat cubesat) {
        CubesatResponseDTO dto = new CubesatResponseDTO();
        dto.setIdCubesat(cubesat.getIdCubesat());
        dto.setNombre(cubesat.getNombre());
        dto.setMasa(cubesat.getMasa());
        dto.setDimensiones(cubesat.getDimensiones());
        dto.setFrecuencia(cubesat.getFrecuencia());
        dto.setEstado(cubesat.getEstado());
        if (cubesat.getUsuario() != null) {
            dto.setIdUsuario(cubesat.getUsuario().getIdUser());
        }
        return dto;
    }
}
