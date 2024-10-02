package maville.equipe27.views;


import maville.equipe27.enums.AuthChoices;
import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.validators.AuthValidator;
import org.beryx.textio.TextIO;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.StringJoiner;

public class AuthView {

   TextIO textIO;

    public AuthView(TextIO textIO) {
        this.textIO = textIO;
    }

    public AuthChoices promptMenu() {
        return textIO.newEnumInputReader(AuthChoices.class).read();
    }

    public String promptEmail() {
        return textIO.newStringInputReader().withPattern("^[A-Za-z0-9\\._%+\\-]+@[A-Za-z0-9\\.\\-]+\\.[A-Za-z]{2,}$").read("Entrez votre courriel");
    }

    public String promptPassword() {
        return textIO.newStringInputReader().withMinLength(8).withInputMasking(true).read("Entrez le mot de passe");
    }

    public RoleChoices promptRole() {
        return textIO.newEnumInputReader(RoleChoices.class).read("Choisissez un rôle");
    }

    public String promptFirstname() {
        return textIO.newStringInputReader().read("Entrez votre prénom");
    }

    public String promptLastname() {
        return textIO.newStringInputReader().read("Entrez votre nom de famille");
    }

    public LocalDate promptDateOfBirth() {
        String dateLiteral;
        LocalDate date;
        while(true) {
            try {
                dateLiteral = textIO.newStringInputReader().read("Entrez votre date de naissance (YYYY-MM-DD)");
                date = LocalDate.parse(dateLiteral);
                break;
            } catch(DateTimeParseException _) {
                textIO.getTextTerminal().println("Entrez une date avec un format valide");
            }
        }

        if (Period.between(date, LocalDate.now()).getYears() < 16) {
            textIO.getTextTerminal().println("Vous devez avoir au moins 16 ans pour utiliser ce logiciel.");
            return null;
        }

        return date;
    }

    public String promptPhone() {
        String phone;
        while (true) {
            phone = textIO.newStringInputReader().withMinLength(0).read("Entrez votre # de téléphone (ou ENTER pour passer)");
            if (phone.isEmpty() || AuthValidator.validatePhone(phone))
                return phone;
            else textIO.getTextTerminal().println("Veuillez entre un # dans le format 123-456-7890");
        }
    }

    public String promptAddress() {
        int civicNumber = textIO.newIntInputReader().withMinVal(1).read("Entrez votre # civic (123)");
        String street = textIO.newStringInputReader().read("Entrez votre rue (Rue ABC)");
        int unit = textIO.newIntInputReader().withMinVal(0).withDefaultValue(0).read("Entrez votre unité (si applicable)");
        String postalCode;

        while(true) {
            postalCode = textIO.newStringInputReader().read("Entrez votre code postal (A1B 2C3)");
            if (AuthValidator.validatePostalCode(postalCode))
                break;
            else textIO.getTextTerminal().println("Entrez un code postal valide");
        }

        StringJoiner joiner = new StringJoiner(",");
        joiner.add(String.valueOf(civicNumber)).add(street).add(String.valueOf(unit)).add(postalCode);
        return joiner.toString();
    }

    public CompanyChoices promptCompanyType() {
        return textIO.newEnumInputReader(CompanyChoices.class).read("Choisissez un type d'entreprise");
    }

    public String promptCityIdentifier() {
        int code = textIO.newIntInputReader().withMinVal(10000000).withMaxVal(99999999).read("Entrez l'identifiant");
        return String.valueOf(code);
    }

    public void loginSuccess(String username) {
        textIO.getTextTerminal().printf("\nBienvenue %s, vous avez été connecté(e) avec succès\n", username);
    }

    public void loginFailure() {
        textIO.getTextTerminal().println("Nom d'utilisateur ou mot de passe incorrect.");
    }

    public void registerSuccess(String username) {
        textIO.getTextTerminal().printf("\nCompte pour %s créé avec succès.\n", username);
    }

    public void registerFailure() {
        textIO.getTextTerminal().println("Une erreur est survenue lors de votre inscription ou un utilisateur avec le même courriel existe déjà.");
    }
}
