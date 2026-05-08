package pe.edu.upc.cubegridlab.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pe.edu.upc.cubegridlab.dtos.CreateSensorDataDTO;
import pe.edu.upc.cubegridlab.dtos.UpdateSensorDataDTO;
import pe.edu.upc.cubegridlab.dtos.SensorDataResponseDTO;
import pe.edu.upc.cubegridlab.entities.Sensor;
import pe.edu.upc.cubegridlab.entities.SensorData;
import pe.edu.upc.cubegridlab.entities.Simulaciones;
import pe.edu.upc.cubegridlab.servicesimplements.DataGeneratorService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorDataService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISimulacionesService;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/sensor-data")
public class SensorDataController {

    @Autowired
    private ISensorDataService sensorDataService;

    @Autowired
    private ISensorService sensorService;

    @Autowired
    private ISimulacionesService simulacionesService;

    @Autowired
    private DataGeneratorService dataGeneratorService;

    @GetMapping
    public List<SensorDataResponseDTO> listar() {
        return sensorDataService.list().stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @PostMapping
    public ResponseEntity<?> registrar(@RequestBody CreateSensorDataDTO dto) {
        // Buscar sensor
        Optional<Sensor> sensorOptional = sensorService.listId(dto.getIdSensor());
        if (!sensorOptional.isPresent()) {
            return ResponseEntity.badRequest().body("Sensor no encontrado");
        }

        // Buscar simulación (opcional)
        Simulaciones simulacion = null;
        if (dto.getIdSimulacion() != null) {
            Optional<Simulaciones> simOptional = simulacionesService.listId(dto.getIdSimulacion());
            if (simOptional.isPresent()) {
                simulacion = simOptional.get();
            }
        }

        // Parsear timestamp
        LocalDateTime timestamp;
        try {
            timestamp = LocalDateTime.parse(dto.getTimestamp());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de timestamp inválido. Use: ISO 8601 (2026-05-07T10:45:00)");
        }

        // Crear dato de sensor
        SensorData sensorData = new SensorData(
            dto.getValue(),
            timestamp,
            dto.getType(),
            dto.getEventType(),
            dto.getMessage(),
            sensorOptional.get(),
            simulacion
        );

        SensorData savedSensorData = sensorDataService.insert(sensorData);
        SensorDataResponseDTO responseDTO = dataGeneratorService.convertToSensorDataResponseDTO(savedSensorData);

        return ResponseEntity.ok(responseDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> buscar(@PathVariable int id) {
        Optional<SensorData> sensorData = sensorDataService.listId(id);

        if (sensorData.isPresent()) {
            SensorDataResponseDTO responseDTO = dataGeneratorService.convertToSensorDataResponseDTO(sensorData.get());
            return ResponseEntity.ok(responseDTO);
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody UpdateSensorDataDTO dto) {
        Optional<SensorData> optional = sensorDataService.listId(dto.getIdData());

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        SensorData sensorData = optional.get();
        sensorData.setValue(dto.getValue());

        try {
            sensorData.setTimestamp(LocalDateTime.parse(dto.getTimestamp()));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Formato de timestamp inválido");
        }

        sensorData.setType(dto.getType());
        sensorData.setEventType(dto.getEventType());
        sensorData.setMessage(dto.getMessage());

        sensorDataService.update(sensorData);
        SensorDataResponseDTO responseDTO = dataGeneratorService.convertToSensorDataResponseDTO(sensorData);

        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id) {
        sensorDataService.delete(id);
        return ResponseEntity.ok("Datos del sensor eliminados correctamente");
    }

    @GetMapping("/sensor/{idSensor}")
    public List<SensorDataResponseDTO> listarPorSensor(@PathVariable int idSensor) {
        return sensorDataService.listBySensor(idSensor).stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/simulacion/{idSimulacion}")
    public List<SensorDataResponseDTO> listarPorSimulacion(@PathVariable int idSimulacion) {
        return sensorDataService.listBySimulacion(idSimulacion).stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/sensor/{idSensor}/rango-tiempo")
    public List<SensorDataResponseDTO> listarPorRangoTiempo(
            @PathVariable int idSensor,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime startTime,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime endTime) {
        return sensorDataService.listSensorDataByTimeRange(idSensor, startTime, endTime).stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/simulacion/{idSimulacion}/anomalias")
    public List<SensorDataResponseDTO> listarAnomaliasPorSimulacion(@PathVariable int idSimulacion) {
        return sensorDataService.listAnomaliesBySimulacion(idSimulacion).stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/sensor/{idSensor}/anomalias")
    public List<SensorDataResponseDTO> listarAnomaliasPorSensor(@PathVariable int idSensor) {
        return sensorDataService.listSensorAnomalies(idSensor).stream()
            .map(dataGeneratorService::convertToSensorDataResponseDTO)
            .collect(Collectors.toList());
    }

    @GetMapping("/simulacion/{idSimulacion}/contar-anomalias")
    public ResponseEntity<?> contarAnomaliasPorSimulacion(@PathVariable int idSimulacion) {
        Long count = sensorDataService.countAnomaliesBySimulacion(idSimulacion);
        return ResponseEntity.ok("Total de anomalías: " + count);
    }
}
