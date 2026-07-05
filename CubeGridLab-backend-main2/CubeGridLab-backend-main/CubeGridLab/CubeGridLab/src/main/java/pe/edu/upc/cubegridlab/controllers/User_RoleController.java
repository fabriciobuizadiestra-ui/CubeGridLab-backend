package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignDTO;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignResponseDTO;
import pe.edu.upc.cubegridlab.dtos.User_RoleDTO;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user-roles")
public class User_RoleController {
    @Autowired
    private IUser_RoleService urS;

    @GetMapping
    public ResponseEntity<List<User_RoleDTO>> listar() {
        List<User_RoleDTO> listaUserRoles = urS.list().stream()
                .map(y -> {
                    User_RoleDTO dto = new User_RoleDTO();
                    dto.setIdUserRole(y.getIdUserRole());
                    dto.setIdUser(y.getUser().getIdUser());
                    dto.setNameUser(y.getUser().getNameUser());
                    dto.setIdRole(y.getRole().getIdRole());
                    dto.setNameRole(y.getRole().getNameRole());
                    return dto;
                })
                .sorted((a, b) -> Integer.compare(a.getIdUserRole(), b.getIdUserRole()))
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUserRoles);
    }

    @GetMapping("/{idUserRole}")
    public ResponseEntity<?> obtenerPorId(@PathVariable int idUserRole) {
        Optional<User_Role> ur = urS.listId(idUserRole);
        if (ur.isPresent()) {
            User_RoleDTO dto = new User_RoleDTO();
            dto.setIdUserRole(ur.get().getIdUserRole());
            dto.setIdUser(ur.get().getUser().getIdUser());
            dto.setNameUser(ur.get().getUser().getNameUser());
            dto.setIdRole(ur.get().getRole().getIdRole());
            dto.setNameRole(ur.get().getRole().getNameRole());
            return ResponseEntity.ok(dto);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Asignación no encontrada");
    }

    @PostMapping("/asignar")
    public ResponseEntity<?> asignarRolAUsuario(@RequestBody User_RoleAssignDTO assignDTO){
        try {
            // Validar que los IDs no sean vacíos
            if (assignDTO.getIdUser() <= 0 || assignDTO.getIdRole() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("El ID del usuario y el ID del rol deben ser mayores a 0");
            }

            // Usar el servicio para asignar el rol con validaciones
            User_Role user_role = urS.assignRoleToUser(assignDTO);

            // Mapeo manual para evitar ambigüedad en ModelMapper
            User_RoleAssignResponseDTO responseDTO = new User_RoleAssignResponseDTO();
            responseDTO.setIdUserRole(user_role.getIdUserRole());
            responseDTO.setIdUser(user_role.getUser().getIdUser());
            responseDTO.setNameUser(user_role.getUser().getNameUser());
            responseDTO.setIdRole(user_role.getRole().getIdRole());
            responseDTO.setNameRole(user_role.getRole().getNameRole());

            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al asignar rol: " + e.getMessage());
        }
    }

    @PutMapping("/Actualizar")
    public ResponseEntity<?> actualizarAsignacion(@RequestBody User_RoleAssignDTO assignDTO){
        try {
            if (assignDTO.getIdUserRole() <= 0 || assignDTO.getIdUser() <= 0 || assignDTO.getIdRole() <= 0) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Los IDs deben ser mayores a 0");
            }

            User_Role updated = urS.updateRoleAssignment(assignDTO);

            User_RoleDTO response = new User_RoleDTO();
            response.setIdUserRole(updated.getIdUserRole());
            response.setIdUser(updated.getUser().getIdUser());
            response.setNameUser(updated.getUser().getNameUser());
            response.setIdRole(updated.getRole().getIdRole());
            response.setNameRole(updated.getRole().getNameRole());

            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Error: " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar asignación: " + e.getMessage());
        }
    }
    @DeleteMapping("/{idUserRole}")
    public ResponseEntity<String> eliminar(@PathVariable int idUserRole) {
        // Validación de entrada
        if (idUserRole <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de asignación inválido");
        }

        try {
            Optional<User_Role> user_role = urS.listId(idUserRole);

            if (user_role.isPresent()) {
                urS.delete(idUserRole);
                return ResponseEntity.ok("Asignación eliminada correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Asignación no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar asignación: " + e.getMessage());
        }
    }

}
