package maville.equipe27.models;

import java.time.LocalTime;

/**
 * La classe {@code PrefHoraire} représente les préférences horaires d'un résident pour les travaux faits dans sons quartier.
 * Elle inclut l'adresse e-mail de l'utilisateur, l'heure de début et de fin des préférences horaires, ainsi que le quartier
 * pour lequel ces préférences sont spécifiées.
 */
public class PrefHoraire {

    /**
     * L'adresse e-mail de l'utilisateur pour lequel les préférences horaires sont définies.
     */
    private String email;

    /**
     * L'heure de début de la plage horaire préférée de l'utilisateur.
     */
    private LocalTime de;

    /**
     * L'heure de fin de la plage horaire préférée de l'utilisateur.
     */
    private LocalTime a;

    /**
     * Le quartier pour lequel l'utilisateur a défini des préférences horaires.
     */
    private String quartier;

    // Constructeur

    /**
     * Constructeur de la classe {@code PrefHoraire} pour initialiser les attributs.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     * @param de L'heure de début de la plage horaire préférée.
     * @param a L'heure de fin de la plage horaire préférée.
     * @param quartier Le quartier associé aux préférences horaires.
     */
    public PrefHoraire(String email, LocalTime de, LocalTime a, String quartier) {
        this.email = email;
        this.de = de;
        this.a = a;
        this.quartier = quartier;
    }

    // Méthodes

    /**
     * Retourne l'adresse e-mail de l'utilisateur.
     *
     * @return L'adresse e-mail de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'adresse e-mail de l'utilisateur.
     *
     * @param email L'adresse e-mail de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne l'heure de début de la plage horaire préférée.
     *
     * @return L'heure de début.
     */
    public LocalTime getDe() {
        return de;
    }

    /**
     * Définit l'heure de début de la plage horaire préférée.
     *
     * @param de L'heure de début.
     */
    public void setDe(LocalTime de) {
        this.de = de;
    }

    /**
     * Retourne l'heure de fin de la plage horaire préférée.
     *
     * @return L'heure de fin.
     */
    public LocalTime getA() {
        return a;
    }

    /**
     * Définit l'heure de fin de la plage horaire préférée.
     *
     * @param a L'heure de fin.
     */
    public void setA(LocalTime a) {
        this.a = a;
    }

    /**
     * Retourne le quartier pour lequel les préférences horaires sont définies.
     *
     * @return Le quartier de l'utilisateur.
     */
    public String getQuartier() {
        return quartier;
    }

    /**
     * Définit le quartier pour lequel les préférences horaires sont définies.
     *
     * @param quartier Le quartier de l'utilisateur.
     */
    public void setQuartier(String quartier) {
        this.quartier = quartier;
    }
}
