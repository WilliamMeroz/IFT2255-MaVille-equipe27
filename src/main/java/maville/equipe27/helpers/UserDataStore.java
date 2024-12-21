package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.User;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code UserDataStore} est une classe qui gère la persistance des utilisateurs dans un fichier JSON.
 * Elle offre des méthodes pour récupérer, ajouter et sauvegarder des utilisateurs dans un fichier.
 */
public class UserDataStore {
    private final String FILE_NAME;

    /**
     * Constructeur qui initialise le nom du fichier où les utilisateurs seront stockés.
     *
     * @param filename Le nom du fichier JSON où les utilisateurs seront enregistrés.
     */
    public UserDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    /**
     * Récupère la liste des utilisateurs stockés dans le fichier JSON.
     *
     * @return La liste des utilisateurs.
     */
    public List<User> getUserList() {
        List<User> users = null;
        try (Reader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new CustomerUsersJsonAdapter());
            Gson gson = builder.create();
            users = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            // Nous devrions ajouter un véritable traitement des exceptions ici.
            e.printStackTrace();
        }

        return users;
    }

    /**
     * Récupère un utilisateur en fonction de son adresse email.
     *
     * @param email L'adresse email de l'utilisateur à récupérer.
     * @return L'utilisateur correspondant à l'email, ou {@code null} si aucun utilisateur n'est trouvé.
     */
    public User fetchUser(String email) {
        return getUserList().stream().filter(user -> user.getEmail().equals(email)).findFirst().orElse(null);
    }

    /**
     * Sauvegarde un nouvel utilisateur dans le fichier JSON.
     *
     * @param user L'utilisateur à sauvegarder.
     * @return {@code true} si l'utilisateur a été sauvegardé avec succès, {@code false} en cas d'échec.
     */
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
