package maville.equipe27.controllers;

import maville.equipe27.helpers.AuthHelper;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.models.User;
import maville.equipe27.views.AuthView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class AuthController {
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

    public void run() {
        String username = view.promptUsername();
        String password = view.promptPassword();

        attemptLogin(username, password);
    }
}
