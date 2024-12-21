package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Travail;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZonedDateTime;

/**
 * {@code CustomTravauxJsonAdapter} est une classe qui implémente l'interface {@link JsonDeserializer}
 * de la bibliothèque Gson, permettant de personnaliser la manière dont les objets {@link Travail}
 * sont désérialisés depuis le format JSON. Nécéssaire par la librairie Gson.
 */
public class CustomTravauxJsonAdapter implements JsonDeserializer<Travail> {

    private final String CATEGORIES_FILE_NAME = "travaux_categories_mapping.json";

    /**
     * Désérialise un élément JSON en un objet {@link Travail}.
     *
     * <p>Cette méthode extrait les informations pertinentes du JSON, telles que l'ID, le nom de l'intervenant,
     * le titre du travail, les dates de début et de fin, ainsi que le type de travail (déterminé à partir d'un fichier
     * de mappage des catégories).</p>
     *
     * @param jsonElement L'élément JSON à désérialiser.
     * @param type Le type de retour, ici {@link Travail}.
     * @param jsonDeserializationContext Le contexte de désérialisation pour les objets imbriqués.
     * @return L'objet {@link Travail} désérialisé à partir du JSON.
     * @throws JsonParseException Si le JSON est mal formé ou si un champ attendu est manquant.
     */
    @Override
    public Travail deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject travail = jsonElement.getAsJsonObject();

        String id = getAsStringSafe(travail, "id");
        String nomIntervenant = getAsStringSafe(travail, "organizationname");
        String titre = getAsStringSafe(travail, "reason_category") + " dans la zone " + getAsStringSafe(travail, "occupancy_name");
        String quartier = getAsStringSafe(travail, "boroughid");

        // Parsing des dates de début et de fin
        String debutString = getAsStringSafe(travail, "duration_start_date");
        LocalDate debut = null;
        if (debutString != null) {
            ZonedDateTime zonedDateTimeDebut = ZonedDateTime.parse(debutString);
            debut = zonedDateTimeDebut.toLocalDate();
        }

        String finString = getAsStringSafe(travail, "duration_end_date");
        LocalDate fin = null;
        if (finString != null) {
            ZonedDateTime zonedDateTimeFin = ZonedDateTime.parse(finString);
            fin = zonedDateTimeFin.toLocalDate();
        }

        // Détermination du type de travail
        TravauxTypes typeTravail = TravauxTypes.AUTRE;
        String categoryString = getAsStringSafe(travail, "reason_category");
        if (categoryString != null) {
            try (Reader reader = new FileReader(CATEGORIES_FILE_NAME)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                if (jsonObject.get(categoryString) != null) {
                    typeTravail = TravauxTypes.valueOf(jsonObject.get(categoryString).getAsString());
                }
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return new Travail(id, nomIntervenant, titre, quartier, typeTravail, debut, fin);
    }

    /**
     * Méthode utilitaire pour obtenir une valeur de propriété d'un objet JSON de manière sécurisée.
     *
     * @param jsonObject L'objet JSON contenant la propriété.
     * @param propertyName Le nom de la propriété à extraire.
     * @return La valeur de la propriété sous forme de chaîne de caractères, ou {@code null} si la propriété est absente.
     */
    private static String getAsStringSafe(JsonObject jsonObject, String propertyName) {
        JsonElement element = jsonObject.get(propertyName);
        if (element != null && !element.isJsonNull()) {
            return element.getAsString();
        }
        return null;
    }
}
