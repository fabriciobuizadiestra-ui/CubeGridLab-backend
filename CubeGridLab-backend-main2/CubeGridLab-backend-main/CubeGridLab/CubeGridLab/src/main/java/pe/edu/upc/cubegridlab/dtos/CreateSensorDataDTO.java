package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para crear/registrar un nuevo registro de datos de sensor
 * Solo contiene los datos mínimos necesarios
 * Sin datos anidados (solo IDs de referencia)
 */
public class CreateSensorDataDTO {
    private Double value;
    private String timestamp;
    private String type;
    private String eventType;
    private String message;
    private int idSensor;
    private Integer idSimulacion;

    public CreateSensorDataDTO() {
    }

    public CreateSensorDataDTO(Double value, String timestamp, String type, String eventType, String message, int idSensor, Integer idSimulacion) {
        this.value = value;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
        this.message = message;
        this.idSensor = idSensor;
        this.idSimulacion = idSimulacion;
    }

    // Getters and Setters
    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
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

    public Integer getIdSimulacion() {
        return idSimulacion;
    }

    public void setIdSimulacion(Integer idSimulacion) {
        this.idSimulacion = idSimulacion;
    }
}

