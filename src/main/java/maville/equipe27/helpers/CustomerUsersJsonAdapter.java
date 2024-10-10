package maville.equipe27.helpers;

import com.google.gson.*;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.Intervenant;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CustomerUsersJsonAdapter implements JsonDeserializer<List<User>>, JsonSerializer<List<User>> {
    @Override
    public List<User> deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        List<User> users = new ArrayList<>();
        if (jsonElement.isJsonArray()) {
            JsonArray jsonArray = jsonElement.getAsJsonArray();
            for (JsonElement element : jsonArray) {
                JsonObject newObject = element.getAsJsonObject();

                if (newObject.has("role")) {
                    String role = newObject.get("role").getAsString();

                    if ("2".equals(role)) {
                        Intervenant intervenant = jsonDeserializationContext.deserialize(newObject, Intervenant.class);
                        users.add(intervenant);
                    } else if ("1".equals(role)) {
                        String email = newObject.get("email").getAsString();
                        String password = newObject.get("password").getAsString();
                        RoleChoices actualRole = RoleChoices.RÉSIDENT;
                        String firstname = newObject.get("firstname").getAsString();
                        String lastname = newObject.get("lastname").getAsString();
                        LocalDate dob = LocalDate.parse(newObject.get("dob").getAsString());
                        String phone = newObject.get("phone").getAsString();
                        String address = newObject.get("address").getAsString();
                        Resident resident = new Resident(email, password, actualRole, firstname, lastname, dob, phone, address);
                        users.add(resident);
                    }
                } else throw new JsonParseException("Error while parsing users.json");
            }
        } else throw new JsonParseException("Expected a JSON array");

        return users;
    }

    @Override
    public JsonElement serialize(List<User> users, Type type, JsonSerializationContext jsonSerializationContext) {
        JsonArray jsonArray = new JsonArray();
        for (User u : users) {
            JsonObject jsonObject = new JsonObject();

            jsonObject.addProperty("email", u.getEmail());
            jsonObject.addProperty("password", u.getPassword());
            jsonObject.addProperty("role", u.getRole() == RoleChoices.RÉSIDENT ? "1" : "2");
            jsonObject.addProperty("firstname", u.getFirstname());
            jsonObject.addProperty("lastname", u.getLastname());

            if (u instanceof Resident) {
                Resident resident = (Resident)u;
                jsonObject.addProperty("dob", resident.getDob().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                jsonObject.addProperty("phone", resident.getPhone());
                jsonObject.addProperty("address", resident.getAddress());
            } else {
                Intervenant intervenant = (Intervenant)u;
                jsonObject.addProperty("companyChoices", intervenant.getCompanyChoices().toString());
                jsonObject.addProperty("cityIdentifier", intervenant.getCityIdentifier());
            }

            jsonArray.add(jsonObject);
        }

        return jsonArray;
    }
}
