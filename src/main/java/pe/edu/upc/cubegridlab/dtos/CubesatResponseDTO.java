package pe.edu.upc.cubegridlab.dtos;

public class CubesatResponseDTO {
    private int idCubesat;
    private String nombre;
    private double masa;
    private String dimensiones;
    private String frecuencia;
    private String estado;
    private Integer idUsuario;

    public int getIdCubesat() {
        return idCubesat;
    }

    public void setIdCubesat(int idCubesat) {
        this.idCubesat = idCubesat;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getMasa() {
        return masa;
    }

    public void setMasa(double masa) {
        this.masa = masa;
    }

    public String getDimensiones() {
        return dimensiones;
    }

    public void setDimensiones(String dimensiones) {
        this.dimensiones = dimensiones;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }
}
