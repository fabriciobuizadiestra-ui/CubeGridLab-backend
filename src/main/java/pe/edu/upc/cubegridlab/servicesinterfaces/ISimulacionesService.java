package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Simulaciones;

import java.util.List;
import java.util.Optional;

public interface ISimulacionesService {

    List<Simulaciones> list();

    Simulaciones insert(Simulaciones s);

    Optional<Simulaciones> listId(int id);

    void update(Simulaciones s);

    void delete(int id);
}