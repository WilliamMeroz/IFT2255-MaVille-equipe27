package maville.equipe27.controllers;

import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.models.Intervenant;
import maville.equipe27.views.IntervenantView;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.json.simple.JSONArray;
import java.io.FileReader;
import java.io.StringReader;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

class IntervenantControllerTest {

    @Test
    void testHandleConnectionEvent() {
        IntervenantView mockView = new IntervenantView(null);
        IntervenantController controller = new IntervenantController(mockView);
        Intervenant intervenant = new Intervenant("test@mail.com", "password", null, "John", "Doe", null, "123");

        controller.handleConnectionEvent(intervenant);

        assertEquals("test@mail.com", controller.getIntervenantEmail());
    }

    @Test
    void testPromptMainMenuCalled() {
        IntervenantView mockView = Mockito.mock(IntervenantView.class);
        IntervenantController controller = new IntervenantController(mockView);

        // Simuler un appel à run avec le choix 0
        controller.run();

        verify(mockView).promptMainMenu();
    }

    @Test
    void testAfficherRequetesTravail() throws Exception {
        IntervenantView mockView = Mockito.mock(IntervenantView.class);
        String mockJson = "[{\"titre\":\"Requete 1\",\"description\":\"Description 1\"}]";
        StringReader mockReader = new StringReader(mockJson);

        IntervenantController controller = new IntervenantController(mockView, mockReader);

        controller.afficherRequetesTravail();

        JSONArray expectedRequetes = new JSONArray();
        JSONObject requete = new JSONObject();
        requete.put("titre", "Requete 1");
        requete.put("description", "Description 1");
        expectedRequetes.add(requete);

        verify(mockView).afficherRequetes(expectedRequetes);
    }
}
