package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "instituciones")
public class Institucion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idInstitucion;

    @Column(length = 100, nullable = false)
    private String nombre;

    @Column(length = 50)
    private String tipo;

    @OneToMany(mappedBy = "institucion")
    private List<User> usuarios;

    // getters y setters


    public int getIdInstitucion() {
        return idInstitucion;
    }

    public void setIdInstitucion(int idInstitucion) {
        this.idInstitucion = idInstitucion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public List<User> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<User> usuarios) {
        this.usuarios = usuarios;
    }
}