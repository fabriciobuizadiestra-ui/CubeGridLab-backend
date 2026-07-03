package pe.edu.upc.cubegridlab.dtos;

import java.time.LocalDateTime;

public class SimulacionResponseDTO {
    private int idSimulacion;
    private String nombre;
    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;
    private String tipo;
    private float altitud;
    private float inclinacion;
    private float velocidad;
    private String estado;
    private String resultado;
    private Integer idMision;
    private String nombreMision;
    private Integer idUsuario;
    private Integer idCubesat;

    public int getIdSimulacion() {
        return idSimulacion;
    }

    public void setIdSimulacion(int idSimulacion) {
        this.idSimulacion = idSimulacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public LocalDateTime getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(LocalDateTime fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public LocalDateTime getFecha_fin() {
        return fecha_fin;
    }

    public void setFecha_fin(LocalDateTime fecha_fin) {
        this.fecha_fin = fecha_fin;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public float getAltitud() {
        return altitud;
    }

    public void setAltitud(float altitud) {
        this.altitud = altitud;
    }

    public float getInclinacion() {
        return inclinacion;
    }

    public void setInclinacion(float inclinacion) {
        this.inclinacion = inclinacion;
    }

    public float getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(float velocidad) {
        this.velocidad = velocidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getResultado() {
        return resultado;
    }

    public void setResultado(String resultado) {
        this.resultado = resultado;
    }

    public Integer getIdMision() {
        return idMision;
    }

    public void setIdMision(Integer idMision) {
        this.idMision = idMision;
    }

    public String getNombreMision() {
        return nombreMision;
    }

    public void setNombreMision(String nombreMision) {
        this.nombreMision = nombreMision;
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
