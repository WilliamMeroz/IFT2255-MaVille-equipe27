package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.enums.ProjetStatus;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Projet;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * {@code ProjetJsonAdapter} est un adaptateur JSON utilisé pour la sérialisation et la désérialisation des objets {@link Projet}.
 * Il permet de convertir une liste de projets en format JSON et de la reconvertir en objets {@link Projet}. Utilisé par la librairie Gson.
 */
public class ProjetJsonAdapter implements JsonDeserializer<List<Projet>>, JsonSerializer<List<Projet>> {

    /**
     * Désérialise un élément JSON en une liste de projets.
     *
     * @param jsonElement L'élément JSON à désérialiser.
     * @param type Le type attendu (ici une liste de projets).
     * @param jsonDeserializationContext Le contexte de désérialisation.
     * @return Une liste de projets désérialisée depuis le JSON.
     * @throws JsonParseException Si le JSON ne peut pas être converti en objets {@link Projet}.
     */
    @Override
    public List<Projet> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<Projet> projets = new ArrayList<>();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject newObject = element.getAsJsonObject();
                String intervenantCityIdentifier = newObject.get("intervenantCityIdentifier").getAsString();
                String titre = newObject.get("titre").getAsString();
                String desc = newObject.get("desc").getAsString();
                TravauxTypes travailType = TravauxTypes.valueOf(newObject.get("type").getAsString());
                JsonArray quartiersJson = newObject.get("quartiers").getAsJsonArray();
                String[] quartiers = new String[quartiersJson.size()];
                JsonArray ruesJson = newObject.get("rues").getAsJsonArray();
                String[] rues = new String[ruesJson.size()];

                for (int i = 0; i < quartiersJson.size(); i++) {
                    quartiers[i] = quartiersJson.get(i).getAsString();
                }

                for (int i = 0; i < ruesJson.size(); i++) {
                    rues[i] = ruesJson.get(i).getAsString();
                }

                LocalDate debut = LocalDate.parse(newObject.get("debut").getAsString());
                LocalDate fin = LocalDate.parse(newObject.get("fin").getAsString());
                LocalTime horaireDebut = LocalTime.parse(newObject.get("horaireDebut").getAsString());
                LocalTime horaireFin = LocalTime.parse(newObject.get("horaireFin").getAsString());
                ProjetStatus status = ProjetStatus.valueOf(newObject.get("status").getAsString());

                projets.add(new Projet(intervenantCityIdentifier, titre, desc, travailType, quartiers, debut, fin, horaireDebut, horaireFin, status, rues));
            }
        }

        return projets;
    }

    /**
     * Sérialise une liste de projets en un élément JSON.
     *
     * @param projets La liste de projets à sérialiser.
     * @param type Le type des objets à sérialiser.
     * @param jsonSerializationContext Le contexte de sérialisation.
     * @return Un élément JSON représentant la liste de projets.
     */
    @Override
    public JsonElement serialize(List<Projet> projets, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        for (Projet p : projets) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("intervenantCityIdentifier", p.getIntervenantCityIdentifier());
            jsonObject.addProperty("titre", p.getTitre());
            jsonObject.addProperty("desc", p.getDesc());
            jsonObject.addProperty("type", p.getType().toString());

            JsonArray quartiersArray = new JsonArray();
            Arrays.stream(p.getQuartiers()).forEach(quartiersArray::add);

            JsonArray ruesArray = new JsonArray();
            Arrays.stream(p.getRues()).forEach(ruesArray::add);

            jsonObject.add("quartiers", quartiersArray);
            jsonObject.addProperty("debut", p.getDebut().toString());
            jsonObject.addProperty("fin", p.getFin().toString());
            jsonObject.addProperty("horaireDebut", p.getHoraireDebut().toString());
            jsonObject.addProperty("horaireFin", p.getHoraireFin().toString());
            jsonObject.addProperty("status", p.getStatus().toString());
            jsonObject.add("rues", ruesArray);

            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
