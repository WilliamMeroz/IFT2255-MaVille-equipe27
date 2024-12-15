package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.Projet;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProjectDataStore {
    private final String FILE_NAME;

    public ProjectDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    public boolean saveProject(Projet projet) {
        ArrayList<Projet> projets = new ArrayList<>(getProjets());

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

    public List<Projet> getProjets() {
        List<Projet> projets = null;

        try (FileReader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new ProjetJsonAdapter());
            Gson gson = builder.create();
            projets = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return projets;
    }

    public List<Projet> getUserProjects() {
        String id = ConnectedIntervenant.getInstance().getIntervenant().getCityIdentifier();
        return getProjets().stream().filter(p -> p.getIntervenantCityIdentifier().equals(id)).toList();
    }
}
