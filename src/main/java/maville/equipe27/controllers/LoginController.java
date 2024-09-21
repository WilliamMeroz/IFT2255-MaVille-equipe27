package maville.equipe27.controllers;

import maville.equipe27.models.User;
import maville.equipe27.views.LoginView;

public class LoginController {
    private final LoginView view;
    public LoginController(LoginView view) {
        this.view = view;
    }

    private boolean attemptLogin(String username, String password) {

        return false;
    }

    public void run() {
        String username = view.promptUsername();
        String password = view.promptPassword();

        attemptLogin(username, password);
    }
}
