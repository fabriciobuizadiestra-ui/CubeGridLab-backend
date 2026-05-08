package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para actualizar un sensor existente
 * Contiene los datos que pueden ser modificados
 * Sin datos anidados (solo ID del dispositivo)
 */
public class UpdateSensorDTO {
    private int idSensor;
    private String name;
    private String type;
    private String unitMeasurement;
    private Double minValue;
    private Double maxValue;
    private String status;

    public UpdateSensorDTO() {
    }

    public UpdateSensorDTO(int idSensor, String name, String type, String unitMeasurement, Double minValue, Double maxValue, String status) {
        this.idSensor = idSensor;
        this.name = name;
        this.type = type;
        this.unitMeasurement = unitMeasurement;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.status = status;
    }

    // Getters and Setters
    public int getIdSensor() {
        return idSensor;
    }

    public void setIdSensor(int idSensor) {
        this.idSensor = idSensor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnitMeasurement() {
        return unitMeasurement;
    }

    public void setUnitMeasurement(String unitMeasurement) {
        this.unitMeasurement = unitMeasurement;
    }

    public Double getMinValue() {
        return minValue;
    }

    public void setMinValue(Double minValue) {
        this.minValue = minValue;
    }

    public Double getMaxValue() {
        return maxValue;
    }

    public void setMaxValue(Double maxValue) {
        this.maxValue = maxValue;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

