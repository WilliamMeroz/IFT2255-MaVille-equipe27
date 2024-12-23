package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.RequeteTravail;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * {@code RequeteTravailJsonAdapter} est un adaptateur personnalisé pour la sérialisation et la désérialisation des objets {@link RequeteTravail}.
 * Il convertit les objets {@link RequeteTravail} en JSON et vice versa, en utilisant les interfaces Gson {@link JsonSerializer} et {@link JsonDeserializer}.
 * Utilisé par la librairie Gson.
 */
public class RequeteTravailJsonAdapter implements JsonDeserializer<List<RequeteTravail>>, JsonSerializer<List<RequeteTravail>> {

    /**
     * Désérialise un élément JSON en une liste de requêtes de travail {@link RequeteTravail}.
     *
     * @param jsonElement L'élément JSON à désérialiser.
     * @param type Le type de la donnée à désérialiser.
     * @param jsonDeserializationContext Le contexte de désérialisation.
     * @return Une liste de requêtes de travail {@link RequeteTravail} désérialisées.
     * @throws JsonParseException Si le JSON est mal formé.
     */
    @Override
    public List<RequeteTravail> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<RequeteTravail> requeteTravails = new ArrayList<>();

        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject newObject = element.getAsJsonObject();
                String titre = newObject.get("titre").getAsString();
                String desc = newObject.get("description").getAsString();
                String typeDesc = newObject.get("type").getAsString();
                TravauxTypes travailType = TravauxTypes.valueOf(typeDesc);
                String dateStr = newObject.get("date").getAsString();
                LocalDate debut = LocalDate.parse(dateStr);
                String status = newObject.get("status").getAsString();
                String owner = newObject.get("owner").getAsString();

                JsonArray candidatesJSON = newObject.getAsJsonArray("candidates");
                RequeteTravail r = new RequeteTravail(owner, titre, desc, travailType, debut, status);
                for (int i = 0; i < candidatesJSON.size(); i++)
                    r.addCandidature(candidatesJSON.get(i).getAsString());


                requeteTravails.add(r);
            }
        }

        return requeteTravails;
    }

    /**
     * Sérialise une liste de requêtes de travail {@link RequeteTravail} en un élément JSON.
     *
     * @param requeteTravails La liste de requêtes de travail à sérialiser.
     * @param type Le type de la donnée à sérialiser.
     * @param jsonSerializationContext Le contexte de sérialisation.
     * @return L'élément JSON représentant la liste de requêtes de travail.
     */
    @Override
    public JsonElement serialize(List<RequeteTravail> requeteTravails, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();

        for (RequeteTravail t : requeteTravails) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("titre", t.getTitreTravail());
            jsonObject.addProperty("description", t.getDescription());
            jsonObject.addProperty("type", t.getTypeTravail().toString());
            jsonObject.addProperty("date", t.getDateDebut().toString());
            jsonObject.addProperty("status", t.getStatus());
            jsonObject.addProperty("owner", t.getOwner());

            JsonArray candidates = new JsonArray();
            for (String c : t.getCandidates())
                candidates.add(c);

            jsonObject.add("candidates", candidates);
            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
