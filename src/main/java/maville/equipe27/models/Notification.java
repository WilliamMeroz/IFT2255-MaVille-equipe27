package maville.equipe27.models;

import java.util.Date;

/**
 * La classe {@code Notification} représente une notification qu'un utilisateur peut recevoir.
 * Une notification contient un message, une adresse e-mail associée et une date d'émission.
 *
 * <p>Cette classe fournit des constructeurs, des accesseurs (getters) et des mutateurs (setters)
 * pour gérer ses attributs.</p>
 *
 * @author William Méroz-Moreau
 * @version 1.0
 */
public class Notification {
    // Attributs

    /**
     * Le message de la notification.
     */
    String message;

    /**
     * L'adresse e-mail associée au compte de l'utilisateur qui la reçoit.
     */
    String associatedEmail;

    /**
     * La date et l'heure à laquelle la notification a été émise.
     */
    Date dateEmitted;

    // Constructeur

    /**
     * Constructeur de la classe {@code Notification}.
     *
     * @param message        Le message de la notification.
     * @param associatedEmail L'adresse e-mail de l'utilisateur.
     * @param dateEmitted    La date d'émission de la notification.
     */
    public Notification(String message, String associatedEmail, Date dateEmitted) {
        this.message = message;
        this.associatedEmail = associatedEmail;
        this.dateEmitted = dateEmitted;
    }

    // Méthodes

    /**
     * Retourne le message de la notification.
     *
     * @return Le message de la notification.
     */
    public String getMessage() {
        return message;
    }

    /**
     * Définit un nouveau message pour la notification.
     *
     * @param message Le nouveau message de la notification.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Retourne l'adresse e-mail associée à l'utilisateur qui reçoit la notification.
     *
     * @return L'adresse e-mail associée à la notification.
     */
    public String getAssociatedEmail() {
        return associatedEmail;
    }

    /**
     * Définit une nouvelle adresse e-mail associée à la notification.
     *
     * @param associatedEmail La nouvelle adresse e-mail associée.
     */
    public void setAssociatedEmail(String associatedEmail) {
        this.associatedEmail = associatedEmail;
    }

    /**
     * Retourne la date d'émission de la notification.
     *
     * @return La date d'émission de la notification.
     */
    public Date getDateEmitted() {
        return dateEmitted;
    }

    /**
     * Définit une nouvelle date d'émission pour la notification.
     *
     * @param dateEmitted La nouvelle date d'émission.
     */
    public void setDateEmitted(Date dateEmitted) {
        this.dateEmitted = dateEmitted;
    }
}
