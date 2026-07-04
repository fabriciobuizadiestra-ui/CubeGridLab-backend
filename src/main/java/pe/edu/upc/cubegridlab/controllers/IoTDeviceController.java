package pe.edu.upc.cubegridlab.controllers;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.*;
import pe.edu.upc.cubegridlab.entities.IoTDevice;
import pe.edu.upc.cubegridlab.entities.User;
import pe.edu.upc.cubegridlab.servicesinterfaces.IIoTDeviceService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IUserService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/iot-devices")
public class IoTDeviceController {

    @Autowired
    private IIoTDeviceService iotDeviceService;
    
    @Autowired
    private IUserService userService;

    @GetMapping
    public ResponseEntity<List<IoTDeviceDTO>> listar(){
        ModelMapper m=new ModelMapper();
        List<IoTDeviceDTO> listaDispositivos=iotDeviceService.list().stream()
                .map(y->m.map(y,IoTDeviceDTO.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(listaDispositivos);
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CreateIoTDeviceDTO dto) {
        // Buscar usuario
        Optional<User> userOptional = userService.listId(dto.getIdUser());
        if (!userOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Usuario no encontrado");
        }

        // Crear dispositivo
        IoTDevice device = new IoTDevice(
            dto.getName(),
            dto.getType(),
            dto.getDescription(),
            LocalDateTime.now(),
            dto.getStatus(),
            userOptional.get()
        );

        return ResponseEntity.ok(iotDeviceService.insert(device));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable int id) {
        ModelMapper m = new ModelMapper();
        Optional<IoTDevice> ioTDevice = iotDeviceService.listId(id);

        if (ioTDevice.isPresent()) {
            IoTDeviceDTO dto = m.map(ioTDevice.get(), IoTDeviceDTO.class);
            return ResponseEntity.ok(dto);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Dispositivo iot no encontrado");
        }
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UpdateIoTDeviceDTO dto) {
        Optional<IoTDevice> optional = iotDeviceService.listId(dto.getIdDevice());
        
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        IoTDevice device = optional.get();
        device.setName(dto.getName());
        device.setType(dto.getType());
        device.setDescription(dto.getDescription());
        device.setStatus(dto.getStatus());

        iotDeviceService.update(device);
        return ResponseEntity.ok("Dispositivo IoT actualizado correctamente");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        iotDeviceService.delete(id);
        return ResponseEntity.ok("Dispositivo IoT eliminado correctamente");
    }

    @GetMapping("/usuario/{idUser}")
    public ResponseEntity<?> buscarPorUsuario(@PathVariable int idUser) {

        ModelMapper m = new ModelMapper();

        List<IoTDevice> ioTDevices = iotDeviceService.listByUser(idUser);

        if (ioTDevices.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("No se encontraron dispositivos IoT para este usuario");
        }

        List<IoTDeviceDTO> dtoList = ioTDevices.stream()
                .map(device -> m.map(device, IoTDeviceDTO.class))
                .toList();

        return ResponseEntity.ok(dtoList);
    }

}

