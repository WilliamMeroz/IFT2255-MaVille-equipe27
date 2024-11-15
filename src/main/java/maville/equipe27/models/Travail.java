package maville.equipe27.models;

import com.google.gson.annotations.SerializedName;
import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

public class Travail {
    @SerializedName("id")
    private String id;

    @SerializedName("organizationname")
    private String nomIntervenant;

    @SerializedName("reason_category")
    private String type;

    @SerializedName("duration_start_date")
    private Date debut;

    @SerializedName("duration_end_date")
    private Date fin;

    public Travail() { }

    public Travail(String id, String nomIntervenant, String type, Date debut, Date fin) {
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDebut() {
        return debut;
    }

    public void setDebut(Date debut) {
        this.debut = debut;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    @Override
    public String toString() {
        return String.format("ID: %s\nIntervenant: %s\nType de travaux: %s\nDe: %s\nÀ: %s\n",
                id, (nomIntervenant == null ? "NA" : nomIntervenant), type, debut, fin);
    }
}
