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

public class CustomTravauxJsonAdapter implements JsonDeserializer<Travail> {
    private final String CATEGORIES_FILE_NAME = "travaux_categories_mapping.json";

    @Override
    public Travail deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject travail = jsonElement.getAsJsonObject();

        String id = getAsStringSafe(travail, "id");
        String nomIntervenant = getAsStringSafe(travail, "organizationname");
        String quartier = getAsStringSafe(travail, "boroughid");

        String debutString = getAsStringSafe(travail, "duration_start_date");
        LocalDate debut;
        if (debutString == null)
            debut = null;
        else {
            ZonedDateTime zonedDateTimeDebut = ZonedDateTime.parse(travail.get("duration_start_date").getAsString());
            debut = zonedDateTimeDebut.toLocalDate();
        }

        String finString = getAsStringSafe(travail, "duration_end_date");
        LocalDate fin;
        if (finString == null)
            fin = null;
        else {
            ZonedDateTime zonedDateTimeFin = ZonedDateTime.parse(travail.get("duration_end_date").getAsString());
            fin = zonedDateTimeFin.toLocalDate();
        }

        TravauxTypes typeTravail = TravauxTypes.AUTRE;
        String categoryString = getAsStringSafe(travail, "reason_category");
        if (categoryString != null) {
            try (Reader reader = new FileReader(CATEGORIES_FILE_NAME)) {
                JsonObject jsonObject = JsonParser.parseReader(reader).getAsJsonObject();
                if (jsonObject.get(categoryString) != null)
                    typeTravail = TravauxTypes.valueOf(jsonObject.get(categoryString).getAsString());
            } catch (IOException | IllegalArgumentException e) {
                e.printStackTrace();
            }
        }

        return new Travail(id, nomIntervenant, quartier, typeTravail, debut, fin);
    }

    private static String getAsStringSafe(JsonObject jsonObject, String propertyName) {
        JsonElement element = jsonObject.get(propertyName);
        if (element != null && !element.isJsonNull())
            return element.getAsString();
        return null;
    }
}
