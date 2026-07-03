package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pe.edu.upc.cubegridlab.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmailUser(String emailUser);

    //Como desarrollador quiero listar usuarios con status true e ingresar un rango de fechas de registro.
    @Query("select u from User u where u.statusUser = true and u.registerDateUser between :startDate and :endDate")
    public List<User> findByStatusTrueAndRegistrationDateBetween(@Param("startDate")LocalDate startDate, @Param("endDate") LocalDate endDate);
}
