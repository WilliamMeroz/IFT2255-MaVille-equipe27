package maville.equipe27.views;


import maville.equipe27.enums.AuthChoices;
import maville.equipe27.enums.RoleChoices;
import org.beryx.textio.TextIO;

public class AuthView {

   TextIO textIO;

    public AuthView(TextIO textIO) {
        this.textIO = textIO;
    }

    public AuthChoices promptMenu() {
        return textIO.newEnumInputReader(AuthChoices.class).read();
    }

    public String promptUsername() {
        return textIO.newStringInputReader().withMinLength(6).read("Entrez le nom d'utilisateur:");
    }

    public String promptPassword() {
        return textIO.newStringInputReader().withMinLength(8).withInputMasking(true).read("Entrez le mot de passe");
    }

    public RoleChoices promptRole() {
        return textIO.newEnumInputReader(RoleChoices.class).read("Choisissez un rôle");
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
        textIO.getTextTerminal().println("Une erreur est survenue lors de votre inscription");
    }
}
