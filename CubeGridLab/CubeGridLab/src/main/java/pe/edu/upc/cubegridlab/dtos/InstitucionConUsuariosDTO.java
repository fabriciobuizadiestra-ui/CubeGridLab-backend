package pe.edu.upc.cubegridlab.dtos;

public class InstitucionConUsuariosDTO {
    private int idInstitucion;
    private String nombre;
    private int cantidadUsuarios;

    public InstitucionConUsuariosDTO() {
    }

    public InstitucionConUsuariosDTO(int idInstitucion, String nombre, String tipo, int cantidadUsuarios) {
        this.idInstitucion = idInstitucion;
        this.nombre = nombre;

        this.cantidadUsuarios = cantidadUsuarios;
    }

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

    public int getCantidadUsuarios() {
        return cantidadUsuarios;
    }

    public void setCantidadUsuarios(int cantidadUsuarios) {
        this.cantidadUsuarios = cantidadUsuarios;
    }
}
