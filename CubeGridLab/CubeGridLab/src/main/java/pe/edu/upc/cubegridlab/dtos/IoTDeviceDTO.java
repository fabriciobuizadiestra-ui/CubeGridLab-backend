package pe.edu.upc.cubegridlab.dtos;

import java.time.LocalDateTime;

public class IoTDeviceDTO {
    private int idDevice;
    private String name;
    private String type;
    private String description;
    private LocalDateTime registrationDate;
    private String status;
    private int idUser;

    public IoTDeviceDTO() {
    }

    public IoTDeviceDTO(int idDevice, String name, String type, String description, LocalDateTime registrationDate, String status, int idUser) {
        this.idDevice = idDevice;
        this.name = name;
        this.type = type;
        this.description = description;
        this.registrationDate = registrationDate;
        this.status = status;
        this.idUser = idUser;
    }

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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

