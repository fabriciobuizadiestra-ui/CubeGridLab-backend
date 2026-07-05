package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "sensors")
public class Sensor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSensor;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(length = 50)
    private String unitMeasurement;

    @Column(nullable = false)
    private Double minValue;

    @Column(nullable = false)
    private Double maxValue;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(length = 30, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_device")
    private IoTDevice iotDevice;

    @OneToMany(mappedBy = "sensor", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorDataList;

    public Sensor() {
    }

    public Sensor(String name, String type, String unitMeasurement, Double minValue, Double maxValue, LocalDateTime registrationDate, String status, IoTDevice iotDevice) {
        this.name = name;
        this.type = type;
        this.unitMeasurement = unitMeasurement;
        this.minValue = minValue;
        this.maxValue = maxValue;
        this.registrationDate = registrationDate;
        this.status = status;
        this.iotDevice = iotDevice;
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

    public IoTDevice getIotDevice() {
        return iotDevice;
    }

    public void setIotDevice(IoTDevice iotDevice) {
        this.iotDevice = iotDevice;
    }

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }

    /**
     * Determina el tipo de evento basado en el rango permitido del sensor
     */
    public String determineEventType(Double value) {
        if (value < minValue || value > maxValue) {
            if (value < minValue * 0.9 || value > maxValue * 1.1) {
                return "ERROR";
            }
            return "ALERT";
        }
        if (value < minValue * 0.95 || value > maxValue * 1.05) {
            return "WARNING";
        }
        return "NORMAL";
    }
}

