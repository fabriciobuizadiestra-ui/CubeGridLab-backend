package pe.edu.upc.cubegridlab.servicesinterfaces;

import pe.edu.upc.cubegridlab.entities.SensorData;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ISensorDataService {

    List<SensorData> list();

    SensorData insert(SensorData sensorData);

    Optional<SensorData> listId(int id);

    void update(SensorData sensorData);

    void delete(int id);

    List<SensorData> listBySensor(int idSensor);

    List<SensorData> listBySimulacion(int idSimulacion);

    List<SensorData> listSensorDataByTimeRange(int idSensor, LocalDateTime startTime, LocalDateTime endTime);

    List<SensorData> listAnomaliesBySimulacion(int idSimulacion);

    List<SensorData> listSensorAnomalies(int idSensor);

    Long countAnomaliesBySimulacion(int idSimulacion);
}

