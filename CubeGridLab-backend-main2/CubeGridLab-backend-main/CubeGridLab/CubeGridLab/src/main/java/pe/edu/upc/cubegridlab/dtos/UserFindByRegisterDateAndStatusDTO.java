package pe.edu.upc.cubegridlab.dtos;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class UserFindByRegisterDateAndStatusDTO {
    private String emailUser;
    private LocalDate registerDateUser;
    private Boolean statusUser;

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
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
