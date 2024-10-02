package maville.equipe27.controllers;

import maville.equipe27.models.Intervenant;

public class IntervenantController implements IController {

    private Intervenant intervenant;

    public void handleConnectionEvent(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    @Override
    public void run() {
        System.out.println("ON EST CONNECTÉ EN TANT QU'INTERVENANT. Username: " + intervenant.getEmail());
    }
}
