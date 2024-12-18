package maville.equipe27.controllers;

import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.helpers.RequeteTravailDataStore;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.RequeteTravail;
import maville.equipe27.models.Travail;
import maville.equipe27.views.ResidentView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ResidentControllerTest {

    @Mock
    private HTTPRequestsHelper httpRequestsHelper;

    @Mock
    private RequeteTravailDataStore requeteTravailDataStore;

    @Mock
    private ResidentView residentView;

    private ResidentController residentController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        residentController = new ResidentController(residentView);
        residentController.httpRequestsHelper = httpRequestsHelper;
        residentController.requeteTravailDataStore = requeteTravailDataStore;
    }

    @Test
    void testConsulterEntravesRue() {
        // Arrange
        String rue = "Main Street";
        List<Entrave> expectedEntraves = Arrays.asList(new Entrave(), new Entrave());
        when(httpRequestsHelper.getEntravesByStreet(rue)).thenReturn(expectedEntraves);

        // Act
        List<Entrave> actualEntraves = residentController.consulterEntravesRue(rue);

        // Assert
        assertEquals(expectedEntraves, actualEntraves);
        verify(httpRequestsHelper).getEntravesByStreet(rue);
    }

    @Test
    void testConsulterTravauxParQuartier() {
        // Arrange
        String quartier = "Downtown";
        List<Travail> expectedTravaux = Arrays.asList(new Travail(), new Travail());
        when(httpRequestsHelper.getTravauxByQuartier(quartier)).thenReturn(expectedTravaux);

        // Act
        List<Travail> actualTravaux = residentController.consulterTravauxParQuartier(quartier);

        // Assert
        assertEquals(expectedTravaux, actualTravaux);
        verify(httpRequestsHelper).getTravauxByQuartier(quartier);
    }

    @Test
    void testConsulterTravauxParType() {
        // Arrange
        TravauxTypes type = TravauxTypes.CONSTRUCTION;
        List<Travail> expectedTravaux = Arrays.asList(new Travail(), new Travail());
        when(httpRequestsHelper.getTravauxByType(type.toString())).thenReturn(expectedTravaux);

        // Act
        List<Travail> actualTravaux = residentController.consulterTravauxParType(type);

        // Assert
        assertEquals(expectedTravaux, actualTravaux);
        verify(httpRequestsHelper).getTravauxByType(type.toString());
    }

    @Test
    void testSubmitRequeteTravail() {
        // Arrange
        RequeteTravail requeteTravail = new RequeteTravail("resident@gmail.com", "test", "test", TravauxTypes.ROUTIER, LocalDate.now());
        when(requeteTravailDataStore.saveRequete(requeteTravail)).thenReturn(true);

        // Act
        boolean result = requeteTravailDataStore.saveRequete(requeteTravail);

        // Assert
        assertEquals(true, result);
        verify(requeteTravailDataStore).saveRequete(requeteTravail);
    }

    @Test
    void testShowTravauxFuturs() {
        // Arrange
        List<Travail> expectedTravaux = Arrays.asList(new Travail(), new Travail());
        when(httpRequestsHelper.getFutureTravaux()).thenReturn(expectedTravaux);

        // Act
        List<Travail> actualTravaux = residentController.consulterFutursTravaux();

        // Assert
        assertEquals(expectedTravaux, actualTravaux);
        verify(httpRequestsHelper).getFutureTravaux();
    }
}
