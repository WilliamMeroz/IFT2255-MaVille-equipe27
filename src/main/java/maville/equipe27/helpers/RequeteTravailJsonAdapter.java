package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.*;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class RequeteTravailJsonAdapter implements JsonDeserializer<List<RequeteTravail>>, JsonSerializer<List<RequeteTravail>> {
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
                requeteTravails.add(new RequeteTravail(owner, titre, desc, travailType, debut, status));
            }
        }
        return requeteTravails;
    }

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

            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
