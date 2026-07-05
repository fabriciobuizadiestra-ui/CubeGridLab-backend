package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.Role;

import java.util.List;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Integer> {
    //Listar la cantidad de usuarios por rol.
    @Query("SELECT r.nameRole, COUNT(ur.user) FROM Role r LEFT JOIN r.userRoles ur GROUP BY r.nameRole")
    List<Object[]> quantityUserByRole();

}
