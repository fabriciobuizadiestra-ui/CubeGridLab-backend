package pe.edu.upc.cubegridlab.dtos;

public class CantidadMisionesPorEstudianteDTO {

    private String nombreUsuario;
    private int cantidadMisiones;

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public int getCantidadMisiones() {
        return cantidadMisiones;
    }

    public void setCantidadMisiones(int cantidadMisiones) {
        this.cantidadMisiones = cantidadMisiones;
    }
}