package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.IoTDevice;
import java.util.List;
import java.util.Optional;

public interface IIoTDeviceService {

    List<IoTDevice> list();

    IoTDevice insert(IoTDevice device);

    Optional<IoTDevice> listId(int id);

    void update(IoTDevice device);

    void delete(int id);

    List<IoTDevice> listByUser(int idUser);


}

