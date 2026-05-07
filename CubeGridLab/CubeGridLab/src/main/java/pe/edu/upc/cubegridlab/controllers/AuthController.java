package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.AuthResponseDTO;
import pe.edu.upc.cubegridlab.dtos.LoginDTO;
import pe.edu.upc.cubegridlab.dtos.UserDTO;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.securities.JwtTokenProvider;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private IUserService uS;

    @Autowired
    private IUser_RoleService urS;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO dto) {

        if (dto.getEmailUser() == null || dto.getEmailUser().isEmpty()
                || dto.getPasswordUser() == null || dto.getPasswordUser().isEmpty()) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body("Correo y contraseña son obligatorios");
        }

        try {
            // Autenticar usuario
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            dto.getEmailUser(),
                            dto.getPasswordUser()
                    )
            );

            SecurityContextHolder.getContext().setAuthentication(authentication);

            // Generar JWT token
            String jwt = tokenProvider.generateToken(authentication);

            // Obtener datos del usuario
            Optional<User> usuario = uS.findByEmail(dto.getEmailUser());
            if (usuario.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body("Usuario no encontrado");
            }

            User user = usuario.get();

            UserDTO responseDTO = new UserDTO();
            responseDTO.setNameUser(user.getNameUser());
            responseDTO.setEmailUser(user.getEmailUser());

            List<User_Role> userRoles = urS.findByUserId(user.getIdUser());
            if (!userRoles.isEmpty()) {
                responseDTO.setRoleUser(
                        userRoles.get(0).getRole().getNameRole()
                );
            }

            AuthResponseDTO authResponse = new AuthResponseDTO(
                    jwt,
                    "Login exitoso",
                    responseDTO
            );

            return ResponseEntity.ok(authResponse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body("Email o contraseña incorrectos");
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Sesión cerrada correctamente");
    }
}