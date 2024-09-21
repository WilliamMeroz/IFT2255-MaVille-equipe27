package maville.equipe27.helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;
import maville.equipe27.models.User;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class LoginHelper {

    private final UserDataStore userDataStore;
    private User user = null;

    public LoginHelper(UserDataStore userDataStore) {
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
