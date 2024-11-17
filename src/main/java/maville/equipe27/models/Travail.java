package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;

public class Travail {
    private String id;

    private String titre;

    private String nomIntervenant;

    private String quartier;

    private TravauxTypes type;

    private LocalDate debut;

    private LocalDate fin;

    public Travail() { }

    public Travail(String id, String nomIntervenant, String titre, String quartier, TravauxTypes type, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.nomIntervenant = nomIntervenant;
        this.titre = titre;
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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
    return String.format("ID: %s\nIntervenant: %s\nTitre: %s\nQuartier: %s\nType de travaux: %s\nDe: %s\nÀ: %s\n",
                id, (nomIntervenant == null ? "NA" : nomIntervenant), titre, quartier, type, debut, fin);
    }
}
