package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.entities.IoTDevice;
import pe.edu.upc.cubegridlab.repositories.IIoTDeviceRepository;
import pe.edu.upc.cubegridlab.servicesinterfaces.IIoTDeviceService;

import java.util.List;
import java.util.Optional;

@Service
public class IoTDeviceServiceImplement implements IIoTDeviceService {

    @Autowired
    private IIoTDeviceRepository iotDeviceRepository;

    @Override
    public List<IoTDevice> list() {
        return iotDeviceRepository.findAll();
    }

    @Override
    public IoTDevice insert(IoTDevice device) {
        return iotDeviceRepository.save(device);
    }

    @Override
    public Optional<IoTDevice> listId(int id) {
        return iotDeviceRepository.findById(id);
    }

    @Override
    public void update(IoTDevice device) {
        iotDeviceRepository.save(device);
    }

    @Override
    public void delete(int id) {
        iotDeviceRepository.deleteById(id);
    }

    @Override
    public List<IoTDevice> listByUser(int idUser) {
        return iotDeviceRepository.findByUsuarioIdUser(idUser);
    }

}

