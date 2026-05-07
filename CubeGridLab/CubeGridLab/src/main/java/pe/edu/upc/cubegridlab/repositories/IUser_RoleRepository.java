package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.User_Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUser_RoleRepository extends JpaRepository<User_Role, Integer> {
    List<User_Role> findByUserIdUser(int idUser);
    Optional<User_Role> findByUserIdUserAndRoleIdRole(int idUser, int idRole);
}
