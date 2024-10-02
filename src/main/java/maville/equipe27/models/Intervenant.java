package maville.equipe27.models;

import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.enums.RoleChoices;

public class Intervenant extends User {
    private CompanyChoices companyChoices;
    private String cityIdentifier;

    // Copy constructor.
    public Intervenant(String email, String password, RoleChoices role, String firstname, String lastname,
                       CompanyChoices companyChoices, String cityIdentifier) {
        super(email, password, role, firstname, lastname);
        this.companyChoices = companyChoices;
        this.cityIdentifier = cityIdentifier;
    }

    public CompanyChoices getCompanyChoices() {
        return companyChoices;
    }

    public void setCompanyChoices(CompanyChoices companyChoices) {
        this.companyChoices = companyChoices;
    }

    public String getCityIdentifier() {
        return cityIdentifier;
    }

    public void setCityIdentifier(String cityIdentifier) {
        this.cityIdentifier = cityIdentifier;
    }
}
