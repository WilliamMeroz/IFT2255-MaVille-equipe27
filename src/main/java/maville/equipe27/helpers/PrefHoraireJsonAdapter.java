package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.models.PrefHoraire;

import java.lang.reflect.Type;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PrefHoraireJsonAdapter implements JsonDeserializer<List<PrefHoraire>>, JsonSerializer<List<PrefHoraire>> {
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
