package maville.equipe27.controllers;

import com.google.gson.annotations.SerializedName;
import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.helpers.ConnectedIntervenant;
import maville.equipe27.models.Intervenant;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static maville.equipe27.enums.RoleChoices.INTERVENANT;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Classe de tests pour {@code ConnectedIntervenant}.
 */
public class ConnectedIntervenantTests {

    @BeforeEach
    void resetSingleton() {
        // Réinitialisation manuelle de l'instance pour chaque test.
        ConnectedIntervenant instance = ConnectedIntervenant.getInstance();
        instance.setIntervenant(null);
    }

    @Test
    void testSingletonInstance() {
        // Vérifie que la méthode getInstance retourne toujours la même instance.
        ConnectedIntervenant instance1 = ConnectedIntervenant.getInstance();
        ConnectedIntervenant instance2 = ConnectedIntervenant.getInstance();

        assertNotNull(instance1, "L'instance de ConnectedIntervenant ne doit pas être null.");
        assertSame(instance1, instance2, "getInstance doit retourner la même instance unique.");
    }

    @Test
    void testSetAndGetIntervenant() {
        // Vérifie que l'intervenant peut être défini et récupéré correctement.
        ConnectedIntervenant connectedIntervenant = ConnectedIntervenant.getInstance();
        Intervenant intervenant = new Intervenant("John Doe", "johndoe@example.com", INTERVENANT, "John"
        , "Doe", CompanyChoices.PUBLIC, "12345");

        connectedIntervenant.setIntervenant(intervenant);
        Intervenant retrievedIntervenant = connectedIntervenant.getIntervenant();

        assertNotNull(retrievedIntervenant, "L'intervenant récupéré ne doit pas être null.");
        assertEquals(intervenant, retrievedIntervenant, "L'intervenant récupéré doit correspondre à l'intervenant défini.");
    }

    @Test
    void testIntervenantDefaultsToNull() {
        // Vérifie que l'intervenant est null par défaut.
        ConnectedIntervenant connectedIntervenant = ConnectedIntervenant.getInstance();

        assertNull(connectedIntervenant.getIntervenant(), "L'intervenant par défaut doit être null.");
    }
}
