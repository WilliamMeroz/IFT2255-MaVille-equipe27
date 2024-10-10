package maville.equipe27.controllers;

import maville.equipe27.models.Resident;
import maville.equipe27.views.ResidentView;

import java.util.Scanner;

public class ResidentController implements IController {

    private Resident resident;
    private ResidentView residentView;

    public ResidentController(ResidentView residentView) {
        this.residentView = residentView;
    }

    public void handleConnectionEvent(Resident resident) {
        this.resident = resident;
    }

    @Override
    public void run() {
        System.out.println("Résident: " + this.resident.getEmail());

        int choice = 0;
        while (true) {
            switch (choice) {
                case 0:
                    choice = residentView.promptMainMenu();
                    break;
                case 1:
                    choice = residentView.promptTravauxEnCours();
                    break;
                case 2:
                    choice = residentView.promptTravauxAVenir();
                    break;
                case 3:
                    choice = residentView.promptRechercheTravaux();
                    break;
                case 4:
                    choice = residentView.promptNotifications();
                    break;
                case 5:
                    choice = residentView.promptPlanificationParticipative();
                    break;
                case 6:
                    choice = residentView.promptSignalerProbleme();
                    break;
                case 7:
                    choice = residentView.promptRequeteTravail();
                    break;
                case 8:
                    System.exit(0);
                    break;
            }
        }
    }
}
