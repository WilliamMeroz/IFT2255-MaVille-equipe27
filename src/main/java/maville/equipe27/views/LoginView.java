package maville.equipe27.views;


import org.beryx.textio.TextIO;

public class LoginView {

   TextIO textIO;

    public LoginView(TextIO textIO) {
        this.textIO = textIO;
    }

    public String promptUsername() {
        return textIO.newStringInputReader().withMinLength(6).read("Entrez le nom d'utilisateur:");
    }

    public String promptPassword() {
        return textIO.newStringInputReader().withMinLength(8).withInputMasking(true).read("Entrez le mot de passe");
    }
}
