package maville.equipe27.controllers;

import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.models.Intervenant;
import maville.equipe27.views.IntervenantView;
import org.json.simple.JSONObject;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
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
    void testIntervenantIsSetAfterHandleConnectionEvent() {
        // Création de la vue et du contrôleur
        IntervenantView mockView = new IntervenantView(null);
        IntervenantController controller = new IntervenantController(mockView);

        // Création d'un intervenant simulé
        Intervenant intervenant = new Intervenant(
                "test@mail.com", "password", null, "John", "Doe", null, "123"
        );

        // Appel à la méthode pour connecter l'intervenant
        controller.handleConnectionEvent(intervenant);

        // Vérification que l'intervenant est bien défini dans le contrôleur
        assertNotNull(controller.getIntervenantEmail(), "L'email de l'intervenant ne devrait pas être null.");
    }
}
