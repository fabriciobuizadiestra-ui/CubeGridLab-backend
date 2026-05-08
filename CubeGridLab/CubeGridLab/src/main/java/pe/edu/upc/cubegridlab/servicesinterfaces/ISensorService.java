package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.Sensor;
import java.util.List;
import java.util.Optional;

public interface ISensorService {

    List<Sensor> list();

    Sensor insert(Sensor sensor);

    Optional<Sensor> listId(int id);

    void update(Sensor sensor);

    void delete(int id);

    List<Sensor> listByDevice(int idDevice);

    List<Sensor> listActiveSensorsByDevice(int idDevice);

    List<Sensor> listSensorsByUser(int idUser);
}

