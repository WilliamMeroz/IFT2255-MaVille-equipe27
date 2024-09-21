package maville.equipe27;

import maville.equipe27.controllers.LoginController;
import maville.equipe27.views.LoginView;
import maville.equipe27.views.TextIOSingleton;

public class Main {
    public static void main(String[] args) {
        LoginController loginController = new LoginController(
                new LoginView(TextIOSingleton.getInstance())
        );

    loginController.run();

    }
}