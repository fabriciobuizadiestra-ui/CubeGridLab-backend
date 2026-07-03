package pe.edu.upc.cubegridlab.dtos;

public class QuantityUsersByRoleDTO {
    private int quantityUsers;
    private String nameRole;

    public int getQuantityUsers() {
        return quantityUsers;
    }

    public void setQuantityUsers(int quantityUsers) {
        this.quantityUsers = quantityUsers;
    }

    public String getNameRole() {
        return nameRole;
    }

    public void setNameRole(String nameRole) {
        this.nameRole = nameRole;
    }
}
