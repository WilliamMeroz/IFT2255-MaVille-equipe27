package maville.equipe27;

import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.controllers.LoginController;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.views.LoginView;
import maville.equipe27.views.TextIOSingleton;

public class Main {
    public static void main(String[] args) {
        LoginView loginView = new LoginView(TextIOSingleton.getInstance());
        UserDataStore userDataStore = new UserDataStore("/users.json");

        LoginController loginController = new LoginController(loginView, userDataStore);
        ResidentController residentController = new ResidentController();
        IntervenantController intervenantController = new IntervenantController();

        loginController.addPropertyChangeListener(residentController);
        loginController.addPropertyChangeListener(intervenantController);

        loginController.run();

    }
}