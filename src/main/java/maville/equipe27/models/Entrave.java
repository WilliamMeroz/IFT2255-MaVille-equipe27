package maville.equipe27.models;

import com.google.gson.annotations.SerializedName;

/**
 * La classe {@code Entrave} représente une entrave à la circulation sur une rue, y compris les informations
 * concernant l'impact de l'entrave sur la largeur de la rue, le type d'entrave, l'état des trottoirs, des pistes cyclables,
 * ainsi que les points de départ et d'arrivée de l'entrave.
 */
public class Entrave {

    // Attributs

    /**
     * Largeur de l'impact de l'entrave sur la rue.
     */
    @SerializedName("streetimpactwidth")
    private String impactWidth;

    /**
     * Type d'impact ou d'entrave sur la rue (par exemple, construction, accident, etc.).
     */
    @SerializedName("streetimpacttype")
    private String impactType;

    /**
     * Statut de l'entrave concernant les trottoirs (par exemple, obstrué, libre).
     */
    @SerializedName("sidewalk_blockedtype")
    private String sidewalkStatus;

    /**
     * Statut de l'entrave concernant les pistes cyclables (par exemple, obstrué, libre).
     */
    @SerializedName("bikepath_blockedtype")
    private String bikepathStatus;

    /**
     * Nom de la rue ou de l'endroit où commence l'entrave.
     */
    @SerializedName("fromname")
    private String from;

    /**
     * Nom de la rue ou de l'endroit où se termine l'entrave.
     */
    @SerializedName("toname")
    private String to;

    // Constructeurs

    /**
     * Constructeur sans arguments de la classe {@code Entrave}.
     * Ce constructeur est utilisé pour la désérialisation JSON, notamment avec Gson.
     */
    public Entrave() { }

    /**
     * Constructeur de la classe {@code Entrave} pour initialiser tous les attributs.
     *
     * @param impactWidth La largeur de l'impact de l'entrave sur la rue.
     * @param impactType Le type d'impact ou d'entrave sur la rue.
     * @param sidewalkStatus Le statut des trottoirs (par exemple, obstrué, libre).
     * @param bikepathStatus Le statut des pistes cyclables (par exemple, obstrué, libre).
     * @param from Le nom de la rue de départ de l'entrave.
     * @param to Le nom de la rue de fin de l'entrave.
     */
    public Entrave(String impactWidth, String impactType, String sidewalkStatus, String bikepathStatus, String from, String to) {
        this.impactWidth = impactWidth;
        this.impactType = impactType;
        this.sidewalkStatus = sidewalkStatus;
        this.bikepathStatus = bikepathStatus;
        this.from = from;
        this.to = to;
    }

    // Méthodes

    /**
     * Retourne la largeur de l'impact de l'entrave sur la rue.
     *
     * @return La largeur de l'impact.
     */
    public String getImpactWidth() {
        return impactWidth;
    }

    /**
     * Définit la largeur de l'impact de l'entrave sur la rue.
     *
     * @param impactWidth La nouvelle largeur de l'impact.
     */
    public void setImpactWidth(String impactWidth) {
        this.impactWidth = impactWidth;
    }

    /**
     * Retourne le type d'impact ou d'entrave sur la rue.
     *
     * @return Le type d'impact.
     */
    public String getImpactType() {
        return impactType;
    }

    /**
     * Définit le type d'impact ou d'entrave sur la rue.
     *
     * @param impactType Le nouveau type d'impact.
     */
    public void setImpactType(String impactType) {
        this.impactType = impactType;
    }

    /**
     * Retourne le statut des trottoirs concernant l'entrave.
     *
     * @return Le statut des trottoirs.
     */
    public String getSidewalkStatus() {
        return sidewalkStatus;
    }

    /**
     * Définit le statut des trottoirs concernant l'entrave.
     *
     * @param sidewalkStatus Le nouveau statut des trottoirs.
     */
    public void setSidewalkStatus(String sidewalkStatus) {
        this.sidewalkStatus = sidewalkStatus;
    }

    /**
     * Retourne le statut des pistes cyclables concernant l'entrave.
     *
     * @return Le statut des pistes cyclables.
     */
    public String getBikepathStatus() {
        return bikepathStatus;
    }

    /**
     * Définit le statut des pistes cyclables concernant l'entrave.
     *
     * @param bikepathStatus Le nouveau statut des pistes cyclables.
     */
    public void setBikepathStatus(String bikepathStatus) {
        this.bikepathStatus = bikepathStatus;
    }

    /**
     * Retourne le nom de la rue de départ de l'entrave.
     *
     * @return Le nom de la rue de départ.
     */
    public String getFrom() {
        return from;
    }

    /**
     * Définit le nom de la rue de départ de l'entrave.
     *
     * @param from Le nouveau nom de la rue de départ.
     */
    public void setFrom(String from) {
        this.from = from;
    }

    /**
     * Retourne le nom de la rue de fin de l'entrave.
     *
     * @return Le nom de la rue de fin.
     */
    public String getTo() {
        return to;
    }

    /**
     * Définit le nom de la rue de fin de l'entrave.
     *
     * @param to Le nouveau nom de la rue de fin.
     */
    public void setTo(String to) {
        this.to = to;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères des informations de l'entrave.
     *
     * @return La chaîne de caractères représentant les informations de l'entrave.
     */
    @Override
    public String toString() {
        return String.format("Largeur d'obstruction: %s\nType d'entrave: %s\nStatus des trottoirs: %s\nStatus des pistes cyclables: %s\nDe: %s\nÀ: %s\n",
                impactWidth, impactType, sidewalkStatus, bikepathStatus, from, to);
    }
}
