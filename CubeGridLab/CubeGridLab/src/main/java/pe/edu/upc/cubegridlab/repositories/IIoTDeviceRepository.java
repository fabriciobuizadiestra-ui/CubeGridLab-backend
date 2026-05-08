package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.cubegridlab.entities.IoTDevice;
import java.util.List;
import java.util.Optional;

public interface IIoTDeviceRepository extends JpaRepository<IoTDevice, Integer> {
    List<IoTDevice> findByUsuarioIdUser(int idUser);
    List<IoTDevice> findByStatus(String status);
}

