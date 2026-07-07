package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pe.edu.upc.cubegridlab.entities.Role;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.repositories.IRoleRepository;
import pe.edu.upc.cubegridlab.repositories.IUser_RoleRepository;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements IUserService{
    private static final String DEFAULT_ROLE = "STUDENT";

    @Autowired
    private IUserRepository uR;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private IUser_RoleRepository userRoleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return uR.findAll();
    }

    @Override
    @Transactional
    public User insert(User u) {
        // Encriptar la contraseña antes de guardar
        if (u.getPasswordUser() != null && !u.getPasswordUser().isEmpty()) {
            u.setPasswordUser(passwordEncoder.encode(u.getPasswordUser()));
        }

        User savedUser = uR.save(u);
        assignDefaultRole(savedUser);
        return savedUser;
    }

    private void assignDefaultRole(User user) {
        Role studentRole = roleRepository.findByNameRoleIgnoreCase(DEFAULT_ROLE)
                .orElseThrow(() -> new IllegalStateException("No existe el rol por defecto STUDENT"));

        boolean alreadyAssigned = userRoleRepository
                .findByUserIdUserAndRoleIdRole(user.getIdUser(), studentRole.getIdRole())
                .isPresent();

        if (!alreadyAssigned) {
            User_Role userRole = new User_Role();
            userRole.setUser(user);
            userRole.setRole(studentRole);
            userRoleRepository.save(userRole);
        }
    }

    @Override
    public Optional<User> listId(int id) {
        return uR.findById(id);
    }

    @Override
    public void update(User u) {
        Optional<User> existingUser = uR.findById(u.getIdUser());
        if (existingUser.isPresent()) {
            // Encriptar si la contraseña fue cambiada
            if (u.getPasswordUser() != null && !u.getPasswordUser().isEmpty()) {
                if (!passwordEncoder.matches(u.getPasswordUser(), existingUser.get().getPasswordUser())) {
                    u.setPasswordUser(passwordEncoder.encode(u.getPasswordUser()));
                }
            }
        }
        uR.save(u);
    }

    @Override
    public void delete(int id) {
        uR.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String emailUser) {
        return uR.findByEmailUser(emailUser);

    }

    @Override
    public List<User> findByStatusTrueAndRegistrationDateBetween(LocalDate startDate, LocalDate endDate) {
        return uR.findByStatusTrueAndRegistrationDateBetween(startDate, endDate);
    }
}