package maville.equipe27.helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.User;

public class AuthHelper {

    private final UserDataStore userDataStore;

    public AuthHelper(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

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

    public User register(String username, String password, RoleChoices role) {

        User fetchedUser = this.userDataStore.fetchUser(username);

        User returnedUser = null;

        // Return null to controller so we know the user already exists.
        if (fetchedUser == null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(10, password.toCharArray());
            returnedUser = this.userDataStore.saveUser(username, hashedPassword, role);
        }

        return returnedUser;
    }
}
