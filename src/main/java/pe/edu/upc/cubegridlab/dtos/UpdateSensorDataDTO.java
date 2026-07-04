package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para actualizar un registro de datos de sensor existente
 * Contiene los datos que pueden ser modificados
 * Sin datos anidados (solo IDs de referencia)
 */
public class UpdateSensorDataDTO {
    private int idData;
    private Double value;
    private String timestamp;
    private String type;
    private String eventType;
    private String message;

    public UpdateSensorDataDTO() {
    }

    public UpdateSensorDataDTO(int idData, Double value, String timestamp, String type, String eventType, String message) {
        this.idData = idData;
        this.value = value;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
        this.message = message;
    }

    // Getters and Setters
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
}

