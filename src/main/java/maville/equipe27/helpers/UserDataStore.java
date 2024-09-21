package maville.equipe27.helpers;

import com.google.gson.Gson;
import maville.equipe27.models.User;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.Arrays;

public class UserDataStore {
    private final String FILE_NAME;

    public UserDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    public User fetchUser(String username) {
        User[] users = null;
        try (Reader reader = new InputStreamReader(this.getClass().getResourceAsStream(FILE_NAME))) {
            users = new Gson().fromJson(reader, User[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }

         return Arrays.stream(users)
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst().orElse(null);
    }
}
