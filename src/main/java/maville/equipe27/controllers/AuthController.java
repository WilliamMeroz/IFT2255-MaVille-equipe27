package maville.equipe27.controllers;

import maville.equipe27.enums.AuthChoices;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.helpers.AuthHelper;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.models.User;
import maville.equipe27.views.AuthView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuthController implements IController {
    private final AuthView view;
    private final AuthHelper authHelper;
    private final PropertyChangeSupport support;


    public AuthController(AuthView view, UserDataStore userDataStore) {
        this.view = view;
        this.authHelper = new AuthHelper(userDataStore);
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private void attemptLogin(String username, String password) {
        User user = authHelper.login(username, password);
        if(user != null) {
            view.loginSuccess(username);
            support.firePropertyChange("user", null, user);
        }
        else {
            view.loginFailure();
            this.run();
        }
    }

    private void register(String username, String password, RoleChoices role) {
        User user = authHelper.register(username, password, role);
        if(user != null) {
            view.registerSuccess(username);
            support.firePropertyChange("user", null, user);
        } else {
            view.registerFailure();
            this.run();
        }
    }

    @Override
    public void run() {
        AuthChoices option = view.promptMenu();

        if (option == AuthChoices.QUITTER) System.exit(0);

        String username = view.promptUsername();
        String password = view.promptPassword();

        if (option == AuthChoices.INSCRIPTION) {
            RoleChoices role = view.promptRole();
            register(username, password, role);
        }
        else attemptLogin(username, password);
    }
}
