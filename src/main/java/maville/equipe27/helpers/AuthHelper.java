package maville.equipe27.helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import maville.equipe27.models.User;

public class AuthHelper {

    private final UserDataStore userDataStore;
    private User user = null;

    public AuthHelper(UserDataStore userDataStore) {
        this.userDataStore = userDataStore;
    }

    public User login(String username, String password) {
        this.user = this.userDataStore.fetchUser(username);

        User returnedUser = null;

        if (user != null) {
            if (BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
                returnedUser = user;
            }
        }

        return returnedUser;
    }
}
