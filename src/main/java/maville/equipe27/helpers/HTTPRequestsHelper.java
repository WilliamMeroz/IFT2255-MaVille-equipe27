package maville.equipe27.helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.Travail;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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

        String encodedFilter = URLEncoder.encode(String.format("{\"%s\":\"%s\"}", attributeName, attributeValue), StandardCharsets.UTF_8);
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

    private List<Travail> getTravauxFromFilter(String attributeName, String attributeValue) {
        List<Travail> travaux = null;

        String encodedFilter = URLEncoder.encode(String.format("{\"%s\":\"%s\"}", attributeName, attributeValue), StandardCharsets.UTF_8);
        try {

            URI uri;
            if (attributeName != null && attributeValue != null) uri = new URI(BASE_URL + encodedRessourceTravaux + "&filters=" + encodedFilter);
            else uri = new URI(BASE_URL + encodedRessourceTravaux);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(uri)
                    .header("Accept", "application/json")
                    .GET()
                    .build();

            HttpResponse<String> response = this.client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                Gson gson = new GsonBuilder().registerTypeAdapter(Travail.class, new CustomTravauxJsonAdapter()).setPrettyPrinting().create();
                Type travailType = new TypeToken<List<Travail>>() {}.getType();

                JsonObject jsonObject = JsonParser.parseString(response.body()).getAsJsonObject();
                JsonArray records = jsonObject.getAsJsonObject("result").getAsJsonArray("records");
                travaux = gson.fromJson(records, travailType);
            } else {
                System.out.println("Une erreur est survenue lors de la requête pour les travaux...");
            }
        } catch (IOException | InterruptedException | java.net.URISyntaxException e) {
            e.printStackTrace();
        }

        return travaux;
    }

    public List<Entrave> getEntravesByStreet(String street) {
        return getEntravesFromFilter("streetid", street + " ");
    }

    public List<Entrave> getEntravesByIdRequest(String idRequest) {
        return getEntravesFromFilter("id_request", idRequest);
    }

    public List<Travail> getCurrentTravaux() {
        return getTravauxFromFilter(null, null).stream().filter(travail -> travail.getDebut().isBefore(LocalDate.now())).toList();
    }

    public List<Travail> getTravauxByQuartier(String quartier) {
        return getTravauxFromFilter("boroughid", quartier);
    }

    public List<Travail> getTravauxByType(String type) {
        return getCurrentTravaux().stream().filter(travail -> travail.getType() == TravauxTypes.valueOf(type)).toList();
    }

    public List<Travail> getFutureTravaux(List<Travail> travaux, int numFutureMonths) {
        return getTravauxFromFilter(null, null)
                .stream()
                .filter(travail ->
                        (travail.getDebut().isAfter(LocalDate.now())) && travail.getDebut().isBefore(LocalDate.now().plusMonths(numFutureMonths)))
                .toList();
    }
}
