package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.Sensor;
import pe.edu.upc.cubegridlab.repositories.ISensorRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorService;

import java.util.List;
import java.util.Optional;

@Service
public class SensorServiceImplement implements ISensorService {

    @Autowired
    private ISensorRepository sensorRepository;

    @Override
    public List<Sensor> list() {
        return sensorRepository.findAll();
    }

    @Override
    public Sensor insert(Sensor sensor) {
        return sensorRepository.save(sensor);
    }

    @Override
    public Optional<Sensor> listId(int id) {
        return sensorRepository.findById(id);
    }

    @Override
    public void update(Sensor sensor) {
        sensorRepository.save(sensor);
    }

    @Override
    public void delete(int id) {
        sensorRepository.deleteById(id);
    }

    @Override
    public List<Sensor> listByDevice(int idDevice) {
        return sensorRepository.findByIotDeviceIdDevice(idDevice);
    }

    @Override
    public List<Sensor> listActiveSensorsByDevice(int idDevice) {
        return sensorRepository.findActiveSensorsByDevice(idDevice, "ACTIVE");
    }

    @Override
    public List<Sensor> listSensorsByUser(int idUser) {
        return sensorRepository.findSensorsByUser(idUser);
    }
}

