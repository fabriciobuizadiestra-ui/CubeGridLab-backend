package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.entities.User_Role;
import pe.edu.upc.cubegridlab.dtos.User_RoleAssignDTO;

import java.util.List;

public interface IUser_RoleService {
    List<User_Role> list();
    List<User_Role> findByUserId(int idUser);
    public User_Role assignRoleToUser(User_RoleAssignDTO assignDTO);
}
