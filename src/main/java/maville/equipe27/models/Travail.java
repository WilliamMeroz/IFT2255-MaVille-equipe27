package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;

public class Travail {
    private String id;
    private String nomIntervenant;
    private TravauxTypes type;
    private LocalDate debut;
    private LocalDate fin;

    public Travail() { }

    public Travail(String id, String nomIntervenant, TravauxTypes type, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.nomIntervenant = nomIntervenant;
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
        return String.format("Largeur d'obstruction: %s\nType d'entrave: %s\nStatus des trottoir: %s\nStatus des pistes cyclables: %s\nDe: %s\nÀ: %s\n",
                impactWidth, impactType, sidewalkStatus, bikepathStatus, from, to);
    }
}
