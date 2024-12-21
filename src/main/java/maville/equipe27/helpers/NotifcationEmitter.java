package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.Notification;
import maville.equipe27.models.Projet;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;
import org.mockito.internal.matchers.Not;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class NotifcationEmitter {
    private final String FILE_NAME;
    private List<User> residents;
    private UserDataStore userDataStore;

    public NotifcationEmitter(String fileName) {
        this.FILE_NAME = fileName;
        this.userDataStore = new UserDataStore("users.json");
        this.residents = this.userDataStore.getUserList().stream().filter(u -> u.getRole().equals(RoleChoices.RÉSIDENT)).toList();
    }

    public boolean emit(Projet projet) {
        String[] quartiers = projet.getQuartiers();
        ArrayList<Notification> notifications = new ArrayList<>();

        for (String quartier : quartiers) {
            for (User user : residents) {
                Resident resident = (Resident) user;
                String residentQuartier = resident.getAddress().split(",")[2];

                if (residentQuartier.equals(quartier)) {
                    notifications.add(new Notification("Nouveau projet: " + projet.getTitre() + " dans votre quartier!", resident.getEmail(), new Date()));
                }
            }
        }


        return storeNotifications(notifications);
    }

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

    public List<Notification> getNotificationsForUser(Resident resident) {
        return getNotifications().stream().filter(n -> n.getAssociatedEmail().equals(resident.getEmail())).toList();
    }

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
