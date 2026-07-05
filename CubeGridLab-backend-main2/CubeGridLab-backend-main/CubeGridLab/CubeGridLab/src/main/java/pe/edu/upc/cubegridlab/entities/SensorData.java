package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "sensor_data")
public class SensorData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idData;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private LocalDateTime timestamp;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(length = 30, nullable = false)
    private String eventType;

    @Column(length = 500)
    private String message;

    @ManyToOne
    @JoinColumn(name = "id_sensor")
    private Sensor sensor;

    @ManyToOne
    @JoinColumn(name = "id_simulacion")
    private Simulaciones simulacion;

    public SensorData() {
    }

    public SensorData(Double value, LocalDateTime timestamp, String type, String eventType, String message, Sensor sensor, Simulaciones simulacion) {
        this.value = value;
        this.timestamp = timestamp;
        this.type = type;
        this.eventType = eventType;
        this.message = message;
        this.sensor = sensor;
        this.simulacion = simulacion;
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

    public Sensor getSensor() {
        return sensor;
    }

    public void setSensor(Sensor sensor) {
        this.sensor = sensor;
    }

    public Simulaciones getSimulacion() {
        return simulacion;
    }

    public void setSimulacion(Simulaciones simulacion) {
        this.simulacion = simulacion;
    }
}

