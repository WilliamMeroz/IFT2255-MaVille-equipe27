package maville.equipe27.controllers;

import maville.equipe27.enums.AuthChoices;
import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.helpers.AuthHelper;
import maville.equipe27.helpers.UserDataStore;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;
import maville.equipe27.views.AuthView;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.time.LocalDate;

public class AuthController implements IController {
    private final AuthView view;
    private final AuthHelper authHelper;
    private final PropertyChangeSupport support;


    public AuthController() {
        this.view = null;
        this.authHelper = new AuthHelper(new UserDataStore("users.json"));
        this.support = new PropertyChangeSupport(this);
    }

    public AuthController(AuthView view, AuthHelper authHelper) {
        this.view = view;
        this.authHelper = authHelper;
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    public void attemptLogin(String email, String password) {
        User user = authHelper.login(email, password);
        if(user != null) {
            view.loginSuccess(user.getFirstname());
            support.firePropertyChange("user", null, user);
        }
        else {
            view.loginFailure();
        }
    }

    public User login(String email, String password) {
        return authHelper.login(email, password);
    }

    public void attemptRegister(User user) {
        boolean success = authHelper.register(user);
        if(success) {
            view.registerSuccess(user.getFirstname());
            support.firePropertyChange("user", null, user);
        } else {
            view.registerFailure();
        }
    }

    public User register(User user) {
        if (authHelper.register(user)) {
            return user;
        }

        return null;
    }

    @Override
    public void run() {
        AuthChoices option = view.promptMenu();

        if (option == AuthChoices.QUITTER) System.exit(0);

        String email = view.promptEmail();
        String password = view.promptPassword();


        if (option == AuthChoices.INSCRIPTION) {
            RoleChoices role = view.promptRole();
            String firstname = view.promptFirstname();
            String lastname = view.promptLastname();

            User user = null;
            if(role == RoleChoices.RÉSIDENT) {
                LocalDate dob = view.promptDateOfBirth();
                if (dob == null) System.exit(0);
                String phone = view.promptPhone();
                String address = view.promptAddress();

                user = new Resident(email, password, role, firstname, lastname, dob, phone, address);
            }

            if (role == RoleChoices.INTERVENANT) {
                CompanyChoices companyChoice = view.promptCompanyType();
                String cityIdentifier = view.promptCityIdentifier();
                user = new Intervenant(email, password, role, firstname, lastname, companyChoice, cityIdentifier);
            }

            attemptRegister(user);
        }
        else attemptLogin(email, password);
    }
}
