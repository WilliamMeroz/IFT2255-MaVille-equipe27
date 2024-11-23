package maville.equipe27.controllers;

import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.models.Intervenant;
import maville.equipe27.views.IntervenantView;
import org.beryx.textio.TextIO;
import org.beryx.textio.TextTerminal;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.contains;
import static org.mockito.Mockito.*;

class IntervenantControllerTests {

    @Mock
    private IntervenantView intervenantView;

    @Mock
    private Intervenant intervenant;

    @Mock
    private TextIO textIO;

    private IntervenantController intervenantController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        intervenantController = new IntervenantController(intervenantView);
        intervenantController.handleConnectionEvent(intervenant);

        // Mock TextIO if used within IntervenantView
        when(intervenantView.textIO).thenReturn(textIO);
        when(textIO.getTextTerminal()).thenReturn(mock(TextTerminal.class));
    }

    @Test
    void testRun_MainMenuPrompt() {
        // Arrange
        when(intervenant.getEmail()).thenReturn("test@example.com");
        when(intervenantView.promptMainMenu()).thenReturn(6); // Exit immediately

        // Act
        intervenantController.run();

        // Assert
        verify(intervenantView).promptMainMenu();
    }

    @Test
    void testAfficherRequetesTravail_Success() throws Exception {
        // Arrange
        JSONArray mockRequetes = new JSONArray();
        JSONObject mockRequete = new JSONObject();
        mockRequete.put("id", "123");
        mockRequete.put("description", "Mock request");
        mockRequetes.add(mockRequete);

        // Spy on the controller to mock the parseRequetesFromFile method
        IntervenantController controllerSpy = spy(intervenantController);
        doReturn(mockRequetes).when(controllerSpy).afficherRequetesTravail();

        // Act
        controllerSpy.afficherRequetesTravail();

        // Assert
        verify(intervenantView).afficherRequetes(mockRequetes);
    }

    @Test
    void testAfficherRequetesTravail_FileNotFound() throws Exception {
        // Arrange
        IntervenantController controllerSpy = spy(intervenantController);
        doThrow(new RuntimeException("File not found")).when(controllerSpy).afficherRequetesTravail();

        TextTerminal textTerminal = mock(TextTerminal.class);
        when(intervenantView.textIO).thenReturn(textIO);
        when(textIO.getTextTerminal()).thenReturn(textTerminal);

        // Act
        controllerSpy.afficherRequetesTravail();

        // Assert
        verify(textTerminal).println(contains("Erreur lors du chargement des requêtes"));
    }

    @Test
    void testGetIntervenantEmail() {
        // Arrange
        when(intervenant.getEmail()).thenReturn("test@example.com");

        // Act
        String email = intervenantController.getIntervenantEmail();

        // Assert
        assertEquals("test@example.com", email);
        verify(intervenant).getEmail();
    }
}
