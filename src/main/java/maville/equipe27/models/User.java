package maville.equipe27.models;

import maville.equipe27.enums.RoleChoices;

public abstract class User {
    private String email;
    private String password;
    private RoleChoices role;
    private String firstname;
    private String lastname;

    public User(String email, String password, RoleChoices role, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
