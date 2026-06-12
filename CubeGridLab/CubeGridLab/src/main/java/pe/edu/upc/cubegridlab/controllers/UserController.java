package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.dtos.UserFindByRegisterDateAndStatusDTO;
import pe.edu.upc.cubegridlab.dtos.UserInsertDTO;
import pe.edu.upc.cubegridlab.dtos.UserUpdateDTO;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService uS;
    @Autowired
    private IUser_RoleService urS;
    @GetMapping
    public ResponseEntity<List<UserDTO>> listar()
    {
        List<UserDTO> listaUsuarios= uS.list().stream()
                .map(this::convertUserToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(listaUsuarios);
    }

    private UserDTO convertUserToDTO(User user) {
        UserDTO dto = new UserDTO();

        dto.setIdUser(user.getIdUser());
        dto.setNameUser(user.getNameUser());
        dto.setEmailUser(user.getEmailUser());

        // Obtener los roles del usuario
        List<User_Role> userRoles = urS.findByUserId(user.getIdUser());
        if (!userRoles.isEmpty()) {
            String roles = userRoles.stream()
                    .map(ur -> ur.getRole().getNameRole())
                    .collect(Collectors.joining(", "));
            dto.setRoleUser(roles);
        }

        return dto;
    }
    @PostMapping("/registra")
    public ResponseEntity<?> registrar(@RequestBody UserInsertDTO dto){
        // Validaciones de entrada
        if (dto == null || dto.getNameUser() == null || dto.getNameUser().isEmpty() ||
            dto.getLastNameUser() == null || dto.getLastNameUser().isEmpty() ||
            dto.getEmailUser() == null || dto.getEmailUser().isEmpty() ||
            dto.getPasswordUser() == null || dto.getPasswordUser().isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Campos obligatorios faltantes");
        }

        // Validar que el email no exista ya
        Optional<User> existingUser = uS.findByEmail(dto.getEmailUser());
        if (existingUser.isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("El email ya está registrado");
        }

        try {
            ModelMapper m = new ModelMapper();
            User u = m.map(dto, User.class);

            // Establecer estado activo por defecto
            u.setStatusUser(true);

            // Establecer fecha de registro
            if (u.getRegisterDateUser() == null) {
                u.setRegisterDateUser(java.time.LocalDate.now());
            }

            User usuario = uS.insert(u);
            UserDTO responseDTO = convertUserToDTO(usuario);
            return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al registrar usuario: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        Optional<User> usuario = uS.listId(id);

        if (usuario.isPresent()) {
            UserDTO dto = convertUserToDTO(usuario.get());
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuario no encontrado");
        }
    }

    @PutMapping("/actualiza")
    public ResponseEntity<String> actualizar(@RequestBody UserUpdateDTO dto) {
        // Validación de entrada
        if (dto == null || dto.getIdUser() <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de usuario inválido");
        }

        try {
            Optional<User> existente = uS.listId(dto.getIdUser());
            if (existente.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }

            User u = existente.get();

            if (dto.getNameUser() != null && !dto.getNameUser().isEmpty()) {
                u.setNameUser(dto.getNameUser());
            }
            if (dto.getLastNameUser() != null && !dto.getLastNameUser().isEmpty()) {
                u.setLastNameUser(dto.getLastNameUser());
            }
            if (dto.getEmailUser() != null && !dto.getEmailUser().isEmpty()) {
                u.setEmailUser(dto.getEmailUser());
            }
            if (dto.getPasswordUser() != null && !dto.getPasswordUser().isEmpty()) {
                u.setPasswordUser(dto.getPasswordUser());
            }

            uS.update(u);

            return ResponseEntity.ok("Usuario actualizado correctamente");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al actualizar usuario: " + e.getMessage());
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminar(@PathVariable int id) {
        // Validación de entrada
        if (id <= 0) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("ID de usuario inválido");
        }

        try {
            Optional<User> usuario = uS.listId(id);

            if (usuario.isPresent()) {
                uS.delete(id);
                return ResponseEntity.ok("Usuario eliminado correctamente");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error al eliminar usuario: " + e.getMessage());
        }
    }
    @GetMapping("/filtro-de-correos")
    public ResponseEntity<?> findByStatusTrueAndRegistrationDateBetween(@RequestParam("startDate") LocalDate startDate,
                                             @RequestParam("endDate") LocalDate endDate){
        if (startDate == null || endDate == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Las fechas de inicio y fin son obligatorias");
        }
        if (startDate.isAfter(endDate))
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("La fecha de inicio no puede ser posterior a la fecha de fin");
        }
        ModelMapper m =new ModelMapper();
        List<UserFindByRegisterDateAndStatusDTO> listaBusqueda= uS.findByStatusTrueAndRegistrationDateBetween(startDate, endDate)
                .stream().map(l->m.map(l,UserFindByRegisterDateAndStatusDTO.class))
                .collect(Collectors.toList());
        if(listaBusqueda.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No hay usuarios registrados entre estas fechas");
        }
        return ResponseEntity.ok(listaBusqueda);
    }
    @PostMapping("/logout")
    public ResponseEntity<String> cerrarSesion() {
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }
}
