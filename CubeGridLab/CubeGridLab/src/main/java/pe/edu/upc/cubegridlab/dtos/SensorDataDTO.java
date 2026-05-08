package pe.edu.upc.cubegridlab.dtos;

import java.time.LocalDateTime;

public class SensorDataDTO {
    private int idData;
    private Double value;
    private LocalDateTime timestamp;
    private String type;
    private String eventType;
    private String message;
    private int idSensor;
    private int idSimulacion;

    public SensorDataDTO() {
    }

    public SensorDataDTO(int idData, Double value, LocalDateTime timestamp, String type, String eventType, String message, int idSensor, int idSimulacion) {
        this.idData = idData;
        this.value = value;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
        this.message = message;
        this.idSensor = idSensor;
        this.idSimulacion = idSimulacion;
    }

    public int getIdData() {
        return idData;
    }

    public void setIdData(int idData) {
        this.idData = idData;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getEventType() {
        return eventType;
    }

    public void setEventType(String eventType) {
        this.eventType = eventType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public int getIdSimulacion() {
        return idSimulacion;
    }

    public void setIdSimulacion(int idSimulacion) {
        this.idSimulacion = idSimulacion;
    }
}

