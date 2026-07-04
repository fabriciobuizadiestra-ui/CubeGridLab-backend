package pe.edu.upc.cubegridlab.dtos;

public class CantidadLeccionesPorModuloDTO {
    private int idModulo;
    private String nombreModulo;
    private int cantidadLecciones;

    public int getIdModulo() {
        return idModulo;
    }

    public void setIdModulo(int idModulo) {
        this.idModulo = idModulo;
    }

    public String getNombreModulo() {
        return nombreModulo;
    }

    public void setNombreModulo(String nombreModulo) {
        this.nombreModulo = nombreModulo;
    }

    public int getCantidadLecciones() {
        return cantidadLecciones;
    }

    public void setCantidadLecciones(int cantidadLecciones) {
        this.cantidadLecciones = cantidadLecciones;
    }
}
