package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "cubesats")
public class Cubesat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idCubesat;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(nullable = false)
    private double masa;

    @Column(length = 100)
    private String dimensiones;

    @Column(length = 50)
    private String frecuencia;

    @Column(length = 30)
    private String estado;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    public Cubesat() {
    }

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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }
}
