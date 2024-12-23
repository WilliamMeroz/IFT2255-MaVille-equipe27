package maville.equipe27.helpers;

import maville.equipe27.enums.RoleChoices;
import maville.equipe27.models.Resident;
import maville.equipe27.helpers.ConnectedResident;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedResidentTest {

    private ConnectedResident connectedResident;
    private Resident resident;

    @BeforeEach
    void setUp() {
        // Initialize a new Resident object for testing
        resident = new Resident("test@example.com", "password", RoleChoices.RÉSIDENT, "John", "Doe",
                LocalDate.of(1990, 1, 1), "1234567890", "123 Main St, City, District");

        // Get the Singleton instance of ConnectedResident
        connectedResident = ConnectedResident.getInstance();
    }

    @Test
    void testSingletonInstance() {
        // Fetch the instance multiple times and verify that they are the same
        ConnectedResident firstInstance = ConnectedResident.getInstance();
        ConnectedResident secondInstance = ConnectedResident.getInstance();

        assertSame(firstInstance, secondInstance, "ConnectedResident should be a Singleton.");
    }

    @Test
    void testSetResident() {
        // Set a resident in the Singleton instance
        connectedResident.setResident(resident);

        // Verify that the resident is correctly set
        assertNotNull(connectedResident.getResident(), "Resident should not be null.");
        assertEquals(resident, connectedResident.getResident(), "The set resident should be the same as the retrieved resident.");
    }

    @Test
    void testSetAndGetResident() {
        // Set the resident and then get it
        connectedResident.setResident(resident);

        Resident fetchedResident = connectedResident.getResident();

        assertNotNull(fetchedResident, "Resident should not be null.");
        assertEquals(resident.getEmail(), fetchedResident.getEmail(), "The fetched resident's email should match the set resident.");
    }
}
