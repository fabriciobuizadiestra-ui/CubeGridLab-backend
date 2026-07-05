package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Role;
import pe.edu.upc.cubegridlab.repositories.IRoleRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IRoleService;

import java.util.List;

@Service
public class RoleServiceImplement implements IRoleService {
    @Autowired
    private IRoleRepository rR;

    @Override
    public List<Role> list() {
        return rR.findAll();
    }

    @Override
    public List<Object[]> quantityUserByRole() {
        return rR.quantityUserByRole();
    }
}

