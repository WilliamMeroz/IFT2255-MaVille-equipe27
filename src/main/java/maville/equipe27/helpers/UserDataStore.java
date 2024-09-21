package maville.equipe27.helpers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class UserDataStore {
    private final String FILE_NAME;

    public UserDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    private User[] getUserList() {
        User[] users = null;
        try (Reader reader = new FileReader(FILE_NAME)) {
            users = GsonSingleton.getInstance().fromJson(reader, User[].class);
        } catch (IOException e) {
            // We should add real Exception handling.
            e.printStackTrace();
        }

        return users;
    }

    public User fetchUser(String username) {
         return Arrays.stream(getUserList())
                    .filter(user -> user.getUsername().equals(username))
                    .findFirst().orElse(null);
    }

    public User saveUser(String username, String password, RoleChoices role) {
        ArrayList<User> users = new ArrayList<>(Arrays.asList(getUserList()));

        User newUser = new User(username, password, role);
        users.add(newUser);

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonSingleton.getInstance().toJson(users, fileWriter);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newUser;
    }
}
