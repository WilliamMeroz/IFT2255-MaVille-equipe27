package maville.equipe27.helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import maville.equipe27.models.Entrave;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HTTPRequestsHelper {
    private final String BASE_URL = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=";
    private final String RESSOURCE_TRAVAUX = "cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
    private final String RESSOURCE_ENTRAVE = "a2bc8014-488c-495d-941b-e7ae1999d1bd";
    private String encodedRessourceTravaux;
    private String encodedRessourceEntrave;
    private final HttpClient client;

    public HTTPRequestsHelper() {
        this.client = HttpClient.newHttpClient();
        this.encodedRessourceTravaux = URLEncoder.encode(RESSOURCE_TRAVAUX, StandardCharsets.UTF_8);
        this.encodedRessourceEntrave = URLEncoder.encode(RESSOURCE_ENTRAVE, StandardCharsets.UTF_8);
    }

    private List<Entrave> getEntravesFromFilter(String attributeName, String attributeValue) {
        List<Entrave> returnedEntraves = null;

        String encodedFilter = URLEncoder.encode(String.format("{\"%s\":\"%s \"}", attributeName, attributeValue), StandardCharsets.UTF_8);
        try {
            URI uri = new URI(BASE_URL + encodedRessourceEntrave + "&filters=" + encodedFilter);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new GsonBuilder().setPrettyPrinting().create();
                Type listType = new TypeToken<List<Entrave>>() {}.getType();

                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonArray records = jsonObject.getAsJsonObject("result").getAsJsonArray("records");
                returnedEntraves = gson.fromJson(records, listType);
            } else {
                System.out.println("Erreur lors de la requête pour les entraves...");
            }

        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }

        return returnedEntraves;
    }

    public List<Entrave> getEntravesByStreet(String street) {
        return getEntravesFromFilter("streetid", street);
    }

    public List<Entrave> getEntravesByIdRequest(String idRequest) {
        return getEntravesFromFilter("id_request", idRequest);
    }
}
