package pe.edu.upc.cubegridlab.servicesimplements;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pe.edu.upc.cubegridlab.dtos.SensorDataResponseDTO;
import pe.edu.upc.cubegridlab.dtos.SensorResponseDTO;
import pe.edu.upc.cubegridlab.entities.IoTDevice;
import pe.edu.upc.cubegridlab.entities.Sensor;
import pe.edu.upc.cubegridlab.entities.SensorData;
import pe.edu.upc.cubegridlab.entities.Simulaciones;
import pe.edu.upc.cubegridlab.servicesinterfaces.IIoTDeviceService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorDataService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISensorService;
import pe.edu.upc.cubegridlab.servicesinterfaces.ISimulacionesService;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DataGeneratorService {

    @Autowired
    private ISensorService sensorService;

    @Autowired
    private ISensorDataService sensorDataService;

    @Autowired
    private IIoTDeviceService iotDeviceService;

    @Autowired
    private ISimulacionesService simulacionesService;

    public SensorResponseDTO convertToSensorResponseDTO(Sensor sensor) {
        SensorResponseDTO.IoTDeviceBasicDTO deviceDTO = null;
        if (sensor.getIotDevice() != null) {
            deviceDTO = new SensorResponseDTO.IoTDeviceBasicDTO(
                sensor.getIotDevice().getIdDevice(),
                sensor.getIotDevice().getName(),
                sensor.getIotDevice().getType()
            );
        }

        return new SensorResponseDTO(
            sensor.getIdSensor(),
            sensor.getName(),
            sensor.getType(),
            sensor.getUnitMeasurement(),
            sensor.getMinValue(),
            sensor.getMaxValue(),
            sensor.getStatus(),
            sensor.getRegistrationDate().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            deviceDTO
        );
    }


    public SensorDataResponseDTO convertToSensorDataResponseDTO(SensorData sensorData) {
        SensorDataResponseDTO.SensorBasicDTO sensorDTO = null;
        if (sensorData.getSensor() != null) {
            sensorDTO = new SensorDataResponseDTO.SensorBasicDTO(
                sensorData.getSensor().getIdSensor(),
                sensorData.getSensor().getName(),
                sensorData.getSensor().getType(),
                sensorData.getSensor().getUnitMeasurement()
            );
        }

        SensorDataResponseDTO.SimulacionBasicDTO simulacionDTO = null;
        if (sensorData.getSimulacion() != null) {
            simulacionDTO = new SensorDataResponseDTO.SimulacionBasicDTO(
                sensorData.getSimulacion().getIdSimulacion(),
                sensorData.getSimulacion().getNombre(),
                sensorData.getSimulacion().getEstado()
            );
        }

        return new SensorDataResponseDTO(
            sensorData.getIdData(),
            sensorData.getValue(),
            sensorData.getTimestamp().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME),
            sensorData.getType(),
            sensorData.getEventType(),
            sensorData.getMessage(),
            sensorDTO,
            simulacionDTO
        );
    }


    public List<SensorData> generateTestData(int sensorId, int simulacionId) {
        List<SensorData> testData = new ArrayList<>();

        Optional<Sensor> sensorOptional = sensorService.listId(sensorId);
        Optional<Simulaciones> simulacionOptional = simulacionesService.listId(simulacionId);

        if (!sensorOptional.isPresent() || !simulacionOptional.isPresent()) {
            return testData;
        }

        Sensor sensor = sensorOptional.get();
        Simulaciones simulacion = simulacionOptional.get();

        LocalDateTime baseTime = LocalDateTime.now();

        // Datos de prueba para diferentes tipos de eventos
        double[][] testValues = {
            // NORMAL: dentro del rango
            {25.0, 26.5, 24.8, 25.2, 26.0},

            // WARNING: cerca de los límites (95-105% del rango)
            {sensor.getMinValue() * 0.94, sensor.getMaxValue() * 1.06},

            // ALERT: fuera del rango pero no crítico
            {sensor.getMinValue() - 5, sensor.getMaxValue() + 10},

            // ERROR: extremadamente fuera del rango
            {sensor.getMinValue() * 0.85, sensor.getMaxValue() * 1.15}
        };

        String[] eventTypes = {"NORMAL", "WARNING", "ALERT", "ERROR"};
        String[][] messages = {
            {"Temperatura normal", "Lectura estable", "Valor dentro del rango", "Condiciones óptimas", "Funcionamiento correcto"},
            {"Temperatura cerca del límite inferior", "Temperatura cerca del límite superior"},
            {"Temperatura fuera del rango permitido", "Valor crítico detectado"},
            {"ERROR CRÍTICO: Temperatura extremadamente baja", "ERROR CRÍTICO: Temperatura extremadamente alta"}
        };

        for (int i = 0; i < testValues.length; i++) {
            for (int j = 0; j < testValues[i].length; j++) {

                String eventType = sensor.determineEventType(testValues[i][j]);

                String message = messages[i][Math.min(j, messages[i].length - 1)];

                SensorData sensorData = new SensorData(
                        testValues[i][j],
                        baseTime.plusMinutes(j),
                        "SENSOR_READING",
                        eventType,
                        message,
                        sensor,
                        simulacion
                );

                testData.add(sensorData);
            }
        }

        return testData;
    }


    public List<SensorData> generateComprehensiveTestData(int simulacionId) {
        List<SensorData> allTestData = new ArrayList<>();

        // Buscar todos los sensores activos
        List<Sensor> activeSensors = sensorService.list().stream()
            .filter(sensor -> "ACTIVE".equals(sensor.getStatus()))
            .toList();

        for (Sensor sensor : activeSensors) {
            List<SensorData> sensorTestData = generateTestData(sensor.getIdSensor(), simulacionId);
            allTestData.addAll(sensorTestData);
        }

        return allTestData;
    }
}

