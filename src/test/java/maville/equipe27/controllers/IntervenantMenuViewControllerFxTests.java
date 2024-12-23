import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import javafx.collections.FXCollections;
import javafx.scene.control.*;
import maville.equipe27.enums.ProjetStatus;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Projet;
import maville.equipe27.models.RequeteTravail;

import java.time.LocalDate;
import java.util.List;

public class IntervenantMenuViewControllerFxTests {

    private IntervenantMenuViewControllerFx controller;

    @BeforeEach
    public void setUp() {
        controller = new IntervenantMenuViewControllerFx();
        controller.initialize(); // Simulates the initialization done by the FXML loader
    }

    @Test
    public void testInitializeComponents() {
        // Act
        controller.initialize();

        // Assert
        assertNotNull(controller.nouveauProjetType.getItems(), "Type comboBox should be initialized with values.");
        assertEquals(TravauxTypes.ROUTIER, controller.nouveauProjetType.getValue(), "Default value of the type comboBox should be ROUTIER.");
        assertNotNull(controller.tableViewRequetes.getItems(), "TableView should be populated with requetes.");
    }

    @Test
    public void testHandleNewProjetWithValidData() {
        // Arrange
        controller.nouveauProjetTitre.setText("Nouveau Projet");
        controller.nouveauProjetDesc.setText("Description du projet");
        controller.nouveauProjetRues.setText("Rue1,Rue2");
        controller.nouveauProjetDebut.setValue(LocalDate.now());
        controller.nouveauProjetFin.setValue(LocalDate.now().plusDays(5));
        controller.nouveauProjetDe.getValueFactory().setValue(8);
        controller.nouveauProjetA.getValueFactory().setValue(16);
        controller.nouveauProjetType.setValue(TravauxTypes.ROUTIER);
        controller.nouveauProjetQuartier.setValue("Plateau-Mont-Royal");

        // Act & Assert
        assertDoesNotThrow(() -> controller.handleNewProjet(), "handleNewProjet should not throw an exception with valid data.");
    }

    @Test
    public void testHandleNewProjetWithInvalidDates() {
        // Arrange
        controller.nouveauProjetTitre.setText("Nouveau Projet");
        controller.nouveauProjetDesc.setText("Description du projet");
        controller.nouveauProjetRues.setText("Rue1,Rue2");
        controller.nouveauProjetDebut.setValue(LocalDate.now().plusDays(5));
        controller.nouveauProjetFin.setValue(LocalDate.now());
        controller.nouveauProjetDe.getValueFactory().setValue(16);
        controller.nouveauProjetA.getValueFactory().setValue(8);
        controller.nouveauProjetType.setValue(TravauxTypes.ROUTIER);
        controller.nouveauProjetQuartier.setValue("Plateau-Mont-Royal");

        // Act
        Exception exception = assertThrows(Exception.class, () -> controller.handleNewProjet(), "handleNewProjet should throw an exception with invalid dates.");

        // Assert
        assertTrue(exception.getMessage().contains("La date de début ne peut pas être après la date de fin"), "Expected error message about invalid dates.");
    }
}
