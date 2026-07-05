package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Misiones;

import java.util.List;
import java.util.Optional;

public interface IMisionesService {
    public List<Misiones> list();
    Misiones insert(Misiones m);
    Optional<Misiones> listId(int id);
    void update(Misiones m);
    void delete(int id);
    Long contarMisionesExitosas();
    List<Object[]> quantityMissionsByUser();
}
