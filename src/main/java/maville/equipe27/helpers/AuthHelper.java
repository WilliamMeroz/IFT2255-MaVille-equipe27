package maville.equipe27.helpers;

import at.favre.lib.crypto.bcrypt.BCrypt;
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

    public boolean register(User user) {

        User existingUser = this.userDataStore.fetchUser(user.getEmail());
        // Return null to controller so we know the user already exists.
        if (existingUser == null) {
            String hashedPassword = BCrypt.withDefaults().hashToString(10, user.getPassword().toCharArray());
            user.setPassword(hashedPassword);
            return this.userDataStore.saveUser(user);
        }

        return false;
    }
}
