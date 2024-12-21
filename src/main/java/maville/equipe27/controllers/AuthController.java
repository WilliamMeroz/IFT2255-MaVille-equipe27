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

/**
 * Le contrôleur principal de l'authentification. Il gère les actions liées à la connexion et à l'inscription des utilisateurs.
 */
public class AuthController implements IController {
    private final AuthView view; // La vue associée à l'authentification
    private final AuthHelper authHelper; // L'aide à l'authentification pour traiter la logique métier
    private final PropertyChangeSupport support; // Support pour l'ajout de listeners pour les changements de propriété

    /**
     * Constructeur sans vue spécifique (utilisé par défaut).
     */
    public AuthController() {
        this.view = null;
        this.authHelper = new AuthHelper(new UserDataStore("users.json"));
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Constructeur avec vue et helper d'authentification.
     */
    public AuthController(AuthView view, AuthHelper authHelper) {
        this.view = view;
        this.authHelper = authHelper;
        this.support = new PropertyChangeSupport(this);
    }

    /**
     * Ajoute un écouteur pour les changements de propriété.
     *
     * @param listener L'écouteur à ajouter.
     */
    public void addPropertyChangeListener(PropertyChangeListener listener) {
        support.addPropertyChangeListener(listener);
    }

    /**
     * Tente de connecter un utilisateur avec son email et son mot de passe.
     *
     * @param email L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     */
    public void attemptLogin(String email, String password) {
        User user = authHelper.login(email, password);
        if (user != null) {
            view.loginSuccess(user.getFirstname());
            support.firePropertyChange("user", null, user);
        } else {
            view.loginFailure();
        }
    }

    /**
     * Effectue une connexion et retourne l'utilisateur si la connexion réussit.
     *
     * @param email L'email de l'utilisateur.
     * @param password Le mot de passe de l'utilisateur.
     * @return L'utilisateur connecté, ou null si la connexion échoue.
     */
    public User login(String email, String password) {
        return authHelper.login(email, password);
    }

    /**
     * Tente d'enregistrer un nouvel utilisateur dans le système.
     *
     * @param user L'utilisateur à enregistrer.
     */
    public void attemptRegister(User user) {
        boolean success = authHelper.register(user);
        if (success) {
            view.registerSuccess(user.getFirstname());
            support.firePropertyChange("user", null, user);
        } else {
            view.registerFailure();
        }
    }

    /**
     * Effectue l'enregistrement d'un utilisateur et retourne l'utilisateur si l'enregistrement réussit.
     *
     * @param user L'utilisateur à enregistrer.
     * @return L'utilisateur enregistré, ou null si l'enregistrement échoue.
     */
    public User register(User user) {
        if (authHelper.register(user)) {
            return user;
        }
        return null;
    }

    /**
     * Méthode principale qui gère les différentes actions selon les choix d'authentification (connexion ou inscription).
     */
    @Override
    public void run() {
        // Demande à l'utilisateur de choisir une option du menu
        AuthChoices option = view.promptMenu();

        // Si l'utilisateur choisit de quitter, on termine l'application
        if (option == AuthChoices.QUITTER) System.exit(0);

        // Sinon, on demande l'email et le mot de passe de l'utilisateur
        String email = view.promptEmail();
        String password = view.promptPassword();

        // Si l'utilisateur choisit l'option d'inscription
        if (option == AuthChoices.INSCRIPTION) {
            RoleChoices role = view.promptRole(); // Demande le rôle de l'utilisateur
            String firstname = view.promptFirstname(); // Demande le prénom
            String lastname = view.promptLastname(); // Demande le nom

            User user = null;
            if (role == RoleChoices.RÉSIDENT) {
                // Si l'utilisateur est un résident, on demande ses informations supplémentaires
                LocalDate dob = view.promptDateOfBirth();
                if (dob == null) System.exit(0);
                String phone = view.promptPhone();
                String address = view.promptAddress();

                user = new Resident(email, password, role, firstname, lastname, dob, phone, address);
            }

            if (role == RoleChoices.INTERVENANT) {
                // Si l'utilisateur est un intervenant, on demande son entreprise et identifiant de ville
                CompanyChoices companyChoice = view.promptCompanyType();
                String cityIdentifier = view.promptCityIdentifier();
                user = new Intervenant(email, password, role, firstname, lastname, companyChoice, cityIdentifier);
            }

            // On tente d'enregistrer l'utilisateur
            attemptRegister(user);
        } else {
            // Si l'utilisateur choisit de se connecter, on tente de se connecter
            attemptLogin(email, password);
        }
    }
}
