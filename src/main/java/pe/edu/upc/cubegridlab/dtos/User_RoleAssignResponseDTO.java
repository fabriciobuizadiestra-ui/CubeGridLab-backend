package pe.edu.upc.cubegridlab.dtos;

public class User_RoleAssignResponseDTO {
    private int idUserRole;
    private int idUser;
    private String nameUser;
    private int idRole;
    private String nameRole;

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
