package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "lecciones")
public class Leccion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idLeccion;

    @Column(length = 100, nullable = false)
    private String titulo;

    @Column(columnDefinition = "TEXT")
    private String contenido;

    @ManyToOne
    @JoinColumn(name = "id_modulo")
    private Modulo modulo;

    // getters y setters

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public int getIdLeccion() {
        return idLeccion;
    }

    public void setIdLeccion(int idLeccion) {
        this.idLeccion = idLeccion;
    }

    public Modulo getModulo() {
        return modulo;
    }

    public void setModulo(Modulo modulo) {
        this.modulo = modulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
