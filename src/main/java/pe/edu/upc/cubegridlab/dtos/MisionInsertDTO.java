package pe.edu.upc.cubegridlab.dtos;

public class MisionInsertDTO {
    private String nombreMision;
    private String descripcionMision;
    private Integer idUsuario;
    private Integer idCubesat;

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

    public Integer getIdCubesat() {
        return idCubesat;
    }

    public void setIdCubesat(Integer idCubesat) {
        this.idCubesat = idCubesat;
    }
}
