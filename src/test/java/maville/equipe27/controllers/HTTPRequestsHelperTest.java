package maville.equipe27.controllers;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.Travail;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class HTTPRequestsHelperTests {

    private HTTPRequestsHelper httpRequestsHelper;
    private HttpClient mockHttpClient;

    @BeforeEach
    void setUp() {
        mockHttpClient = mock(HttpClient.class);
        httpRequestsHelper = new HTTPRequestsHelper();
        httpRequestsHelper.client = mockHttpClient; // Inject the mock client
    }

    @Test
    void testGetEntravesByStreet() throws Exception {
        // Arrange
        String street = "Main Street ";
        String encodedStreet = "Main+Street+";
        String expectedUri = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id="
                + URLEncoder.encode("a2bc8014-488c-495d-941b-e7ae1999d1bd", StandardCharsets.UTF_8)
                + "&filters=" + URLEncoder.encode("{\"streetid\":\"" + street + "\"}", StandardCharsets.UTF_8);

        // Mock HTTP response
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        // Create a fake JSON response
        JsonObject jsonResponse = new JsonObject();
        JsonObject resultObject = new JsonObject();
        JsonArray recordsArray = new JsonArray();

        JsonObject entraveJson = new JsonObject();
        entraveJson.addProperty("streetid", street);
        entraveJson.addProperty("id_request", "123");
        recordsArray.add(entraveJson);

        resultObject.add("records", recordsArray);
        jsonResponse.add("result", resultObject);

        when(mockResponse.body()).thenReturn(jsonResponse.toString());

        // Mock the client.send() method
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        List<Entrave> entraves = httpRequestsHelper.getEntravesByStreet("Main Street");

        // Assert
        assertNotNull(entraves);
        assertEquals(1, entraves.size());
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }


    @Test
    void testGetTravauxByQuartier() throws Exception {
        // Arrange
        String quartier = "Downtown";
        // Mock getCurrentTravaux() to return predefined data
        HTTPRequestsHelper helperSpy = spy(httpRequestsHelper);
        Travail travail1 = new Travail();
        travail1.setId("1");
        travail1.setQuartier("Downtown");
        travail1.setType(TravauxTypes.ROUTIER);
        travail1.setDebut(LocalDate.now().minusDays(1));
        travail1.setFin(LocalDate.now().plusDays(5));

        Travail travail2 = new Travail();
        travail2.setId("2");
        travail2.setQuartier("Uptown");
        travail2.setType(TravauxTypes.ROUTIER);
        travail2.setDebut(LocalDate.now().minusDays(2));
        travail2.setFin(LocalDate.now().plusDays(3));

        doReturn(Arrays.asList(travail1, travail2)).when(helperSpy).getCurrentTravaux();

        // Act
        List<Travail> travaux = helperSpy.getTravauxByQuartier(quartier);

        // Assert
        assertNotNull(travaux);
        assertEquals(1, travaux.size());
        assertEquals("1", travaux.get(0).getId());
        assertEquals("Downtown", travaux.get(0).getQuartier());
    }

    @Test
    void testGetFutureTravaux() throws Exception {
        // Arrange
        // Mock getTravauxFromFilter() to return predefined data
        HTTPRequestsHelper helperSpy = spy(httpRequestsHelper);
        Travail travail1 = new Travail();
        travail1.setId("1");
        travail1.setDebut(LocalDate.now().plusDays(1));
        travail1.setFin(LocalDate.now().plusDays(10));

        Travail travail2 = new Travail();
        travail2.setId("2");
        travail2.setDebut(LocalDate.now().plusMonths(4)); // Outside 3 months range
        travail2.setFin(LocalDate.now().plusMonths(5));

        doReturn(Arrays.asList(travail1, travail2)).when(helperSpy).getTravauxFromFilter(null, null);

        // Act
        List<Travail> travaux = helperSpy.getFutureTravaux();

        // Assert
        assertNotNull(travaux);
        assertEquals(1, travaux.size());
        assertEquals("1", travaux.get(0).getId());
    }

    @Test
    void testGetEntravesByIdRequest() throws Exception {
        // Arrange
        String idRequest = "123";
        String expectedUri = "https://donnees.montreal.ca/api/3/action/datastore_search?resource_id="
                + URLEncoder.encode("a2bc8014-488c-495d-941b-e7ae1999d1bd", StandardCharsets.UTF_8)
                + "&filters=" + URLEncoder.encode("{\"id_request\":\"" + idRequest + "\"}", StandardCharsets.UTF_8);

        // Mock HTTP response
        HttpResponse<String> mockResponse = mock(HttpResponse.class);
        when(mockResponse.statusCode()).thenReturn(200);

        // Create a fake JSON response
        JsonObject jsonResponse = new JsonObject();
        JsonObject resultObject = new JsonObject();
        JsonArray recordsArray = new JsonArray();

        JsonObject entraveJson = new JsonObject();
        entraveJson.addProperty("streetid", "Main Street ");
        entraveJson.addProperty("id_request", idRequest);
        recordsArray.add(entraveJson);

        resultObject.add("records", recordsArray);
        jsonResponse.add("result", resultObject);

        when(mockResponse.body()).thenReturn(jsonResponse.toString());

        // Mock the client.send() method
        when(mockHttpClient.send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class)))
                .thenReturn(mockResponse);

        // Act
        List<Entrave> entraves = httpRequestsHelper.getEntravesByIdRequest(idRequest);

        // Assert
        assertNotNull(entraves);
        assertEquals(1, entraves.size());
        verify(mockHttpClient).send(any(HttpRequest.class), any(HttpResponse.BodyHandler.class));
    }

    @Test
    void test() {
        assertEquals(1, 2);
    }
}
