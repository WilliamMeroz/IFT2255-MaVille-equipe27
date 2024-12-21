package maville.equipe27.models;

import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.enums.RoleChoices;

/**
 * La classe {@code Intervenant} représente un intervenant utilisateur dans le système, qui hérite des attributs
 * de la classe {@link User}. Un intervenant est un utilisateur associé à une entreprise, chargée de la gestion des travaux.
 *
 */
public class Intervenant extends User {

    /**
     * Le type de compagnie de l'intervenant: privé, particulier ou public.
     */
    private CompanyChoices companyChoices;

    /**
     * L'identifiant de la ville associée à l'intervenant.
     */
    private String cityIdentifier;

    // Constructeur

    /**
     * Constructeur de la classe {@code Intervenant} pour initialiser tous les attributs.
     *
     * @param email L'email de l'intervenant.
     * @param password Le mot de passe de l'intervenant.
     * @param role Le rôle de l'intervenant (utilisé surtout à des fins de simplification du code).
     * @param firstname Le prénom de l'intervenant ou de la perssone à charge.
     * @param lastname Le nom de famille de l'intervenant ou de la personne à charge.
     * @param companyChoices Le choix d'entreprise de l'intervenant.
     * @param cityIdentifier L'identifiant de la ville associée à l'intervenant.
     */
    public Intervenant(String email, String password, RoleChoices role, String firstname, String lastname,
                       CompanyChoices companyChoices, String cityIdentifier) {
        super(email, password, role, firstname, lastname);
        this.companyChoices = companyChoices;
        this.cityIdentifier = cityIdentifier;
    }

    // Méthodes

    /**
     * Retourne le choix d'entreprise auquel l'intervenant appartient.
     *
     * @return Le choix d'entreprise de l'intervenant.
     */
    public CompanyChoices getCompanyChoices() {
        return companyChoices;
    }

    /**
     * Définit le choix d'entreprise auquel l'intervenant appartient.
     *
     * @param companyChoices Le choix d'entreprise de l'intervenant.
     */
    public void setCompanyChoices(CompanyChoices companyChoices) {
        this.companyChoices = companyChoices;
    }

    /**
     * Retourne l'identifiant de la ville associée à l'intervenant.
     *
     * @return L'identifiant de la ville de l'intervenant.
     */
    public String getCityIdentifier() {
        return cityIdentifier;
    }

    /**
     * Définit l'identifiant de la ville associée à l'intervenant.
     *
     * @param cityIdentifier L'identifiant de la ville de l'intervenant.
     */
    public void setCityIdentifier(String cityIdentifier) {
        this.cityIdentifier = cityIdentifier;
    }
}
