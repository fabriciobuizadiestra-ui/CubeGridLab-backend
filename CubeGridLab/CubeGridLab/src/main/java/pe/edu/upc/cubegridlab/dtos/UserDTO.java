package pe.edu.upc.cubegridlab.dtos;

public class UserDTO {
    private int idUser;
    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String roleUser;
    private InstitucionDTO institucion;

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

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getRoleUser() {
        return roleUser;
    }

    public void setRoleUser(String roleUser) {
        this.roleUser = roleUser;
    }

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public InstitucionDTO getInstitucion() {
        return institucion;
    }

    public void setInstitucion(InstitucionDTO institucion) {
        this.institucion = institucion;
    }
}
