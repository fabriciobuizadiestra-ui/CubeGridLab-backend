package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "misiones")
public class Misiones {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idMision;

    @Column(length = 100, nullable = false)
    private String nombreMision;

    @Column(length = 300)
    private String descripcionMision;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private User usuario;

    @ManyToOne
    @JoinColumn(name = "id_cubesat")
    private Cubesat cubesat;

    public Misiones() {
    }

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

    public User getUsuario() {
        return usuario;
    }

    public void setUsuario(User usuario) {
        this.usuario = usuario;
    }

    public Cubesat getCubesat() {
        return cubesat;
    }

    public void setCubesat(Cubesat cubesat) {
        this.cubesat = cubesat;
    }
}