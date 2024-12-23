package maville.equipe27.models;

import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code RequeteTravail} représente une requête de travail soumise par un résident.
 * Elle contient des informations sur le titre du travail, la description, le type de travail, la date de début,
 * le statut actuel de la requête et le courriel du résident qui a fait la requête.
 *
 * Cette classe permet de gérer les différentes requêtes de travail, y compris l'ajout de candidats pour effectuer le travail
 * et le choix du candidat sélectionné pour la tâche.
 */
public class RequeteTravail {

    private List<String> candidates;

    /**
     * Le titre de la requête de travail.
     */
    private String titreTravail;

    /**
     * La description détaillée de la requête de travail.
     */
    private String description;

    /**
     * Le type de travail demandé (par exemple, réparation de route, etc).
     */
    private TravauxTypes typeTravail;

    /**
     * La date de début souhaitée pour la requête de travail.
     */
    private LocalDate dateDebut;

    /**
     * Le statut actuel de la requête de travail. Par défaut, il est défini sur "INCHANGÉ".
     */
    private String status = "INCHANGÉ";

    /**
     * Le courriel de l'utilisateur qui a fait la requête.
     */
    private String owner;

    private String chosenCandidate = "";

    // Constructeurs

    /**
     * Constructeur de la classe {@code RequeteTravail} pour initialiser une requête avec un statut par défaut.
     *
     * @param owner Le courriel de l'utilisateur qui a fait la requête.
     * @param titreTravail Le titre de la requête de travail.
     * @param description La description de la requête de travail.
     * @param typeTravail Le type de travail demandé.
     * @param dateDebut La date de début souhaitée pour la requête de travail.
     */
    public RequeteTravail(String owner, String titreTravail, String description, TravauxTypes typeTravail, LocalDate dateDebut) {
        this.titreTravail = titreTravail;
        this.description = description;
        this.typeTravail = typeTravail;
        this.dateDebut = dateDebut;
        this.owner = owner;
        this.candidates = new ArrayList<>();
    }

    /**
     * Constructeur de la classe {@code RequeteTravail} pour initialiser une requête avec un statut spécifique.
     *
     * @param owner Le propriétaire de la requête de travail.
     * @param titreTravail Le titre de la requête de travail.
     * @param description La description de la requête de travail.
     * @param typeTravail Le type de travail demandé.
     * @param dateDebut La date de début souhaitée pour la requête de travail.
     * @param status Le statut actuel de la requête de travail.
     */
    public RequeteTravail(String owner, String titreTravail, String description, TravauxTypes typeTravail, LocalDate dateDebut, String status) {
        this.titreTravail = titreTravail;
        this.description = description;
        this.typeTravail = typeTravail;
        this.dateDebut = dateDebut;
        this.status = status;
        this.owner = owner;
        this.candidates = new ArrayList<>();
    }

    // Méthodes getter et setter

    /**
     * Retourne le titre de la requête de travail.
     *
     * @return Le titre de la requête de travail.
     */
    public String getTitreTravail() {
        return titreTravail;
    }

    /**
     * Définit le titre de la requête de travail.
     *
     * @param titreTravail Le titre de la requête de travail.
     */
    public void setTitreTravail(String titreTravail) {
        this.titreTravail = titreTravail;
    }

    /**
     * Retourne la description de la requête de travail.
     *
     * @return La description de la requête de travail.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Définit la description de la requête de travail.
     *
     * @param description La description de la requête de travail.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Retourne le type de travail demandé.
     *
     * @return Le type de travail demandé.
     */
    public TravauxTypes getTypeTravail() {
        return typeTravail;
    }

    /**
     * Définit le type de travail demandé.
     *
     * @param typeTravail Le type de travail demandé.
     */
    public void setTypeTravail(TravauxTypes typeTravail) {
        this.typeTravail = typeTravail;
    }

    /**
     * Retourne la date de début prévue pour la requête de travail.
     *
     * @return La date de début de la requête de travail.
     */
    public LocalDate getDateDebut() {
        return dateDebut;
    }

    /**
     * Définit la date de début prévue pour la requête de travail.
     *
     * @param dateDebut La date de début de la requête de travail.
     */
    public void setDateDebut(LocalDate dateDebut) {
        this.dateDebut = dateDebut;
    }

    /**
     * Retourne le statut actuel de la requête de travail.
     *
     * @return Le statut actuel de la requête.
     */
    public String getStatus() {
        return status;
    }

    /**
     * Définit le statut actuel de la requête de travail.
     *
     * @param status Le statut actuel de la requête.
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Retourne le propriétaire de la requête de travail.
     *
     * @return Le propriétaire de la requête de travail.
     */
    public String getOwner() {
        return owner;
    }

    /**
     * Le courriel de l'utilisateur qui a fait la requête.
     *
     * @param owner Le propriétaire de la requête.
     */
    public void setOwner(String owner) {
        this.owner = owner;
    }

    /**
     * Ajoute une candidature pour un intervenant à la requête de travail.
     *
     * @param courrielIntervenant Le courriel de l'intervenant candidat.
     */
    public void addCandidature(String courrielIntervenant) {
        this.candidates.add(courrielIntervenant);
    }

    /**
     * Retourne la liste des candidats pour la requête de travail.
     *
     * @return La liste des candidats.
     */
    public List<String> getCandidates() {
        return candidates;
    }

    /**
     * Définit le candidat choisi pour effectuer le travail.
     *
     * @param candidate Le courriel du candidat choisi.
     */
    public void setChosenCandidate(String candidate) {
        this.chosenCandidate = candidate;
    }

    /**
     * Retourne le candidat choisi pour effectuer le travail.
     *
     * @return Le courriel du candidat choisi.
     */
    public String getChosenCandidate() {
        return this.chosenCandidate;
    }
}
