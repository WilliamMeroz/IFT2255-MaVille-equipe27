package maville.equipe27.controllers;

import maville.equipe27.enums.AuthChoices;
import maville.equipe27.models.Intervenant;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import static java.lang.System.exit;

public class IntervenantController implements IController {

    private Intervenant intervenant;
    TextIO textIO = TextIoFactory.getTextIO();

    public void handleConnectionEvent(Intervenant intervenant) {
        this.intervenant = intervenant;
    }

    @Override
    public void run() {

        System.out.println("ON EST CONNECTÉ EN TANT QU'INTERVENANT. Username: " + intervenant.getEmail());
        intervenantMenu();
    }

    public void intervenantMenu() {

        TextTerminal terminal = textIO.getTextTerminal(); {

            boolean sortir = true;

            while ( sortir ) {
                terminal.printf( " \n 1. Consulter les travaux \n 2. Rechercher des travaux \n 3. Consulter la liste des requêtes de travail " +
                        "\n 4. Soumettre un nouveau projet de travaux \n 5. Mettre à jour les informations d'un chantier \n 6. Déconnexion" );
                int reponse = textIO.newIntInputReader()
                        .read( " \n Enter your choice : " );

                if( reponse > 6 || reponse < 1) {
                    terminal.printf( " \n Veuillez entrer un numéro valide" );
                    continue;
                }
                switch ( reponse ) {

                    case 1:
                        terminal.printf( " \n Voici la liste des travaux en cours : \n " );
                        break;

                    case 2:
                        terminal.printf( " \n Recherche : \n " );
                        break;

                    case 3:
                        terminal.printf( " \n Voici la liste des requêtes de travail : \n " );
                        break;

                    case 4:
                        terminal.printf( " \n Soumettez le nouveau projet ici : \n " );
                        break;

                    case 5:
                        terminal.printf( " \n Sélectionnez le chantier à modifier : \n " );
                        break;

                    case 6:
                        terminal.printf( " \n Vous vous êtes déconnecté avec succès" );
                        sortir = false;
                        continue;

                }
                int revenir = textIO.newIntInputReader()
                        .read( " \n 1. Revenir au menu principal \n Enter your choice : " );
                if ( revenir != 1 ) {
                    terminal.printf( " \n Veuillez entrer un numéro valide \n " );
                }
            }
        }
    }
}
