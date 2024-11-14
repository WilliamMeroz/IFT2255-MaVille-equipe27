package maville.equipe27.controllers;

import java.util.ArrayList;
import java.util.List;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.models.Entrave;

import static org.junit.jupiter.api.Assertions.*;

class ResidentControllerTest {
    HTTPRequestsHelper httpRequestsHelper = new HTTPRequestsHelper();
    ResidentController testResident = new ResidentController(null);


    @Test
    @DisplayName("Test pour consulterEntraveRue")
    void consulterEntravesTravaux() {
        List<Entrave> entraveList = httpRequestsHelper.getEntravesByStreet("avenue De Lorimier");
        assertEquals(entraveList, testResident.consulterEntravesRue("avenue De Lorimier"));
        entraveList = httpRequestsHelper.getEntravesByStreet("rue Garnier");
        assertEquals(entraveList, testResident.consulterEntravesRue("rue Garnier"));
        entraveList = httpRequestsHelper.getEntravesByIdRequest("671f7f71ba710c00198d625d");
        assertEquals(entraveList, testResident.consulterEntravesTravail("671f7f71ba710c00198d625d"));
        entraveList.clear();
        assertEquals(entraveList, testResident.consulterEntravesTravail("123"));
    }
}