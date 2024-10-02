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


    public AuthController(AuthView view, UserDataStore userDataStore) {
        this.view = view;
        this.authHelper = new AuthHelper(userDataStore);
        this.support = new PropertyChangeSupport(this);
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    private void attemptLogin(String email, String password) {
        User user = authHelper.login(email, password);
        if(user != null) {
            view.loginSuccess(user.getFirstname());
            support.firePropertyChange("user", null, user);
        }
        else {
            view.loginFailure();
            this.run();
        }
    }

    private void register(User user) {
        boolean success = authHelper.register(user);
        if(success) {
            view.registerSuccess(user.getFirstname());
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

            register(user);
        }
        else attemptLogin(email, password);
    }
}
