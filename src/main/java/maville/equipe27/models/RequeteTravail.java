package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import com.google.gson.Gson;
import java.io.FileWriter;
import java.io.IOException;

public class RequeteTravail {
    private String titreTravail;
    private String description;
    private String typeTravail;
    private String dateDebut;

    public RequeteTravail(String titreTravail, String description, String typeTravail, String dateDebut) {
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

    public String getTypeTravail() {
        return typeTravail;
    }

    public void setTypeTravail(String typeTravail) {
        this.typeTravail = typeTravail;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }
}
