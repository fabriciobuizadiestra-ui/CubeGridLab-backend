package pe.edu.upc.cubegridlab.dtos;

public class MisionResponseDTO {
    private int idMision;
    private String nombreMision;
    private String descripcionMision;
    private Integer idUsuario;
    private String nombreUsuario;
    private Integer idCubesat;
    private String nombreCubesat;

    public int getIdMision() {
        return idMision;
    }

    public void setIdMision(int idMision) {
        this.idMision = idMision;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public void setNombreMision(String nombreMision) {
        this.nombreMision = nombreMision;
    }

    public String getDescripcionMision() {
        return descripcionMision;
    }

    public void setDescripcionMision(String descripcionMision) {
        this.descripcionMision = descripcionMision;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public Integer getIdCubesat() {
        return idCubesat;
    }

    public void setIdCubesat(Integer idCubesat) {
        this.idCubesat = idCubesat;
    }

    public String getNombreCubesat() {
        return nombreCubesat;
    }

    public void setNombreCubesat(String nombreCubesat) {
        this.nombreCubesat = nombreCubesat;
    }
}
