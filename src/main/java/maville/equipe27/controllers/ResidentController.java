package maville.equipe27.controllers;

import maville.equipe27.models.Resident;

public class ResidentController implements IController {

    private Resident resident;

    public void handleConnectionEvent(Resident resident) {
        this.resident = resident;
    }

    @Override
    public void run() {
        System.out.println("ON EST CONNECTÉ EN TANT QUE RÉSIDENT. Username: " + this.resident.getUsername());

    }
}
