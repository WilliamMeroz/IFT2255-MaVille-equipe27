package maville.equipe27;

import maville.equipe27.controllers.AuthEventHandler;
import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.controllers.AuthController;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.views.AuthView;
import maville.equipe27.views.IntervenantView;
import maville.equipe27.views.ResidentView;
import maville.equipe27.views.TextIOSingleton;

public class Main {
    public static void main(String[] args) {

        // Initialize views.
        AuthView authView = new AuthView(TextIOSingleton.getInstance());

        // Initialize helper classes and data stores.
        UserDataStore userDataStore = new UserDataStore("users.json");

        // Initialize controllers.
        AuthController authController = new AuthController(authView, userDataStore);

        ResidentView residentView = new ResidentView(TextIOSingleton.getInstance());
        ResidentController residentController = new ResidentController(residentView);

        IntervenantView intervenantView = new IntervenantView(TextIOSingleton.getInstance());
        IntervenantController intervenantController = new IntervenantController(intervenantView);

        // Initialize event handlers (used for Observer pattern).
        AuthEventHandler authEventHandler = new AuthEventHandler(residentController, intervenantController);
        authController.addPropertyChangeListener(authEventHandler);

        // Run the first controller needed. The other controllers will be ran as needed from the auth event handler.
        authController.run();


    }
}