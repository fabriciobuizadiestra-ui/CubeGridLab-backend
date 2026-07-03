package pe.edu.upc.cubegridlab.dtos;

public class LoginDTO {


    private String emailUser;
    private String passwordUser;

    public LoginDTO() {
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
}
