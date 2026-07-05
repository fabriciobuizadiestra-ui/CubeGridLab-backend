package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImplement implements IUserService{
    @Autowired
    private IUserRepository uR;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> list() {
        return uR.findAll();
    }

    @Override
    public User insert(User u) {
        // Encriptar la contraseña antes de guardar
        if (u.getPasswordUser() != null && !u.getPasswordUser().isEmpty()) {
            u.setPasswordUser(passwordEncoder.encode(u.getPasswordUser()));
        }
        return uR.save(u);
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
