package pe.edu.upc.cubegridlab.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pe.edu.upc.cubegridlab.entities.SensorData;
import java.time.LocalDateTime;
import java.util.List;

public interface ISensorDataRepository extends JpaRepository<SensorData, Integer> {
    List<SensorData> findBySensorIdSensor(int idSensor);
    List<SensorData> findBySimulacionIdSimulacion(int idSimulacion);
    List<SensorData> findByEventType(String eventType);

    @Query("SELECT sd FROM SensorData sd WHERE sd.sensor.idSensor = :idSensor AND sd.timestamp BETWEEN :startTime AND :endTime ORDER BY sd.timestamp DESC")
    List<SensorData> findSensorDataByTimeRange(@Param("idSensor") int idSensor, @Param("startTime") LocalDateTime startTime, @Param("endTime") LocalDateTime endTime);

    @Query("SELECT sd FROM SensorData sd WHERE sd.simulacion.idSimulacion = :idSimulacion AND sd.eventType IN ('ALERT', 'ERROR') ORDER BY sd.timestamp DESC")
    List<SensorData> findAnomaliesBySimulacion(@Param("idSimulacion") int idSimulacion);

    @Query("SELECT sd FROM SensorData sd WHERE sd.sensor.idSensor = :idSensor AND sd.eventType IN ('ALERT', 'ERROR') ORDER BY sd.timestamp DESC")
    List<SensorData> findSensorAnomalies(@Param("idSensor") int idSensor);

    @Query("SELECT COUNT(sd) FROM SensorData sd WHERE sd.simulacion.idSimulacion = :idSimulacion AND sd.eventType = :eventType")
    Long countBySimulacionAndEventType(@Param("idSimulacion") int idSimulacion, @Param("eventType") String eventType);
}

