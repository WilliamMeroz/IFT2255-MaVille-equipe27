package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;

public class RequeteTravail {
    private String titreTravail;
    private String description;
    private TravauxTypes typeTravail;
    private LocalDate dateDebut;

    public RequeteTravail(String titreTravail, String description, TravauxTypes typeTravail, LocalDate dateDebut) {
        this.titreTravail = titreTravail;
        this.description = description;
        this.typeTravail = typeTravail;
        this.dateDebut = dateDebut;
    }

    public String getTitreTravail() {
        return titreTravail;
    }

    public void setTitreTravail(String titreTravail) {
        this.titreTravail = titreTravail;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TravauxTypes getTypeTravail() {
        return typeTravail;
    }

    public void setTypeTravail(TravauxTypes typeTravail) {
        this.typeTravail = typeTravail;
    }

    public LocalDate getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }
}
