package maville.equipe27.models;

import maville.equipe27.enums.RoleChoices;

/**
 * La classe abstraite {@code User} représente un utilisateur du système avec des informations de base telles que
 * l'email, le mot de passe, le rôle, ainsi que les prénom et nom. Cette classe sert de base pour d'autres types
 * d'utilisateurs, comme les résidents ou les intervenants.
 */
public abstract class User {

    /**
     * L'email de l'utilisateur, utilisé pour l'identification.
     */
    private String email;

    /**
     * Le mot de passe de l'utilisateur.
     */
    private String password;

    /**
     * Le rôle de l'utilisateur dans le système (par exemple, résident, intervenant).
     */
    private RoleChoices role;

    /**
     * Le prénom de l'utilisateur.
     */
    private String firstname;

    /**
     * Le nom de famille de l'utilisateur.
     */
    private String lastname;

    // Constructeur

    /**
     * Constructeur de la classe {@code User} qui initialise tous les attributs nécessaires.
     *
     * @param email L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @param role Le rôle de l'utilisateur dans le système.
     * @param firstname Le prénom de l'utilisateur.
     * @param lastname Le nom de famille de l'utilisateur.
     */
    public User(String email, String password, RoleChoices role, String firstname, String lastname) {
        this.email = email;
        this.password = password;
        this.role = role;
        this.firstname = firstname;
        this.lastname = lastname;
    }

    // Méthodes getter et setter

    /**
     * Retourne l'email de l'utilisateur.
     *
     * @return L'email de l'utilisateur.
     */
    public String getEmail() {
        return email;
    }

    /**
     * Définit l'email de l'utilisateur.
     *
     * @param email L'email de l'utilisateur.
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Retourne le mot de passe de l'utilisateur.
     *
     * @return Le mot de passe de l'utilisateur.
     */
    public String getPassword() {
        return password;
    }

    /**
     * Définit le mot de passe de l'utilisateur.
     *
     * @param password Le mot de passe de l'utilisateur.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * Retourne le rôle de l'utilisateur.
     *
     * @return Le rôle de l'utilisateur.
     */
    public RoleChoices getRole() {
        return role;
    }

    /**
     * Définit le rôle de l'utilisateur dans le système.
     *
     * @param role Le rôle de l'utilisateur.
     */
    public void setRole(RoleChoices role) {
        this.role = role;
    }

    /**
     * Retourne le prénom de l'utilisateur.
     *
     * @return Le prénom de l'utilisateur.
     */
    public String getFirstname() {
        return firstname;
    }

    /**
     * Définit le prénom de l'utilisateur.
     *
     * @param firstname Le prénom de l'utilisateur.
     */
    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    /**
     * Retourne le nom de famille de l'utilisateur.
     *
     * @return Le nom de famille de l'utilisateur.
     */
    public String getLastname() {
        return lastname;
    }

    /**
     * Définit le nom de famille de l'utilisateur.
     *
     * @param lastname Le nom de famille de l'utilisateur.
     */
    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'objet {@code User}.
     *
     * @return Une chaîne représentant l'utilisateur avec son email, mot de passe et rôle.
     */
    @Override
    public String toString() {
        return "User{" +
                "username='" + email + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
