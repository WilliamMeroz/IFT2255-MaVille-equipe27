package maville.equipe27.controllers;

import maville.equipe27.helpers.LoginHelper;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.models.User;
import maville.equipe27.views.LoginView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginController {
    private final LoginView view;
    private final LoginHelper loginHelper;
    private final PropertyChangeSupport support;


    public LoginController(LoginView view, UserDataStore userDataStore) {
        this.view = view;
        this.loginHelper = new LoginHelper(userDataStore);
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private void attemptLogin(String username, String password) {
        User user = loginHelper.login(username, password);
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
