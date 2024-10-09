package maville.equipe27.controllers;

import maville.equipe27.enums.AuthChoices;
import maville.equipe27.models.Intervenant;

import org.beryx.textio.TextIO;
import org.beryx.textio.TextIoFactory;
import org.beryx.textio.TextTerminal;

import static java.lang.System.exit;
import java.util.concurrent.TimeUnit;

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
                        terminal.printf( " \n Voici la liste des travaux en cours : \n Aucun travail en cours \n " );
                        while ( reponse == 1 ) {
                            int revenir = textIO.newIntInputReader()
                                    .read( " \n 1. Filtrer par rue \n 2. Filtrer par type \n" +
                                            " 3. Filtrer par quartier \n 4. Revenir au menu principal \n " +
                                            "Enter your choice : " );
                            if ( revenir != 4 ) {
                                terminal.printf( " \n L'option n'est pas encore disponible \n " );
                            } else {
                                break;
                            }
                        }
                        break;

                    case 2:
                        terminal.printf( " \n Démarrer une recherche : \n " );
                        while ( reponse == 2 ) {
                            int revenir = textIO.newIntInputReader()
                                    .read( " \n 1. Recherche par titre \n 2. Recherche par type \n" +
                                            " 3. Recherche par quartier \n 4. Revenir au menu principal \n " +
                                            "Enter your choice : " );
                            if ( revenir != 4 ) {
                                terminal.printf( " \n L'option n'est pas encore disponible \n " );
                            } else {
                                break;
                            }
                        }
                        break;

                    case 3:
                        terminal.printf( " \n Voici la liste des requêtes de travail : \n Aucune requête en cours \n ");
                        while ( reponse == 3 ) {
                            int revenir = textIO.newIntInputReader()
                                    .read( " \n 1. Soumettre sa candidature \n 2. Filtrer par type \n" +
                                            " 3. Filtrer par quartier \n 4. Filtrer par date de début \n " +
                                            "5. Revenir au menu principal \n Enter your choice : " );
                            if ( revenir != 5 ) {
                                terminal.printf( " \n L'option n'est pas encore disponible \n " );
                            } else {
                                break;
                            }
                        }
                        break;

                    case 4:
                        terminal.printf( " \n Soumettez le nouveau projet ici : \n " );
                        while ( reponse == 4 ) {
                            int revenir = textIO.newIntInputReader()
                                    .read( " \n 1. Consulter les préférences des résidents \n" +
                                            " 2. Revenir au menu principal \n Enter your choice : " );
                            if ( revenir != 2 ) {
                                terminal.printf( " \n L'option n'est pas encore disponible \n " );
                            } else {
                                break;
                            }
                        }
                        break;

                    case 5:
                        terminal.printf( " \n Sélectionnez le chantier à modifier : \n Aucun projet en cours \n" );
                        while ( reponse == 5 ) {
                            int revenir = textIO.newIntInputReader()
                                    .read( " \n 1. Mettre à jour la description du projet \n" +
                                            " 2. Mettre à jour la date de fin prévue \n 3. Changer le statut du projet \n" +
                                            " 4. Revenir au menu principal \n Enter your choice : " );
                            if ( revenir != 4 ) {
                                terminal.printf( " \n L'option n'est pas encore disponible \n " );
                            } else {
                                break;
                            }
                        }
                        break;

                    case 6:
                        terminal.printf( " \n Vous vous êtes déconnecté avec succès" );
                        sortir = false;
                        try {
                            TimeUnit.SECONDS.sleep(1);
                            System.exit(0);
                        } catch(InterruptedException e) {
                            System.exit(0);
                        }
                }
            }
        }
    }
}
