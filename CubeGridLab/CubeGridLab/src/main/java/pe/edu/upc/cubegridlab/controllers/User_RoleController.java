package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignDTO;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignResponseDTO;
import pe.edu.upc.cubegridlab.dtos.User_RoleDTO;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;
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


}
