package pe.edu.upc.cubegridlab.dtos;

import java.time.LocalDateTime;

public class SensorDTO {
    private int idSensor;
    private String name;
    private String type;
    private String unitMeasurement;
    private Double minValue;
    private Double maxValue;
    private LocalDateTime registrationDate;
    private String status;
    private int idDevice;

    public SensorDTO() {
    }

    public SensorDTO(int idSensor, String name, String type, String unitMeasurement, Double minValue, Double maxValue, LocalDateTime registrationDate, String status, int idDevice) {
        this.idSensor = idSensor;
        this.name = name;
        this.type = type;
        this.unitMeasurement = unitMeasurement;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.registrationDate = registrationDate;
        this.status = status;
        this.idDevice = idDevice;
    }

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

    public LocalDateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(LocalDateTime registrationDate) {
        this.registrationDate = registrationDate;
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

