package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.Notification;
import maville.equipe27.models.Projet;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * {@code NotifcationEmitter} est une classe responsable de la gestion et de l'émission de notifications
 * pour les projets dans un quartier spécifique. Elle filtre les utilisateurs de type {@link Resident} et
 * leur envoie des notifications en fonction de leur quartier d'habitation.
 */
public class NotifcationEmitter {
    private final String FILE_NAME;
    private List<User> residents;
    UserDataStore userDataStore;

    /**
     * Constructeur qui initialise la classe avec le nom du fichier où les notifications seront stockées
     * et charge les résidents depuis le fichier de données utilisateur.
     *
     * @param fileName Le nom du fichier où les notifications seront enregistrées.
     */
    public NotifcationEmitter(String fileName) {
        this.FILE_NAME = fileName;
        this.userDataStore = new UserDataStore("users.json");
        this.residents = this.userDataStore.getUserList().stream()
                .filter(u -> u.getRole().equals(RoleChoices.RÉSIDENT))
                .toList();
    }

    /**
     * Émet des notifications pour les résidents en fonction des quartiers associés à un projet.
     *
     * @param projet Le projet pour lequel les notifications doivent être envoyées.
     * @return {@code true} si les notifications ont été enregistrées avec succès, {@code false} sinon.
     */
    public boolean emit(Projet projet, String message) {
        String[] quartiers = projet.getQuartiers();
        ArrayList<Notification> notifications = new ArrayList<>();

        for (String quartier : quartiers) {
            for (User user : residents) {
                Resident resident = (Resident) user;
                String residentQuartier = resident.getAddress().split(",")[2];

                if (residentQuartier.equals(quartier)) {
                    notifications.add(new Notification(
                            message,
                            resident.getEmail(),
                            new Date()
                    ));
                }
            }
        }

        return storeNotifications(notifications);
    }

    /**
     * Stocke les notifications dans le fichier JSON spécifié.
     *
     * @param notifications La liste des notifications à stocker.
     * @return {@code true} si les notifications ont été stockées avec succès, {@code false} sinon.
     */
    private boolean storeNotifications(List<Notification> notifications) {
        boolean success;
        ArrayList<Notification> allNotifs = getNotifications();
        allNotifs.addAll(notifications);
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            gson.toJson(allNotifs, fileWriter);
            success = true;
        } catch (IOException e) {
            e.printStackTrace();
            success = false;
        }

        return success;
    }

    /**
     * Récupère toutes les notifications associées à un résident particulier.
     *
     * @param resident Le résident pour lequel récupérer les notifications.
     * @return Une liste de {@link Notification} correspondant au résident spécifié.
     */
    public List<Notification> getNotificationsForUser(Resident resident) {
        return getNotifications().stream()
                .filter(n -> n.getAssociatedEmail().equals(resident.getEmail()))
                .toList();
    }

    /**
     * Charge toutes les notifications existantes à partir du fichier JSON.
     *
     * @return Une liste des notifications enregistrées.
     */
    private ArrayList<Notification> getNotifications() {
        ArrayList<Notification> notifications = null;

        try (FileReader reader = new FileReader(FILE_NAME)) {
            Gson gson = new Gson();
            Type listType = new TypeToken<List<Notification>>() {}.getType();

            notifications = gson.fromJson(reader, listType);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return notifications;
    }
}
