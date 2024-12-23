package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.helpers.RequeteTravailJsonAdapter;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.RequeteTravail;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code RequeteTravailDataStore} est une classe responsable de la gestion de la persistance des requêtes de travail dans un fichier JSON.
 * Elle permet de sauvegarder, de récupérer et de filtrer des requêtes de travail.
 */
public class RequeteTravailDataStore {
    private final String FILE_NAME;

    /**
     * Constructeur qui initialise le nom du fichier où les requêtes de travail seront stockées.
     *
     * @param filename Le nom du fichier JSON où les requêtes de travail seront enregistrées.
     */
    public RequeteTravailDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    /**
     * Sauvegarde une nouvelle requête de travail dans le fichier JSON.
     * La requête est ajoutée à la liste existante.
     *
     * @param requeteTravail La requête de travail à sauvegarder.
     * @return {@code true} si la requête a été sauvegardée avec succès, {@code false} en cas d'échec.
     */
    public boolean saveRequete(RequeteTravail requeteTravail) {
        ArrayList<RequeteTravail> requeteTravails = (ArrayList<RequeteTravail>) getRequetes();
        requeteTravails.add(requeteTravail);

        boolean success;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new RequeteTravailJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(requeteTravails, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Récupère toutes les requêtes de travail stockées dans le fichier JSON.
     *
     * @return Une liste contenant toutes les requêtes de travail.
     */
    public List<RequeteTravail> getRequetes() {
        List<RequeteTravail> requeteTravailList = null;
        try (FileReader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(List.class, new RequeteTravailJsonAdapter());
            Gson gson = builder.create();
            requeteTravailList = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return requeteTravailList;
    }

    /**
     * Récupère toutes les requêtes de travail appartenant à un utilisateur spécifique, identifié par son email.
     *
     * @param email L'email de l'utilisateur pour lequel les requêtes de travail doivent être récupérées.
     * @return Une liste de requêtes de travail liées à l'utilisateur.
     */
    public List<RequeteTravail> getRequetesByEmail(String email) {
        return getRequetes().stream()
                .filter(requeteTravail -> requeteTravail.getOwner().equals(email))
                .toList();
    }

    /**
     * Ajoute une candidature pour un intervenant à la requête de travail spécifiée.
     *
     * @param requete La requête de travail à laquelle la candidature est ajoutée.
     * @param candidature L'email de l'intervenant qui postule à la requête de travail.
     * @return {@code true} si la candidature a été ajoutée avec succès, {@code false} en cas d'échec.
     */
    public boolean addCandidate(RequeteTravail requete, String candidature) {
        ArrayList<RequeteTravail> requetes = new ArrayList<>(getRequetes());
        boolean success = false;

        for (RequeteTravail r : requetes) {
            if (r.getOwner().equals(requete.getOwner()) && r.getTitreTravail().equals(requete.getTitreTravail())) {
                r.getCandidates().add(candidature);
                r.setStatus("Candidatures en attente");
                success = true;
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new RequeteTravailJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(requetes, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Accepte une candidature pour une requête de travail, en mettant à jour le statut de la requête.
     *
     * @param email L'email de l'intervenant dont la candidature est acceptée.
     * @return {@code true} si la candidature a été acceptée avec succès, {@code false} en cas d'échec.
     */
    public boolean acceptRequest(String email) {
        ArrayList<RequeteTravail> requetes = new ArrayList<>(getRequetes());
        boolean success = false;

        for (RequeteTravail r : requetes) {
            for (String candidate : r.getCandidates()) {
                if (candidate.equals(email)) {
                    r.setChosenCandidate(email);
                    r.setStatus("Candidature acceptée");
                    success = true;
                    break;
                }
            }
        }

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new RequeteTravailJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(requetes, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Accepte une requête de travail et met à jour son statut à "Requête confirmée".
     *
     * @param requeteTravail La requête de travail à confirmer.
     * @param intervenant L'intervenant qui a été choisi pour exécuter la requête de travail.
     * @return {@code true} si la requête a été confirmée avec succès, {@code false} en cas d'échec.
     */
    public boolean acceptRequete(RequeteTravail requeteTravail, Intervenant intervenant) {
        ArrayList<RequeteTravail> requetes = new ArrayList<>(getRequetes());
        boolean success = false;
        for (RequeteTravail r : requetes) {
            if (r.getTitreTravail().equals(requeteTravail.getTitreTravail()) && r.getChosenCandidate().equals(intervenant.getEmail())) {
                r.setStatus("Requête confirmée");
                success = true;
                break;
            }
        }

        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new RequeteTravailJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(requetes, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }
}
