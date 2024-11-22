
package maville.equipe27.controllers;

import maville.equipe27.helpers.HTTPRequestsHelper;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.Resident;
import maville.equipe27.views.ResidentView;
import maville.equipe27.controllers.ResidentController;

import java.util.List;

public class MockResident extends ResidentController {
    public MockResident() {
        super(null);
    }


    public String mockConsulterEntravesRue(String rue) {
        return "Liste d'entraves de: " + rue;
    }

    public String mockConsulterEntravesTravail(String id) {
        return "Liste d'entraves avec id: " + id;
    }

}