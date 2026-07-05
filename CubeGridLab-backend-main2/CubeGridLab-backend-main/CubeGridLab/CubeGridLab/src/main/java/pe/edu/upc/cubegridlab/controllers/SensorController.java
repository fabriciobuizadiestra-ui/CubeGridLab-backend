package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.CreateSensorDTO;
import pe.edu.upc.cubegridlab.dtos.UpdateSensorDTO;
import pe.edu.upc.cubegridlab.dtos.SensorResponseDTO;
import pe.edu.upc.cubegridlab.entities.IoTDevice;
import pe.edu.upc.cubegridlab.entities.Sensor;
import pe.edu.upc.cubegridlab.servicesimplements.DataGeneratorService;
import pe.edu.upc.cubegridlab.servicesinterfaces.IIoTDeviceService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorDataService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensors")
public class SensorController {

    @Autowired
    private ISensorService sensorService;

    @Autowired
    private IIoTDeviceService iotDeviceService;

    @Autowired
    private DataGeneratorService dataGeneratorService;

    @Autowired
    private ISensorDataService sensorDataService;

    @GetMapping
    public List<SensorResponseDTO> listar() {
        return sensorService.list().stream()
            .map(dataGeneratorService::convertToSensorResponseDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CreateSensorDTO dto) {
        // Buscar dispositivo
        Optional<IoTDevice> deviceOptional = iotDeviceService.listId(dto.getIdDevice());
        if (!deviceOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Dispositivo IoT no encontrado");
        }

        // Crear sensor
        Sensor sensor = new Sensor(
            dto.getName(),
            dto.getType(),
            dto.getUnitMeasurement(),
            dto.getMinValue(),
            dto.getMaxValue(),
            LocalDateTime.now(),
            dto.getStatus(),
            deviceOptional.get()
        );

        Sensor savedSensor = sensorService.insert(sensor);
        SensorResponseDTO responseDTO = dataGeneratorService.convertToSensorResponseDTO(savedSensor);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<Sensor> sensor = sensorService.listId(id);

        if (sensor.isPresent()) {
            SensorResponseDTO responseDTO = dataGeneratorService.convertToSensorResponseDTO(sensor.get());
            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UpdateSensorDTO dto) {
        Optional<Sensor> optional = sensorService.listId(dto.getIdSensor());

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Sensor sensor = optional.get();
        sensor.setName(dto.getName());
        sensor.setType(dto.getType());
        sensor.setUnitMeasurement(dto.getUnitMeasurement());
        sensor.setMinValue(dto.getMinValue());
        sensor.setMaxValue(dto.getMaxValue());
        sensor.setStatus(dto.getStatus());

        sensorService.update(sensor);
        SensorResponseDTO responseDTO = dataGeneratorService.convertToSensorResponseDTO(sensor);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        sensorService.delete(id);
        return ResponseEntity.ok("Sensor eliminado correctamente");
    }

    @GetMapping("/dispositivo/{idDevice}")
    public List<SensorResponseDTO> listarPorDispositivo(@PathVariable int idDevice) {
        return sensorService.listByDevice(idDevice).stream()
            .map(dataGeneratorService::convertToSensorResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/dispositivo/{idDevice}/activos")
    public List<SensorResponseDTO> listarActivosPorDispositivo(@PathVariable int idDevice) {
        return sensorService.listActiveSensorsByDevice(idDevice).stream()
            .map(dataGeneratorService::convertToSensorResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/usuario/{idUser}")
    public List<SensorResponseDTO> listarPorUsuario(@PathVariable int idUser) {
        return sensorService.listSensorsByUser(idUser).stream()
            .map(dataGeneratorService::convertToSensorResponseDTO)
            .collect(Collectors.toList());
    }

    @PostMapping("/generar-datos-prueba/{idSensor}/{idSimulacion}")
    public ResponseEntity<?> generarDatosPrueba(@PathVariable int idSensor, @PathVariable int idSimulacion) {
        List<pe.edu.upc.cubegridlab.entities.SensorData> testData = dataGeneratorService.generateTestData(idSensor, idSimulacion);

        if (testData.isEmpty()) {
            return ResponseEntity.badRequest().body("No se pudieron generar datos de prueba. Verifique que el sensor y simulación existan.");
        }

        // Guardar los datos de prueba
        List<pe.edu.upc.cubegridlab.entities.SensorData> savedData = new java.util.ArrayList<>();
        for (pe.edu.upc.cubegridlab.entities.SensorData data : testData) {
            savedData.add(sensorDataService.insert(data));
        }

        // Convertir a DTOs de respuesta
        List<pe.edu.upc.cubegridlab.dtos.SensorDataResponseDTO> responseDTOs = savedData.stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(java.util.Map.of(
            "message", "Datos de prueba generados exitosamente",
            "totalRegistros", responseDTOs.size(),
            "datos", responseDTOs
        ));
    }

    @PostMapping("/generar-datos-completos/{idSimulacion}")
    public ResponseEntity<?> generarDatosCompletos(@PathVariable int idSimulacion) {
        List<pe.edu.upc.cubegridlab.entities.SensorData> testData = dataGeneratorService.generateComprehensiveTestData(idSimulacion);

        if (testData.isEmpty()) {
            return ResponseEntity.badRequest().body("No se pudieron generar datos de prueba. Verifique que existan sensores activos y la simulación.");
        }

        // Guardar los datos de prueba
        List<pe.edu.upc.cubegridlab.entities.SensorData> savedData = new java.util.ArrayList<>();
        for (pe.edu.upc.cubegridlab.entities.SensorData data : testData) {
            savedData.add(sensorDataService.insert(data));
        }

        // Convertir a DTOs de respuesta
        List<pe.edu.upc.cubegridlab.dtos.SensorDataResponseDTO> responseDTOs = savedData.stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());

        return ResponseEntity.ok(java.util.Map.of(
            "message", "Datos de prueba completos generados exitosamente",
            "totalRegistros", responseDTOs.size(),
            "datos", responseDTOs
        ));
    }
}
