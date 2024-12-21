package maville.equipe27.helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import maville.equipe27.models.User;

/**
 * La classe {@code AuthHelper} fournit des méthodes pour gérer l'authentification des utilisateurs,
 * y compris la connexion et l'inscription. Elle utilise le chiffrement BCrypt pour sécuriser les mots de passe.
 * Elle interagit avec un dépôt de données d'utilisateurs pour vérifier l'existence des utilisateurs et enregistrer
 * de nouveaux utilisateurs dans la base de données.
 */
public class AuthHelper {

    /**
     * Le dépôt de données des utilisateurs pour la gestion des informations utilisateurs.
     */
    private final UserDataStore userDataStore;

    /**
     * Constructeur de la classe {@code AuthHelper}.
     *
     * @param userDataStore Le dépôt de données des utilisateurs utilisé pour accéder aux informations utilisateur.
     */
    public AuthHelper(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    /**
     * Vérifie les informations de connexion d'un utilisateur en comparant le mot de passe fourni
     * avec le mot de passe haché stocké dans la base de données.
     *
     * @param username L'email de l'utilisateur à connecter.
     * @param password Le mot de passe de l'utilisateur à vérifier.
     * @return L'utilisateur connecté si les informations sont valides, sinon {@code null}.
     */
    public User login(String username, String password) {
        User user = this.userDataStore.fetchUser(username);

        User returnedUser = null;

        if (user != null) {
            if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                returnedUser = user;
            }
        }

        return returnedUser;
    }

    /**
     * Enregistre un nouvel utilisateur dans la base de données après avoir vérifié qu'il n'existe pas déjà.
     * Le mot de passe est haché avant d'être stocké.
     *
     * @param user L'utilisateur à enregistrer.
     * @return {@code true} si l'enregistrement est réussi, {@code false} si l'utilisateur existe déjà.
     */
    public boolean register(User user) {

        User existingUser = this.userDataStore.fetchUser(user.getEmail());
        // Retourne false si l'utilisateur existe déjà.
        if (existingUser == null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(10, user.getPassword().toCharArray());
            user.setPassword(hashedPassword);
            return this.userDataStore.saveUser(user);
        }

        return false;
    }
}
