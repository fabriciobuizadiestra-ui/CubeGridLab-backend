package pe.edu.upc.cubegridlab.entities;

import jakarta.persistence.*;

@Entity
@Table(name = "user_role")
public class User_Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUserRole;

    @ManyToOne
    @JoinColumn(name = "idUser")
    private User user;

    @ManyToOne
    @JoinColumn(name = "idRole")
    private Role role;

    public User_Role() {
    }

    public User_Role(int idUserRole, User user, Role role) {
        this.idUserRole = idUserRole;
        this.user = user;
        this.role = role;
    }

    public int getIdUserRole() {
        return idUserRole;
    }

    public void setIdUserRole(int idUserRole) {
        this.idUserRole = idUserRole;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
