package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para crear/registrar un nuevo sensor
 * Solo contiene los datos mínimos necesarios
 * Sin datos anidados (solo ID del dispositivo)
 */
public class CreateSensorDTO {
    private String name;
    private String type;
    private String unitMeasurement;
    private Double minValue;
    private Double maxValue;
    private String status;
    private int idDevice;

    public CreateSensorDTO() {
    }

    public CreateSensorDTO(String name, String type, String unitMeasurement, Double minValue, Double maxValue, String status, int idDevice) {
        this.name = name;
        this.type = type;
        this.unitMeasurement = unitMeasurement;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.status = status;
        this.idDevice = idDevice;
    }

    // Getters and Setters
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

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }
}

