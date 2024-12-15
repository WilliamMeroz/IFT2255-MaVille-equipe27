package maville.equipe27.models;

import maville.equipe27.enums.ProjetStatus;
import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;
import java.time.LocalTime;

public class Projet {
    private String intervenantCityIdentifier;
    private String titre;
    private String desc;
    private TravauxTypes type;
    private String[] quartiers;
    private LocalDate debut;
    private LocalDate fin;
    private LocalTime horaireDebut;
    private LocalTime horaireFin;
    private ProjetStatus status;
    private String[] rues;

    public Projet(String intervenantCityIdentifier, String titre, String desc, TravauxTypes type, String[] quartiers, LocalDate debut, LocalDate fin, LocalTime horaireDebut, LocalTime horaireFin, ProjetStatus status, String[] rues) {
        this.intervenantCityIdentifier = intervenantCityIdentifier;
        this.titre = titre;
        this.desc = desc;
        this.type = type;
        this.quartiers = quartiers;
        this.debut = debut;
        this.fin = fin;
        this.horaireDebut = horaireDebut;
        this.horaireFin = horaireFin;
        this.status = status;
        this.rues = rues;
    }

    public String getIntervenantCityIdentifier() {
        return intervenantCityIdentifier;
    }

    public void setIntervenantCityIdentifier(String intervenantCityIdentifier) {
        this.intervenantCityIdentifier = intervenantCityIdentifier;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public TravauxTypes getType() {
        return type;
    }

    public void setType(TravauxTypes type) {
        this.type = type;
    }

    public String[] getQuartiers() {
        return quartiers;
    }

    public void setQuartiers(String[] quartiers) {
        this.quartiers = quartiers;
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

    public LocalTime getHoraireDebut() {
        return horaireDebut;
    }

    public void setHoraireDebut(LocalTime horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    public LocalTime getHoraireFin() {
        return horaireFin;
    }

    public void setHoraireFin(LocalTime horaireFin) {
        this.horaireFin = horaireFin;
    }

    public ProjetStatus getStatus() {
        return status;
    }

    public void setStatus(ProjetStatus status) {
        this.status = status;
    }

    public String[] getRues() {
        return rues;
    }

    public void setRues(String[] rues) {
        this.rues = rues;
    }
}
