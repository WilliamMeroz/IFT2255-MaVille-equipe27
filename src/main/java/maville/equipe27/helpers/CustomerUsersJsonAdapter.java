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

/**
 * {@code CustomerUsersJsonAdapter} est une classe qui implémente l'interface {@link JsonDeserializer}
 * et {@link JsonSerializer} de la bibliothèque Gson, permettant de personnaliser la manière dont les
 * objets {@link User} (et ses sous-classes {@link Resident} et {@link Intervenant}) sont sérialisés
 * et désérialisés vers/depuis le format JSON. Nécéssaire par la librairie Gson.
 */
public class CustomerUsersJsonAdapter implements JsonDeserializer<List<User>>, JsonSerializer<List<User>> {

    /**
     * Désérialise un élément JSON en une liste d'objets {@link User}.
     *
     * <p>Ce processus différencie les types d'utilisateurs (résident ou intervenant)
     * en fonction du champ "role" et crée l'objet correspondant.</p>
     *
     * @param jsonElement L'élément JSON à désérialiser.
     * @param type Le type de retour, ici {@link List<User>}.
     * @param jsonDeserializationContext Le contexte de désérialisation pour les objets imbriqués.
     * @return La liste des objets {@link User} désérialisée.
     * @throws JsonParseException Si le JSON est mal formé ou si un rôle inconnu est rencontré.
     */
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

    /**
     * Sérialise une liste d'objets {@link User} en un élément JSON.
     *
     * <p>Lors de la sérialisation, les propriétés de chaque utilisateur sont ajoutées au format JSON
     * en fonction de son rôle (résident ou intervenant). Les dates de naissance sont formatées au
     * format {@code yyyy-MM-dd}.</p>
     *
     * @param users La liste des objets {@link User} à sérialiser.
     * @param type Le type de l'objet à sérialiser, ici {@link List<User>}.
     * @param jsonSerializationContext Le contexte de sérialisation pour les objets imbriqués.
     * @return L'élément JSON représentant la liste d'utilisateurs.
     */
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
