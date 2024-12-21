package maville.equipe27.models;

import maville.equipe27.enums.RoleChoices;

import java.time.LocalDate;

/**
 * La classe {@code Resident} représente un résident de la ville, qui est un utilisateur avec des informations
 * personnelles telles que la date de naissance, le numéro de téléphone et l'adresse.
 * Cette classe étend la classe {@link User}, héritant ainsi des attributs de base d'un utilisateur (email, mot de passe, rôle, etc.).
 */
public class Resident extends User {

    /**
     * La date de naissance du résident.
     */
    private LocalDate dob;

    /**
     * Le numéro de téléphone du résident.
     */
    private String phone;

    /**
     * L'adresse du résident, qui contient la rue, le code postal, le quartier et plus en une seule string.
     */
    private String address;

    // Constructeur

    /**
     * Constructeur de la classe {@code Resident}.
     *
     * @param email L'email du résident.
     * @param password Le mot de passe du résident.
     * @param role Le rôle de l'utilisateur (RÉSIDENT, utilisé pour simplifier le code et la logique de connexion).
     * @param firstname Le prénom du résident.
     * @param lastname Le nom du résident.
     * @param dob La date de naissance du résident.
     * @param phone Le numéro de téléphone du résident.
     * @param address L'adresse du résident.
     */
    public Resident(String email, String password, RoleChoices role, String firstname, String lastname,
                    LocalDate dob, String phone, String address) {
        super(email, password, role, firstname, lastname);
        this.dob = dob;
        this.phone = phone;
        this.address = address;
    }

    // Méthodes getter et setter

    /**
     * Retourne la date de naissance du résident.
     *
     * @return La date de naissance du résident.
     */
    public LocalDate getDob() {
        return dob;
    }

    /**
     * Définit la date de naissance du résident.
     *
     * @param dob La date de naissance du résident.
     */
    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    /**
     * Retourne le numéro de téléphone du résident.
     *
     * @return Le numéro de téléphone du résident.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Définit le numéro de téléphone du résident.
     *
     * @param phone Le numéro de téléphone du résident.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Retourne l'adresse du résident.
     *
     * @return L'adresse du résident.
     */
    public String getAddress() {
        return address;
    }

    /**
     * Définit l'adresse du résident.
     *
     * @param address L'adresse du résident.
     */
    public void setAddress(String address) {
        this.address = address;
    }
}
