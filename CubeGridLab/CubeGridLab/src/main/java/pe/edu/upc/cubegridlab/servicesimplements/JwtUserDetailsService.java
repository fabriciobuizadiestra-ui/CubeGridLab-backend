package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.repositories.IUser_RoleRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class JwtUserDetailsService implements UserDetailsService {
    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IUser_RoleRepository userRoleRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByEmailUser(username);

        if (user.isEmpty()) {
            throw new UsernameNotFoundException("El correo " + username + " no existe");
        }

        User userData = user.get();

        // Validar que el usuario esté activo
        if (!userData.getStatusUser()) {
            throw new UsernameNotFoundException("El usuario " + username + " no está activo");
        }

        // Obtener roles del usuario
        List<GrantedAuthority> authorities = new ArrayList<>();
        List<User_Role> userRoles = userRoleRepository.findByUserIdUser(userData.getIdUser());

        if (!userRoles.isEmpty()) {
            for (User_Role ur : userRoles) {
                authorities.add(new SimpleGrantedAuthority("ROLE_" + ur.getRole().getNameRole()));
            }
        } else {
            // Si no tiene rol asignado, asignar rol por defecto
            authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
        }

        // Retornar UserDetails con email, contraseña (encriptada), estado y roles
        return org.springframework.security.core.userdetails.User.builder()
                .username(userData.getEmailUser())
                .password(userData.getPasswordUser())
                .authorities(authorities)
                .disabled(!userData.getStatusUser())
                .build();
    }
}
