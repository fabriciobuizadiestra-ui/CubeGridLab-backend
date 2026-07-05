package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignDTO;
import pe.edu.upc.cubegridlab.entities.Role;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.repositories.IRoleRepository;
import pe.edu.upc.cubegridlab.repositories.IUser_RoleRepository;
import pe.edu.upc.cubegridlab.repositories.IUserRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUser_RoleService;

import java.util.List;
import java.util.Optional;

@Service
public class User_RoleServiceImplement implements IUser_RoleService {
    @Autowired
    private IUser_RoleRepository urR;

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Override
    public List<User_Role> list() {
        return urR.findAll();
    }

    @Override
    public List<User_Role> findByUserId(int idUser) {
        return urR.findByUserIdUser(idUser);
    }


    @Override
    public User_Role assignRoleToUser(User_RoleAssignDTO assignDTO) {
        // Validar que el usuario existe
        Optional<User> user = userRepository.findById(assignDTO.getIdUser());
        if (!user.isPresent()) {
            throw new IllegalArgumentException("El usuario con ID " + assignDTO.getIdUser() + " no existe");
        }

        // Validar que el rol existe
        Optional<Role> role = roleRepository.findById(assignDTO.getIdRole());
        if (!role.isPresent()) {
            throw new IllegalArgumentException("El rol con ID " + assignDTO.getIdRole() + " no existe");
        }

        // Crear y guardar la relación
        User_Role userRole = new User_Role();
        userRole.setUser(user.get());
        userRole.setRole(role.get());

        return urR.save(userRole);
    }

    @Override
    public User_Role updateRoleAssignment(User_RoleAssignDTO assignDTO) {
        // Validar que la asignación existe
        Optional<User_Role> existing = urR.findById(assignDTO.getIdUserRole());
        if (existing.isEmpty()) {
            throw new IllegalArgumentException("La asignación con ID " + assignDTO.getIdUserRole() + " no existe");
        }

        // Validar usuario y rol
        Optional<User> user = userRepository.findById(assignDTO.getIdUser());
        if (!user.isPresent()) {
            throw new IllegalArgumentException("El usuario con ID " + assignDTO.getIdUser() + " no existe");
        }

        Optional<Role> role = roleRepository.findById(assignDTO.getIdRole());
        if (!role.isPresent()) {
            throw new IllegalArgumentException("El rol con ID " + assignDTO.getIdRole() + " no existe");
        }

        User_Role ur = existing.get();
        ur.setUser(user.get());
        ur.setRole(role.get());
        return urR.save(ur);
    }

    @Override
    public void delete(int idUserRole) {
        urR.deleteById(idUserRole);
    }

    @Override
    public Optional<User_Role> listId(int idUserRole) {
        return urR.findById(idUserRole);
    }

}
