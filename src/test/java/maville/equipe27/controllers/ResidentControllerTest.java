package maville.equipe27.controllers;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.RequeteTravail;
import maville.equipe27.helpers.RequeteTravailStore;

import static org.junit.jupiter.api.Assertions.*;

class ResidentControllerTest {
    MockResident mockResident = new MockResident();
    MockRequeteTravailStore requeteTest = new MockRequeteTravailStore();
    RequeteTravail mockRequete = new RequeteTravail(null, null, null, null);

    @Test
    @DisplayName("Test pour consulterEntrave")
    void ConsulterEntravesTravaux() {
        assertEquals("Liste d'entraves de: avenue De Lorimier", mockResident.mockConsulterEntravesRue("avenue De Lorimier"));
        assertEquals("Liste d'entraves avec id: 66e8961b1e85b50019ec815d", mockResident.mockConsulterEntravesTravail("66e8961b1e85b50019ec815d"));
    }

    @Test
    @DisplayName("Test pour RequeteTravailStore")
    void RequeteStoreTest() {
        assertEquals("Liste de Requêtes", requeteTest.mockGetRequetes());
        assertEquals(true, requeteTest.saveRequetes(mockRequete));
    }
}