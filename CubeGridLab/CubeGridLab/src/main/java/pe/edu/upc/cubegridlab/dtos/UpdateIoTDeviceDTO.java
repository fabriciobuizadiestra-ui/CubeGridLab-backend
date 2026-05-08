package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para actualizar un dispositivo IoT existente
 * Contiene los datos que pueden ser modificados
 * Sin datos anidados (solo IDs de referencia)
 */
public class UpdateIoTDeviceDTO {
    private int idDevice;
    private String name;
    private String type;
    private String description;
    private String status;

    public UpdateIoTDeviceDTO() {
    }

    public UpdateIoTDeviceDTO(int idDevice, String name, String type, String description, String status) {
        this.idDevice = idDevice;
        this.name = name;
        this.type = type;
        this.description = description;
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

