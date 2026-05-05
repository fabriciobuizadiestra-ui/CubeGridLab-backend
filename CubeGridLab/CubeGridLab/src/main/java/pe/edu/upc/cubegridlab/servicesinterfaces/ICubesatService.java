package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Cubesat;

import java.util.List;
import java.util.Optional;

public interface ICubesatService {

    List<Cubesat> list();

    Cubesat insert(Cubesat c);

    Optional<Cubesat> listId(int id);

    void update(Cubesat c);

    void delete(int id);

    Long contarCubesatsUtilizadosEnSimulaciones();
}
