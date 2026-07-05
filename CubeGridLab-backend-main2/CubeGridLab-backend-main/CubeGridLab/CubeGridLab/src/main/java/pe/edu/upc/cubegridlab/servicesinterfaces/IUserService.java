package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface IUserService {
    public List<User> list();
    public User insert(User u);
    public Optional<User> listId(int id);
    public void update(User u);
    public void delete(int id);
    Optional<User> findByEmail(String emailUser);
    List<User> findByStatusTrueAndRegistrationDateBetween(LocalDate startDate, LocalDate endDate);
}
