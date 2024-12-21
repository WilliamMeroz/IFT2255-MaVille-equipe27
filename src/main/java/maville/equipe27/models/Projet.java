package maville.equipe27.models;

import maville.equipe27.enums.ProjetStatus;
import maville.equipe27.enums.TravauxTypes;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * La classe {@code Projet} représente un projet de travaux dans la ville. Elle contient des informations sur
 * l'intervenant, le titre du projet, sa description, les types de travaux, les quartiers et rues affectées, ainsi
 * que les horaires et dates de début et de fin du projet. Le statut du projet est également inclus pour indiquer
 * son avancement.
 *
 * <p>Un projet peut concerner plusieurs quartiers et rues, et il a un début et une fin définis avec des horaires
 * précis pour chaque jour.</p>
 */
public class Projet {

    /**
     * L'identifiant de la ville de l'intervenant responsable du projet.
     */
    private String intervenantCityIdentifier;

    /**
     * Le titre du projet.
     */
    private String titre;

    /**
     * La description détaillée du projet.
     */
    private String desc;

    /**
     * Le type de travaux associés au projet.
     */
    private TravauxTypes type;

    /**
     * Les quartiers affectés par le projet.
     */
    private String[] quartiers;

    /**
     * La date de début du projet.
     */
    private LocalDate debut;

    /**
     * La date de fin du projet.
     */
    private LocalDate fin;

    /**
     * L'heure de début de chaque journée de travail du projet.
     */
    private LocalTime horaireDebut;

    /**
     * L'heure de fin de chaque journée de travail du projet.
     */
    private LocalTime horaireFin;

    /**
     * Le statut actuel du projet.
     */
    private ProjetStatus status;

    /**
     * Les rues affectées par le projet.
     */
    private String[] rues;

    // Constructeur

    /**
     * Constructeur de la classe {@code Projet} pour initialiser tous les attributs.
     *
     * @param intervenantCityIdentifier L'identifiant de la ville de l'intervenant.
     * @param titre Le titre du projet.
     * @param desc La description du projet.
     * @param type Le type de travaux réalisés dans le cadre du projet.
     * @param quartiers Les quartiers affectés par le projet.
     * @param debut La date de début du projet.
     * @param fin La date de fin du projet.
     * @param horaireDebut L'heure de début de chaque journée de travail du projet.
     * @param horaireFin L'heure de fin de chaque journée de travail du projet.
     * @param status Le statut actuel du projet.
     * @param rues Les rues affectées par le projet.
     */
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

    // Méthodes

    /**
     * Retourne l'identifiant de la ville de l'intervenant responsable du projet.
     *
     * @return L'identifiant de la ville de l'intervenant.
     */
    public String getIntervenantCityIdentifier() {
        return intervenantCityIdentifier;
    }

    /**
     * Définit l'identifiant de la ville de l'intervenant responsable du projet.
     *
     * @param intervenantCityIdentifier L'identifiant de la ville de l'intervenant.
     */
    public void setIntervenantCityIdentifier(String intervenantCityIdentifier) {
        this.intervenantCityIdentifier = intervenantCityIdentifier;
    }

    /**
     * Retourne le titre du projet.
     *
     * @return Le titre du projet.
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Définit le titre du projet.
     *
     * @param titre Le titre du projet.
     */
    public void setTitre(String titre) {
        this.titre = titre;
    }

    /**
     * Retourne la description du projet.
     *
     * @return La description du projet.
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Définit la description du projet.
     *
     * @param desc La description du projet.
     */
    public void setDesc(String desc) {
        this.desc = desc;
    }

    /**
     * Retourne le type de travaux associés au projet.
     *
     * @return Le type de travaux du projet.
     */
    public TravauxTypes getType() {
        return type;
    }

    /**
     * Définit le type de travaux associés au projet.
     *
     * @param type Le type de travaux du projet.
     */
    public void setType(TravauxTypes type) {
        this.type = type;
    }

    /**
     * Retourne les quartiers affectés par le projet.
     *
     * @return Les quartiers affectés par le projet.
     */
    public String[] getQuartiers() {
        return quartiers;
    }

    /**
     * Définit les quartiers affectés par le projet.
     *
     * @param quartiers Les quartiers affectés par le projet.
     */
    public void setQuartiers(String[] quartiers) {
        this.quartiers = quartiers;
    }

    /**
     * Retourne la date de début du projet.
     *
     * @return La date de début du projet.
     */
    public LocalDate getDebut() {
        return debut;
    }

    /**
     * Définit la date de début du projet.
     *
     * @param debut La date de début du projet.
     */
    public void setDebut(LocalDate debut) {
        this.debut = debut;
    }

    /**
     * Retourne la date de fin du projet.
     *
     * @return La date de fin du projet.
     */
    public LocalDate getFin() {
        return fin;
    }

    /**
     * Définit la date de fin du projet.
     *
     * @param fin La date de fin du projet.
     */
    public void setFin(LocalDate fin) {
        this.fin = fin;
    }

    /**
     * Retourne l'heure de début de chaque journée de travail du projet.
     *
     * @return L'heure de début du projet.
     */
    public LocalTime getHoraireDebut() {
        return horaireDebut;
    }

    /**
     * Définit l'heure de début de chaque journée de travail du projet.
     *
     * @param horaireDebut L'heure de début du projet.
     */
    public void setHoraireDebut(LocalTime horaireDebut) {
        this.horaireDebut = horaireDebut;
    }

    /**
     * Retourne l'heure de fin de chaque journée de travail du projet.
     *
     * @return L'heure de fin du projet.
     */
    public LocalTime getHoraireFin() {
        return horaireFin;
    }

    /**
     * Définit l'heure de fin de chaque journée de travail du projet.
     *
     * @param horaireFin L'heure de fin du projet.
     */
    public void setHoraireFin(LocalTime horaireFin) {
        this.horaireFin = horaireFin;
    }

    /**
     * Retourne le statut actuel du projet.
     *
     * @return Le statut du projet.
     */
    public ProjetStatus getStatus() {
        return status;
    }

    /**
     * Définit le statut actuel du projet.
     *
     * @param status Le statut du projet.
     */
    public void setStatus(ProjetStatus status) {
        this.status = status;
    }

    /**
     * Retourne les rues affectées par le projet.
     *
     * @return Les rues affectées par le projet.
     */
    public String[] getRues() {
        return rues;
    }

    /**
     * Définit les rues affectées par le projet.
     *
     * @param rues Les rues affectées par le projet.
     */
    public void setRues(String[] rues) {
        this.rues = rues;
    }
}
