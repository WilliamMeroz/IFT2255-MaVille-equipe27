package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.models.PrefHoraire;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code PrefHoraireJsonAdapter} est une classe personnalisée pour la sérialisation et la désérialisation des
 * objets {@link PrefHoraire} en utilisant la bibliothèque Gson.
 */
public class PrefHoraireJsonAdapter implements JsonDeserializer<List<PrefHoraire>>, JsonSerializer<List<PrefHoraire>> {

    /**
     * Désérialise un élément JSON en une liste de préférences horaires {@link PrefHoraire}.
     * Cette méthode est utilisée pour convertir un tableau JSON contenant des informations sur les préférences horaires en objets Java.
     *
     * @param jsonElement L'élément JSON à désérialiser.
     * @param type Le type de la classe cible (en général {@link List<PrefHoraire>}).
     * @param jsonDeserializationContext Le contexte de désérialisation fourni par Gson.
     * @return Une liste d'objets {@link PrefHoraire} représentant les données des préférences horaires.
     * @throws JsonParseException Si une erreur survient lors de la désérialisation du JSON.
     */
    @Override
    public List<PrefHoraire> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<PrefHoraire> prefs = new ArrayList<>();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject newObject = element.getAsJsonObject();
                String email = newObject.get("email").getAsString();
                LocalTime de = LocalTime.parse(newObject.get("de").getAsString());
                LocalTime a = LocalTime.parse(newObject.get("a").getAsString());
                String quartier = newObject.get("quartier").getAsString();

                prefs.add(new PrefHoraire(email, de, a, quartier));
            }
        }

        return prefs;
    }

    /**
     * Sérialise une liste de préférences horaires {@link PrefHoraire} en un tableau JSON.
     * Cette méthode est utilisée pour convertir une liste d'objets {@link PrefHoraire} en une représentation JSON.
     *
     * @param prefHoraires La liste de préférences horaires à sérialiser.
     * @param type Le type de la classe cible (en général {@link List<PrefHoraire>}).
     * @param jsonSerializationContext Le contexte de sérialisation fourni par Gson.
     * @return Un élément JSON représentant la liste de préférences horaires.
     */
    @Override
    public JsonElement serialize(List<PrefHoraire> prefHoraires, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        for (PrefHoraire p : prefHoraires) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("email", p.getEmail());
            jsonObject.addProperty("de", p.getDe().toString());
            jsonObject.addProperty("a", p.getA().toString());
            jsonObject.addProperty("quartier", p.getQuartier());

            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
