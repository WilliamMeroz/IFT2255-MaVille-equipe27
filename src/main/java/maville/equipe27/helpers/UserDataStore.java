package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UserDataStore {
    private final String FILE_NAME;

    public UserDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    public List<User> getUserList() {
        List<User> users = null;
        try (Reader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new CustomerUsersJsonAdapter());
            Gson gson = builder.create();
            users = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            // We should add real Exception handling.
            e.printStackTrace();
        }

        return users;
    }

    public User fetchUser(String email) {
         return getUserList().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    public boolean saveUser(User user) {
        ArrayList<User> users = (ArrayList<User>) getUserList();
        users.add(user);

        boolean success;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new CustomerUsersJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(users, fileWriter);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }
}
