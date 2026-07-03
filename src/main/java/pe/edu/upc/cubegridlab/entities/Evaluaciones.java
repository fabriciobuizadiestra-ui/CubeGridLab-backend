package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;
@Entity
public class Evaluaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idEvaluacion;
    private String titulo;
    @ManyToOne
    @JoinColumn(name = "idCurso")
    private Curso curso;

    public Evaluaciones() {
    }

    public Evaluaciones(int idEvaluacion, String titulo, Curso curso) {
        this.idEvaluacion = idEvaluacion;
        this.titulo = titulo;
        this.curso = curso;
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Curso getCurso() {
        return curso;
    }

    public void setCurso(Curso curso) {
        this.curso = curso;
    }
}
