package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para crear/registrar un nuevo dispositivo IoT
 * Solo contiene los datos mínimos necesarios
 * Sin datos anidados (solo IDs de referencia)
 */
public class CreateIoTDeviceDTO {
    private String name;
    private String type;
    private String description;
    private String status;
    private int idUser;

    public CreateIoTDeviceDTO() {
    }

    public CreateIoTDeviceDTO(String name, String type, String description, String status, int idUser) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.status = status;
        this.idUser = idUser;
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

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }
}

