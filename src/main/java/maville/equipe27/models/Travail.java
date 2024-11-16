package maville.equipe27.models;

import com.google.gson.annotations.SerializedName;
import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Travail {
    private String id;

    private String nomIntervenant;

    private String quartier;

    private TravauxTypes type;

    private LocalDate debut;

    private LocalDate fin;

    public Travail() { }

    public Travail(String id, String nomIntervenant, String quartier, TravauxTypes type, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.nomIntervenant = nomIntervenant;
        this.quartier = quartier;
        this.type = type;

        this.debut = debut;
        this.fin = fin;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNomIntervenant() {
        return nomIntervenant;
    }

    public void setNomIntervenant(String nomIntervenant) {
        this.nomIntervenant = nomIntervenant;
    }

    public String getQuartier() {
        return this.quartier;
    }

    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    public TravauxTypes getType() {
        return type;
    }

    public void setType(TravauxTypes type) {
        this.type = type;
    }

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    public LocalDate getFin() {
        return fin;
    }

    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
    return String.format("ID: %s\nIntervenant: %s\nQuartier: %s\nType de travaux: %s\nDe: %s\nÀ: %s\n",
                id, (nomIntervenant == null ? "NA" : nomIntervenant), quartier, type, debut, fin);
    }
}
