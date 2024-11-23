package maville.equipe27.models;

import com.google.gson.annotations.SerializedName;

public class Entrave {

    @SerializedName("streetimpactwidth")
    private String impactWidth;

    @SerializedName("streetimpacttype")
    private String impactType;

    @SerializedName("sidewalk_blockedtype")
    private String sidewalkStatus;

    @SerializedName("bikepath_blockedtype")
    private String bikepathStatus;

    @SerializedName("fromname")
    private String from;

    @SerializedName("toname")
    private String to;

    public Entrave() { }

    public Entrave(String impactWidth, String impactType, String sidewalkStatus, String bikepathStatus, String from, String to) {
        this.impactWidth = impactWidth;
        this.impactType = impactType;
        this.sidewalkStatus = sidewalkStatus;
        this.bikepathStatus = bikepathStatus;
        this.from = from;
        this.to = to;
    }

    public String getImpactWidth() {
        return impactWidth;
    }

    public void setImpactWidth(String impactWidth) {
        this.impactWidth = impactWidth;
    }

    public String getImpactType() {
        return impactType;
    }

    public void setImpactType(String impactType) {
        this.impactType = impactType;
    }

    public String getSidewalkStatus() {
        return sidewalkStatus;
    }

    public void setSidewalkStatus(String sidewalkStatus) {
        this.sidewalkStatus = sidewalkStatus;
    }

    public String getBikepathStatus() {
        return bikepathStatus;
    }

    public void setBikepathStatus(String bikepathStatus) {
        this.bikepathStatus = bikepathStatus;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    @Override
    public String toString() {
        return String.format("Largeur d'obstruction: %s\nType d'entrave: %s\nStatus des trottoir: %s\nStatus des pistes cyclables: %s\nDe: %s\nÀ: %s\n",
                impactWidth, impactType, sidewalkStatus, bikepathStatus, from, to);
    }
}
