package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.Projet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * {@code ProjectDataStore} est une classe responsable de la gestion de la persistance des projets dans un fichier JSON.
 * Elle offre des méthodes pour sauvegarder, mettre à jour et récupérer des projets depuis un fichier.
 */
public class ProjectDataStore {
    private final String FILE_NAME;

    /**
     * Constructeur qui initialise le nom du fichier où les projets seront stockés.
     *
     * @param filename Le nom du fichier JSON où les projets seront stockés.
     */
    public ProjectDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    /**
     * Sauvegarde un nouveau projet dans le fichier JSON. Si un projet avec le même titre existe déjà, il ne sera pas ajouté.
     *
     * @param projet Le projet à sauvegarder.
     * @return {@code true} si le projet a été sauvegardé avec succès, {@code false} si un projet avec le même titre existe déjà.
     */
    public boolean saveProject(Projet projet) {
        ArrayList<Projet> projets = new ArrayList<>(getProjets());

        // Vérifie si un projet avec le même titre existe déjà
        if (!projets.stream().filter(p -> p.getTitre().equals(projet.getTitre())).toList().isEmpty())
            return false;

        projets.add(projet);

        boolean success;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new ProjetJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(projets, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    /**
     * Met à jour un projet existant en changeant son titre. Si le nouveau titre est vide, le projet ne sera pas mis à jour.
     *
     * @param project Le projet à mettre à jour.
     * @param newTitle Le nouveau titre du projet.
     * @return {@code true} si la mise à jour a réussi, {@code false} si aucun projet correspondant n'a été trouvé.
     */
    public boolean updateProject(Projet project, String newTitle) {
        ArrayList<Projet> projets = new ArrayList<>(getProjets());

        boolean success = false;
        for (int i = 0; i < projets.size(); i++) {
            if (projets.get(i).getTitre().equals(project.getTitre())) {
                if (!newTitle.isEmpty()) {
                    project.setTitre(newTitle);
                }

                projets.set(i, project);
                success = true;
                break;
            }
        }

        if (success) {
            try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
                GsonBuilder builder = new GsonBuilder();
                builder.registerTypeHierarchyAdapter(List.class, new ProjetJsonAdapter());
                builder.setPrettyPrinting();
                Gson gson = builder.create();
                gson.toJson(projets, fileWriter);
            } catch (IOException e) {
                success = false;
                e.printStackTrace();
            }
        }

        return success;
    }

    /**
     * Récupère tous les projets stockés dans le fichier JSON.
     *
     * @return Une liste contenant tous les projets.
     */
    public ArrayList<Projet> getProjets() {
        ArrayList<Projet> projets = null;

        try (FileReader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new ProjetJsonAdapter());
            Gson gson = builder.create();
            projets = gson.fromJson(reader, ArrayList.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projets;
    }

    /**
     * Récupère tous les projets associés à l'identifiant de la ville de l'utilisateur actuellement connecté.
     *
     * @return Une liste des projets liés à l'utilisateur connecté.
     */
    public ArrayList<Projet> getUserProjects() {
        String id = ConnectedIntervenant.getInstance().getIntervenant().getCityIdentifier();
        return (ArrayList<Projet>) getProjets().stream().filter(p -> p.getIntervenantCityIdentifier().equals(id)).collect(Collectors.toCollection(ArrayList::new));
    }
}
