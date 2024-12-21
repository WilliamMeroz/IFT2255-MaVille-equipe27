package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;

/**
 * La classe {@code Travail} représente une tâche ou un projet de travaux effectuée dans un quartier de la ville.
 * Cette classe contient des informations sur l'identifiant du travail, le titre, l'intervenant responsable, le quartier,
 * le type de travaux, ainsi que les dates de début et de fin des travaux.
 * Les informations proviennent des APIs de la ville de Montréal.
 */
public class Travail {

    /**
     * L'identifiant unique du travail.
     */
    private String id;

    /**
     * Le titre du travail, décrivant brièvement la tâche.
     */
    private String titre;

    /**
     * Le nom de l'intervenant responsable du travail.
     */
    private String nomIntervenant;

    /**
     * Le quartier où le travail est effectué.
     */
    private String quartier;

    /**
     * Le type de travail effectué (ex: réparation, entretien, construction).
     */
    private TravauxTypes type;

    /**
     * La date de début des travaux.
     */
    private LocalDate debut;

    /**
     * La date de fin des travaux.
     */
    private LocalDate fin;

    // Constructeurs

    /**
     * Constructeur par défaut de la classe {@code Travail}.
     */
    public Travail() { }

    /**
     * Constructeur de la classe {@code Travail} avec tous les attributs nécessaires.
     *
     * @param id L'identifiant unique du travail.
     * @param nomIntervenant Le nom de l'intervenant responsable des travaux.
     * @param titre Le titre du travail.
     * @param quartier Le quartier où le travail est effectué.
     * @param type Le type de travail effectué.
     * @param debut La date de début des travaux.
     * @param fin La date de fin des travaux.
     */
    public Travail(String id, String nomIntervenant, String titre, String quartier, TravauxTypes type, LocalDate debut, LocalDate fin) {
        this.id = id;
        this.nomIntervenant = nomIntervenant;
        this.titre = titre;
        this.quartier = quartier;
        this.type = type;
        this.debut = debut;
        this.fin = fin;
    }

    // Méthodes getter et setter

    /**
     * Retourne l'identifiant unique du travail.
     *
     * @return L'identifiant du travail.
     */
    public String getId() {
        return id;
    }

    /**
     * Définit l'identifiant du travail.
     *
     * @param id L'identifiant du travail.
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Retourne le titre du travail.
     *
     * @return Le titre du travail.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre du travail.
     *
     * @param titre Le titre du travail.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne le nom de l'intervenant responsable du travail.
     *
     * @return Le nom de l'intervenant.
     */
    public String getNomIntervenant() {
        return nomIntervenant;
    }

    /**
     * Définit le nom de l'intervenant responsable du travail.
     *
     * @param nomIntervenant Le nom de l'intervenant.
     */
    public void setNomIntervenant(String nomIntervenant) {
        this.nomIntervenant = nomIntervenant;
    }

    /**
     * Retourne le quartier où le travail est effectué.
     *
     * @return Le quartier du travail.
     */
    public String getQuartier() {
        return this.quartier;
    }

    /**
     * Définit le quartier où le travail est effectué.
     *
     * @param quartier Le quartier du travail.
     */
    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }

    /**
     * Retourne le type de travail effectué.
     *
     * @return Le type de travail.
     */
    public TravauxTypes getType() {
        return type;
    }

    /**
     * Définit le type de travail effectué.
     *
     * @param type Le type de travail.
     */
    public void setType(TravauxTypes type) {
        this.type = type;
    }

    /**
     * Retourne la date de début des travaux.
     *
     * @return La date de début des travaux.
     */
    public LocalDate getDebut() {
        return debut;
    }

    /**
     * Définit la date de début des travaux.
     *
     * @param debut La date de début des travaux.
     */
    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    /**
     * Retourne la date de fin des travaux.
     *
     * @return La date de fin des travaux.
     */
    public LocalDate getFin() {
        return fin;
    }

    /**
     * Définit la date de fin des travaux.
     *
     * @param fin La date de fin des travaux.
     */
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet {@code Travail}.
     *
     * @return Une chaîne décrivant le travail.
     */
    @Override
    public String toString() {
        return String.format("ID: %s\nIntervenant: %s\nTitre: %s\nQuartier: %s\nType de travaux: %s\nDe: %s\nÀ: %s\n",
                id, (nomIntervenant == null ? "NA" : nomIntervenant), titre, quartier, type, debut, fin);
    }
}
