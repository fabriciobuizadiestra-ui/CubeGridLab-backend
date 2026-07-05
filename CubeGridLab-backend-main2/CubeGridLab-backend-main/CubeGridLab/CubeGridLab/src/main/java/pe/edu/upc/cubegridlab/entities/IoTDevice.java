package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "iot_devices")
public class IoTDevice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idDevice;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 50, nullable = false)
    private String type;

    @Column(length = 300)
    private String description;

    @Column(nullable = false)
    private LocalDateTime registrationDate;

    @Column(length = 30, nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @OneToMany(mappedBy = "iotDevice", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Sensor> sensores;

    public IoTDevice() {
    }

    public IoTDevice(String name, String type, String description, LocalDateTime registrationDate, String status, User usuario) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.registrationDate = registrationDate;
        this.status = status;
        this.usuario = usuario;
    }

    // Getters and Setters
    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public List<Sensor> getSensores() {
        return sensores;
    }

    public void setSensores(List<Sensor> sensores) {
        this.sensores = sensores;
    }
}

