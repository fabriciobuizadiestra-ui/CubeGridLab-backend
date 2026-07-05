package pe.edu.upc.cubegridlab.dtos;

/**
 * DTO para asignar un rol a un usuario después del registro
 */
public class AssignRoleDTO {
    private int idUser;
    private int idRole;

    public AssignRoleDTO() {
    }

    public AssignRoleDTO(int idUser, int idRole) {
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
}

