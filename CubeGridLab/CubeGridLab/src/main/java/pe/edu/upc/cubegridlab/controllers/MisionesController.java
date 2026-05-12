package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.MisionInsertDTO;
import pe.edu.upc.cubegridlab.dtos.MisionResponseDTO;
import pe.edu.upc.cubegridlab.entities.Cubesat;
import pe.edu.upc.cubegridlab.entities.Misiones;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.servicesinterfaces.ICubesatService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IMisionesService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/misiones")
public class MisionesController {

    @Autowired
    private IMisionesService mS;

    @Autowired
    private IUserService uS;

    @Autowired
    private ICubesatService cS;

    @GetMapping("/Listar")
    public List<MisionResponseDTO> listar() {
        return mS.list()
                .stream()
                .map(this::toResponseDTO)
                .collect(Collectors.toList());
    }

    @GetMapping("/cantidad-exitosas")
    public Long contarMisionesExitosas() {
        return mS.contarMisionesExitosas();
    }

    @GetMapping("/reporte-misiones-usuario")
    public List<Object[]> reporteMisionesPorUsuario() {
        return mS.quantityMissionsByUser();
    }

    @PostMapping("/Registrar")
    public ResponseEntity<?> registrar(@RequestBody MisionInsertDTO dto) {
        if (dto == null || dto.getNombreMision() == null || dto.getNombreMision().isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Nombre de mision es obligatorio");
        }

        Misiones m = new Misiones();
        m.setNombreMision(dto.getNombreMision());
        m.setDescripcionMision(dto.getDescripcionMision());

        if (dto.getIdUsuario() != null) {
            Optional<User> usuario = uS.listId(dto.getIdUsuario());
            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
            m.setUsuario(usuario.get());
        }

        if (dto.getIdCubesat() != null) {
            Optional<Cubesat> cubesat = cS.listId(dto.getIdCubesat());
            if (cubesat.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("CubeSat no encontrado");
            }
            m.setCubesat(cubesat.get());
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(toResponseDTO(mS.insert(m)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Misiones> mision = mS.listId(id);

        if (mision.isPresent()) {
            return ResponseEntity.ok(toResponseDTO(mision.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        mS.delete(id);
        return ResponseEntity.ok("Misión eliminada correctamente");
    }

    @PostMapping("/{id}/clonar") //US20: Clonar una misión
    public ResponseEntity<?> clonarMision(@PathVariable int id) {

        Optional<Misiones> original = mS.listId(id);

        if (original.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Misiones nueva = new Misiones();

        nueva.setNombreMision(
                original.get().getNombreMision() + " - copia"
        );

        nueva.setDescripcionMision(
                original.get().getDescripcionMision()
        );

        nueva.setUsuario(
                original.get().getUsuario()
        );

        nueva.setCubesat(
                original.get().getCubesat()
        );

        return ResponseEntity.ok(toResponseDTO(mS.insert(nueva)));
    }

    private MisionResponseDTO toResponseDTO(Misiones mision) {
        MisionResponseDTO dto = new MisionResponseDTO();
        dto.setIdMision(mision.getIdMision());
        dto.setNombreMision(mision.getNombreMision());
        dto.setDescripcionMision(mision.getDescripcionMision());

        if (mision.getUsuario() != null) {
            dto.setIdUsuario(mision.getUsuario().getIdUser());
            dto.setNombreUsuario(mision.getUsuario().getNameUser());
        }

        if (mision.getCubesat() != null) {
            dto.setIdCubesat(mision.getCubesat().getIdCubesat());
            dto.setNombreCubesat(mision.getCubesat().getNombre());
        }

        return dto;
    }
}
