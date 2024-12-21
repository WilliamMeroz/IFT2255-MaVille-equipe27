package maville.equipe27.helpers;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.Travail;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.List;

/**
 * {@code HTTPRequestsHelper} est une classe utilitaire pour effectuer des requêtes HTTP afin de récupérer
 * des données sur les travaux et les entraves depuis l'API de la ville de Montréal. Elle utilise la bibliothèque
 * {@link HttpClient} de Java pour envoyer des requêtes GET et analyse les réponses JSON avec {@link Gson}.
 */
public class HTTPRequestsHelper {

    private final String BASE_URL = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id=";
    private final String RESSOURCE_TRAVAUX = "cc41b532-f12d-40fb-9f55-eb58c9a2b12b";
    private final String RESSOURCE_ENTRAVE = "a2bc8014-488c-495d-941b-e7ae1999d1bd";
    private String encodedRessourceTravaux;
    private String encodedRessourceEntrave;
    public HttpClient client;

    /**
     * Constructeur par défaut pour initialiser le client HTTP et encoder les identifiants des ressources.
     */
    public HTTPRequestsHelper() {
        this.client = HttpClient.newHttpClient();
        this.encodedRessourceTravaux = URLEncoder.encode(RESSOURCE_TRAVAUX, StandardCharsets.UTF_8);
        this.encodedRessourceEntrave = URLEncoder.encode(RESSOURCE_ENTRAVE, StandardCharsets.UTF_8);
    }

    /**
     * Effectue une requête HTTP pour récupérer des entraves filtrées par un attribut spécifique.
     *
     * @param attributeName Le nom de l'attribut sur lequel appliquer le filtre.
     * @param attributeValue La valeur de l'attribut pour le filtre.
     * @return Une liste d'objets {@link Entrave} correspondant au filtre.
     */
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

    /**
     * Effectue une requête HTTP pour récupérer des travaux filtrés par un attribut spécifique.
     *
     * @param attributeName Le nom de l'attribut sur lequel appliquer le filtre.
     * @param attributeValue La valeur de l'attribut pour le filtre.
     * @return Une liste d'objets {@link Travail} correspondant au filtre.
     */
    public List<Travail> getTravauxFromFilter(String attributeName, String attributeValue) {
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

    /**
     * Récupère toutes les entraves sur une rue donnée.
     *
     * @param street Le nom de la rue pour laquelle récupérer les entraves.
     * @return Une liste d'objets {@link Entrave} correspondant à la rue spécifiée.
     */
    public List<Entrave> getEntravesByStreet(String street) {
        return getEntravesFromFilter("streetid", street + " ");
    }

    /**
     * Récupère toutes les entraves en fonction de l'ID de la demande.
     *
     * @param idRequest L'ID de la demande pour filtrer les entraves.
     * @return Une liste d'objets {@link Entrave} correspondant à l'ID de la demande.
     */
    public List<Entrave> getEntravesByIdRequest(String idRequest) {
        return getEntravesFromFilter("id_request", idRequest);
    }

    /**
     * Récupère tous les travaux en cours à la date actuelle.
     *
     * @return Une liste d'objets {@link Travail} représentant les travaux en cours.
     */
    public List<Travail> getCurrentTravaux() {
        return getTravauxFromFilter(null, null)
                .stream()
                .filter(travail -> travail.getDebut().isBefore(LocalDate.now()) && travail.getFin().isAfter(LocalDate.now()))
                .toList();
    }

    /**
     * Récupère tous les travaux en cours dans un quartier spécifique.
     *
     * @param quartier Le nom du quartier pour lequel récupérer les travaux.
     * @return Une liste d'objets {@link Travail} correspondant au quartier spécifié.
     */
    public List<Travail> getTravauxByQuartier(String quartier) {
        return getCurrentTravaux().stream().filter(travail -> travail.getQuartier().equals(quartier)).toList();
    }

    /**
     * Récupère tous les travaux en cours d'un type spécifique.
     *
     * @param type Le type de travail pour filtrer les travaux.
     * @return Une liste d'objets {@link Travail} correspondant au type de travail spécifié.
     */
    public List<Travail> getTravauxByType(String type) {
        return getCurrentTravaux().stream().filter(travail -> travail.getType() == TravauxTypes.valueOf(type)).toList();
    }

    /**
     * Récupère tous les travaux futurs dans les trois prochains mois.
     *
     * @return Une liste d'objets {@link Travail} représentant les travaux futurs.
     */
    public List<Travail> getFutureTravaux() {
        return getTravauxFromFilter(null, null)
                .stream()
                .filter(travail -> travail.getDebut().isBefore(LocalDate.now().plusMonths(3)))
                .toList();
    }

    /**
     * Récupère tous les travaux futurs dans un quartier spécifique.
     *
     * @param quartier Le nom du quartier pour lequel récupérer les travaux futurs.
     * @return Une liste d'objets {@link Travail} correspondant au quartier spécifié.
     */
    public List<Travail> getFutureTravauxByQuartier(String quartier) {
        return getFutureTravaux()
                .stream()
                .filter(travail -> travail.getQuartier().equals(quartier))
                .toList();
    }

    /**
     * Récupère tous les travaux futurs d'un type spécifique.
     *
     * @param type Le type de travail pour filtrer les travaux futurs.
     * @return Une liste d'objets {@link Travail} correspondant au type de travail spécifié.
     */
    public List<Travail> getFutureTravauxByType(String type) {
        return getTravauxByType(type)
                .stream()
                .filter(travail ->
                        (travail.getDebut().isAfter(LocalDate.now())) && travail.getDebut().isBefore(LocalDate.now().plusMonths(3)))
                .toList();
    }
}
