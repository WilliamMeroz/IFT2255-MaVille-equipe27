package maville.equipe27;

import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.controllers.AuthController;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.views.AuthView;
import maville.equipe27.views.TextIOSingleton;

public class Main {
    public static void main(String[] args) {
        AuthView authView = new AuthView(TextIOSingleton.getInstance());
        UserDataStore userDataStore = new UserDataStore("/users.json");

        AuthController authController = new AuthController(authView, userDataStore);
        ResidentController residentController = new ResidentController();
        IntervenantController intervenantController = new IntervenantController();

        authController.addPropertyChangeListener(residentController);
        authController.addPropertyChangeListener(intervenantController);

        authController.run();

    }
}