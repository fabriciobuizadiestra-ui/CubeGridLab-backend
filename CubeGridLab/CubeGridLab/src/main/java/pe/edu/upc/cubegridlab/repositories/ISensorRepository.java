package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.cubegridlab.entities.Sensor;
import java.util.List;

public interface ISensorRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findByIotDeviceIdDevice(int idDevice);
    List<Sensor> findByStatus(String status);
    @Query("SELECT s FROM Sensor s WHERE s.iotDevice.idDevice = :idDevice AND s.status = :status")
    List<Sensor> findActiveSensorsByDevice(@Param("idDevice") int idDevice, @Param("status") String status);
    @Query("SELECT s FROM Sensor s WHERE s.iotDevice.usuario.idUser = :idUser")
    List<Sensor> findSensorsByUser(@Param("idUser") int idUser);
}

