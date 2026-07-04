package pe.edu.upc.cubegridlab.dtos;

public class User_RoleAssignDTO {
    private int idUserRole;
    private int idUser;
    private int idRole;

    public User_RoleAssignDTO() {
    }

    public User_RoleAssignDTO(int idUser, int idRole) {
        this.idUser = idUser;
        this.idRole = idRole;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }
}

