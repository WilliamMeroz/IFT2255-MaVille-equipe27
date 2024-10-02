package maville.equipe27.models;

import maville.equipe27.enums.RoleChoices;

import java.time.LocalDate;

public class Resident extends User {

    private LocalDate dob;
    private String phone;
    private String address;

    // Copy constructor.
    public Resident(String email, String password, RoleChoices role, String firstname, String lastname,
                    LocalDate dob, String phone, String address) {
        super(email, password, role, firstname, lastname);
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
