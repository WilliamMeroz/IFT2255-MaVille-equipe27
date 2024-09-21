package maville.equipe27.models;

import maville.equipe27.enums.RoleChoices;

public class User {
    private String username;
    private String password;
    private RoleChoices role;

    public User(String username, String password, RoleChoices role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public RoleChoices getRole() {
        return role;
    }

    public void setRole(RoleChoices role) {
        this.role = role;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
