package maville.equipe27.helpers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import maville.equipe27.models.PrefHoraire;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PrefHoraireDataStore {
    private final String FILE_NAME;

    public PrefHoraireDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    public boolean saveHoraire(PrefHoraire prefHoraire) {
        ArrayList<PrefHoraire> prefs = getPrefs().stream().filter(p -> !p.getEmail().equals(prefHoraire.getEmail())).collect(Collectors.toCollection(ArrayList::new));
        prefs.add(prefHoraire);

        boolean success;
        try (FileWriter fileWriter = new FileWriter(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeHierarchyAdapter(List.class, new PrefHoraireJsonAdapter());
            builder.setPrettyPrinting();
            Gson gson = builder.create();
            gson.toJson(prefs, fileWriter);
            success = true;
        } catch (IOException e) {
            success = false;
            e.printStackTrace();
        }

        return success;
    }

    public List<PrefHoraire> getPrefs() {
        List<PrefHoraire> prefs = null;

        try (FileReader reader = new FileReader(FILE_NAME)) {
            GsonBuilder builder = new GsonBuilder();
            builder.registerTypeAdapter(List.class, new PrefHoraireJsonAdapter());
            Gson gson = builder.create();
            prefs = gson.fromJson(reader, List.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return prefs;
    }

    public List<PrefHoraire> getHorairesByQuartier(String quartier) {
        return getPrefs().stream().filter(prefHoraire -> prefHoraire.getQuartier().equals(quartier)).toList();
    }

    public Optional<PrefHoraire> getHoraireFromEmail(String email) {
        return getPrefs().stream().filter(prefHoraire -> prefHoraire.getEmail().equals(email)).findFirst();
    }
}
