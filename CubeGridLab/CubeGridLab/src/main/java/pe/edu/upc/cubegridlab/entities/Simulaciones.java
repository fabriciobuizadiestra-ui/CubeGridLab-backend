package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name="Simulaciones")
public class Simulaciones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idSimulacion;

    @Column(length = 100, nullable = false)
    private String nombre;

    private LocalDateTime fecha_inicio;
    private LocalDateTime fecha_fin;

    private String tipo;
    private float altitud;
    private float inclinacion;
    private float velocidad;

    @Column(length = 50)
    private String estado;
    @Column(length = 300)
    private String resultado;

    @ManyToOne
    @JoinColumn(name = "id_mision")
    private Misiones mision;

    @OneToMany(mappedBy = "simulacion", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<SensorData> sensorDataList;

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

    public Misiones getMision() {
        return mision;
    }

    public void setMision(Misiones mision) {
        this.mision = mision;
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

    public List<SensorData> getSensorDataList() {
        return sensorDataList;
    }

    public void setSensorDataList(List<SensorData> sensorDataList) {
        this.sensorDataList = sensorDataList;
    }
}
