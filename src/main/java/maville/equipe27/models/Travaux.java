package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;

public class Travaux {
    private String id;
    private String titre;
    private String desc;
    private TravauxTypes type;
    private LocalDate debut;

    public Travaux() { }

    public Travaux(String id, String titre, String desc, TravauxTypes type, LocalDate debut) {
        this.id = id;
        this.titre = titre;
        this.desc = desc;
        this.type = type;
        this.debut = debut;
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

    public LocalDate getDebut() {
        return debut;
    }

    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }
}
