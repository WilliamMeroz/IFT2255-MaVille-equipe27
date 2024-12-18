package maville.equipe27.models;

import java.time.LocalDate;
import java.time.LocalTime;

public class PrefHoraire {
    private String email;
    private LocalTime de;
    private LocalTime a;
    private String quartier;

    public PrefHoraire(String email, LocalTime de, LocalTime a, String quartier) {
        this.email = email;
        this.de = de;
        this.a = a;
        this.quartier = quartier;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalTime getDe() {
        return de;
    }

    public void setDe(LocalTime de) {
        this.de = de;
    }

    public LocalTime getA() {
        return a;
    }

    public void setA(LocalTime a) {
        this.a = a;
    }

    public String getQuartier() {
        return quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }
}
