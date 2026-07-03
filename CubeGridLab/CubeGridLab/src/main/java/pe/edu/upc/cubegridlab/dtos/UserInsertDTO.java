package pe.edu.upc.cubegridlab.dtos;

import java.time.LocalDate;

public class UserInsertDTO {
    private int idUser;
    private String nameUser;
    private String lastNameUser;
    private String emailUser;
    private String passwordUser;
    private LocalDate registerDateUser;
    private Boolean statusUser;

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

    public String getLastNameUser() {
        return lastNameUser;
    }

    public void setLastNameUser(String lastNameUser) {
        this.lastNameUser = lastNameUser;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getPasswordUser() {
        return passwordUser;
    }

    public void setPasswordUser(String passwordUser) {
        this.passwordUser = passwordUser;
    }

    public LocalDate getRegisterDateUser() {
        return registerDateUser;
    }

    public void setRegisterDateUser(LocalDate registerDateUser) {
        this.registerDateUser = registerDateUser;
    }

    public Boolean getStatusUser() {
        return statusUser;
    }

    public void setStatusUser(Boolean statusUser) {
        this.statusUser = statusUser;
    }
}
