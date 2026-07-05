package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.SensorData;
import pe.edu.upc.cubegridlab.repositories.ISensorDataRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorDataService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SensorDataServiceImplement implements ISensorDataService {

    @Autowired
    private ISensorDataRepository sensorDataRepository;

    @Override
    public List<SensorData> list() {
        return sensorDataRepository.findAll();
    }

    @Override
    public SensorData insert(SensorData sensorData) {
        return sensorDataRepository.save(sensorData);
    }

    @Override
    public Optional<SensorData> listId(int id) {
        return sensorDataRepository.findById(id);
    }

    @Override
    public void update(SensorData sensorData) {
        sensorDataRepository.save(sensorData);
    }

    @Override
    public void delete(int id) {
        sensorDataRepository.deleteById(id);
    }

    @Override
    public List<SensorData> listBySensor(int idSensor) {
        return sensorDataRepository.findBySensorIdSensor(idSensor);
    }

    @Override
    public List<SensorData> listBySimulacion(int idSimulacion) {
        return sensorDataRepository.findBySimulacionIdSimulacion(idSimulacion);
    }

    @Override
    public List<SensorData> listSensorDataByTimeRange(int idSensor, LocalDateTime startTime, LocalDateTime endTime) {
        return sensorDataRepository.findSensorDataByTimeRange(idSensor, startTime, endTime);
    }

    @Override
    public List<SensorData> listAnomaliesBySimulacion(int idSimulacion) {
        return sensorDataRepository.findAnomaliesBySimulacion(idSimulacion);
    }

    @Override
    public List<SensorData> listSensorAnomalies(int idSensor) {
        return sensorDataRepository.findSensorAnomalies(idSensor);
    }

    @Override
    public Long countAnomaliesBySimulacion(int idSimulacion) {
        Long alertCount = sensorDataRepository.countBySimulacionAndEventType(idSimulacion, "ALERT");
        Long errorCount = sensorDataRepository.countBySimulacionAndEventType(idSimulacion, "ERROR");
        return alertCount + errorCount;
    }
}

