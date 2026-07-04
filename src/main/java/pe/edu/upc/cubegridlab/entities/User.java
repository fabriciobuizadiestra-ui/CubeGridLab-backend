package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;
    @Column(name = "nameUser", length = 20, nullable = false)
    private String nameUser;
    @Column(name = "lastNameUser", length = 30, nullable = false)
    private String lastNameUser;
    @Column(name = "emailUser", length = 50, nullable = false)
    private String emailUser;
    @Column(name = "passwordUser", length = 200, nullable = false)
    private String passwordUser;
    @Column(name = "registerDateUser", nullable = false)
    private LocalDate registerDateUser;
    @Column(name = "statusUser", length = 20, nullable = false)
    private Boolean statusUser;

    @ManyToOne
    @JoinColumn(name = "idInstitucion")
    private Institucion institucion;

    public User() {
    }

    public User(int idUser, String nameUser, String lastNameUser, String emailUser, String passwordUser, String typeUser, LocalDate registerDateUser, Boolean statusUser) {
        this.idUser = idUser;
        this.nameUser = nameUser;
        this.lastNameUser = lastNameUser;
        this.emailUser = emailUser;
        this.passwordUser = passwordUser;
        this.registerDateUser = registerDateUser;
        this.statusUser = statusUser;
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

    public Institucion getInstitucion() {
        return institucion;
    }

    public void setInstitucion(Institucion institucion) {
        this.institucion = institucion;
    }
}
