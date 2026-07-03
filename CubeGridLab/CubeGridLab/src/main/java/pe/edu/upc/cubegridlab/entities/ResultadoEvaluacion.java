package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "resultado_evaluacion")
public class ResultadoEvaluacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idResultado;
    private String puntaje;
    private String descripcion;
    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;
    @ManyToOne
    @JoinColumn(name = "idEvalucion")
    private Evaluaciones evaluaciones;

    public ResultadoEvaluacion() {
    }

    public ResultadoEvaluacion(int idResultado, String puntaje, String descripcion, User user, Evaluaciones evaluaciones) {
        this.idResultado = idResultado;
        this.puntaje = puntaje;
        this.descripcion = descripcion;
        this.user = user;
        this.evaluaciones = evaluaciones;
    }

    public int getIdResultado() {
        return idResultado;
    }

    public void setIdResultado(int idResultado) {
        this.idResultado = idResultado;
    }

    public String getPuntaje() {
        return puntaje;
    }

    public void setPuntaje(String puntaje) {
        this.puntaje = puntaje;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Evaluaciones getEvaluaciones() {
        return evaluaciones;
    }

    public void setEvaluaciones(Evaluaciones evaluaciones) {
        this.evaluaciones = evaluaciones;
    }
}
