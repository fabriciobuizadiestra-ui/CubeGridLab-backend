package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Role;

import java.util.List;

public interface IRoleService {
    public List<Role> list();
    public List<Object[]> quantityUserByRole();
}

