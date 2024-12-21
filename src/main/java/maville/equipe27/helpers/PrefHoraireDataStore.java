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

/**
 * {@code PrefHoraireDataStore} est une classe responsable de la gestion des données de préférences horaires
 * des résidents pour la complétion de travaux dans leurs quartiers. Elle permet de stocker, récupérer et filtrer des objets {@link PrefHoraire} à partir d'un fichier JSON.
 */
public class PrefHoraireDataStore {
    private final String FILE_NAME;

    /**
     * Constructeur de la classe {@code PrefHoraireDataStore}, qui initialise avec le nom du fichier
     * où les préférences horaires seront stockées.
     *
     * @param filename Le nom du fichier où les données de préférence horaire seront enregistrées.
     */
    public PrefHoraireDataStore(String filename) {
        this.FILE_NAME = filename;
    }

    /**
     * Enregistre une préférence horaire dans le fichier, en remplaçant toute préférence existante pour le même utilisateur.
     *
     * @param prefHoraire L'objet {@link PrefHoraire} à enregistrer.
     * @return {@code true} si l'enregistrement a réussi, {@code false} sinon.
     */
    public boolean saveHoraire(PrefHoraire prefHoraire) {
        ArrayList<PrefHoraire> prefs = getPrefs().stream()
                .filter(p -> !p.getEmail().equals(prefHoraire.getEmail()))
                .collect(Collectors.toCollection(ArrayList::new));
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

    /**
     * Récupère toutes les préférences horaires stockées dans le fichier.
     *
     * @return Une liste de {@link PrefHoraire} contenant toutes les préférences horaires.
     */
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

    /**
     * Récupère toutes les préférences horaires associées à un quartier particulier.
     *
     * @param quartier Le nom du quartier pour lequel récupérer les préférences horaires.
     * @return Une liste de {@link PrefHoraire} correspondant aux utilisateurs du quartier spécifié.
     */
    public List<PrefHoraire> getHorairesByQuartier(String quartier) {
        return getPrefs().stream()
                .filter(prefHoraire -> prefHoraire.getQuartier().equals(quartier))
                .toList();
    }

    /**
     * Récupère la préférence horaire d'un utilisateur en fonction de son adresse email.
     *
     * @param email L'email de l'utilisateur dont on souhaite récupérer les préférences horaires.
     * @return Un {@link Optional} contenant la préférence horaire de l'utilisateur, ou vide si aucune préférence n'est trouvée.
     */
    public Optional<PrefHoraire> getHoraireFromEmail(String email) {
        return getPrefs().stream()
                .filter(prefHoraire -> prefHoraire.getEmail().equals(email))
                .findFirst();
    }
}
