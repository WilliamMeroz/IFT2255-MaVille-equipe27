package maville.equipe27.controllers;

import maville.equipe27.models.Resident;
import maville.equipe27.controllers.Menu;
import java.util.Scanner;

public class ResidentController implements IController {

    private Resident resident;

    public void handleConnectionEvent(Resident resident) {
        this.resident = resident;
    }

    @Override
    public void run() {
        System.out.println("ON EST CONNECTÉ EN TANT QUE RÉSIDENT. Username: " + this.resident.getEmail());

        boolean mainMenu = true;
        while(mainMenu == true) {
            int reponse = -1;
            reponse = Menu.residentMain();

            if(reponse == 1) {
                reponse = Menu.resident_1();
                if(reponse == 1) {
                    System.out.println("Affiche une liste de travaux en cours filtrer par rue");
                }

                if(reponse == 2) {
                    System.out.println("Affiche une liste de travaux en cours filtrer par type de travaux");
                }

                if(reponse == 3) {
                    System.out.println("Affiche une liste de travaux en cours filtrer par quartier");
                }

                if(reponse == 4) {
                    reponse = -1;
                }
            }

            else if(reponse == 2) {
                reponse = Menu.resident_2();

                if(reponse == 1) {
                    System.out.println("Affiche une liste de travaux à venir filter par rue");
                }

                if(reponse == 2) {
                    System.out.println("Affiche une liste de travaux à venir filter par type de travaux");
                }

                if(reponse == 3) {
                    System.out.println("Affiche une liste de travaux à venir filter par quartier");
                }

                if(reponse == 4) {
                    reponse = -1;
                }
            }

            else if(reponse == 3) {
                reponse = Menu.resident_3();
                
                if(reponse == 1) {
                    System.out.println("Entrez le titre que vous voulez");

                    Scanner scanner = new Scanner(System.in);
                    String recherche = new String();
                    recherche = scanner.nextLine();

                    System.out.println("Affiche une liste de travaux filtrer par ce titre");
                }

                if(reponse == 2) {
                    System.out.println("Afficher une liste de type de travaux");
                    System.out.println("Entrez le type de travaux que vous voulez");

                    Scanner scanner = new Scanner(System.in);
                    String recherche = new String();
                    recherche = scanner.nextLine();

                    System.out.println("Affiche une liste de travaux filtrer par ce type de travaux");
                }

                if(reponse == 3) {
                    System.out.println("Entrez le quartier que vous voulez");

                    Scanner scanner = new Scanner(System.in);
                    String probleme = new String();
                    probleme = scanner.nextLine();

                    System.out.println("Affiche une liste de travaux filtrer par ce quartier");
                }

                if(reponse == 4) {
                    reponse = -1;
                }
            }

            else if(reponse == 4) {
                reponse = Menu.resident_4();

                if(reponse == 1) {
                    System.out.println("Afficher les notification de l'utilisateur");
                }

                if(reponse == 2) {
                    System.out.println("Entrez la rue que vous désirez vous abonner");

                    Scanner scanner = new Scanner(System.in);
                    String rueAbonne = new String();
                    rueAbonne = scanner.nextLine();

                    System.out.println("Vous êtes abonné au rue de votre choix");
                }

                if(reponse == 3) {
                    System.out.println("Entrez le quartier que vous désirez vous abonner");

                    Scanner scanner = new Scanner(System.in);
                    String quartierAbonne = new String();
                    quartierAbonne = scanner.nextLine();

                    System.out.println("Vous êtes abonné au quartier de votre choix");
                }

                if(reponse == 4) {
                    System.out.println("Entrez le nom de l'abonnement que vous désirez vous désabonner");
                    System.out.println("Afficher les abonnements de l'utilisateur");

                    Scanner scanner = new Scanner(System.in);
                    String abonnement = new String();
                    abonnement = scanner.nextLine();


                    System.out.println("vous êtes désabonner de cet abonnement");
                }
                
                if(reponse == 5) {
                    reponse = -1;
                }
            }

            else if(reponse == 5) {
                reponse = Menu.resident_5();

                if(reponse == 1) {
                    System.out.println("Afficher la liste de plage horaire disponible");
                    System.out.println("Entrez une plage horaire préféré");
                    
                    Scanner scanner = new Scanner(System.in);
                    String plageHoraire = new String();
                    plageHoraire = scanner.nextLine();

                    System.out.println("votre plage horaire est enregistrée");
                }

                if(reponse == 2) {
                    System.out.println("Afficher la liste de plage horaire avec les votes des voisins");
                }

                if(reponse == 3) {
                    System.out.println("Afficher une liste de travaux finit dans le quartier de l'utilisateur");
                    System.out.println("choisisez quel travail vous voulez commenter");

                    Scanner scanner = new Scanner(System.in);
                    String forumTravail = new String();
                    forumTravail = scanner.nextLine();

                    System.out.println("Entrez votre commentaire ou entrer retour pour retourner aux menu principale");

                    if(forumTravail == "retour") {
                        reponse = -1;
                    } else {
                        System.out.println("Votre commentaire est ajouter aux forum");
                    }
                }

                if(reponse == 4) {
                    reponse = -1;
                }
            }

            else if(reponse == 6) {
                System.out.println("Entrer le type de problème");

                Scanner scanner = new Scanner(System.in);
                String typeProbleme = new String();
                typeProbleme = scanner.nextLine();

                System.out.println("Décrivez le problème");

                String description = new String();
                description = scanner.nextLine();

                System.out.println("Votre problème à été envoyer à la ville");
            }

            else if(reponse == 7) {
                reponse = Menu.resident_7();

                if(reponse == 1) {
                    System.out.println("Entrer le titre du travail");

                    Scanner scanner = new Scanner(System.in);
                    String titreTravail = new String();
                    titreTravail = scanner.nextLine();

                    System.out.println("Donnez une description détailler du travail");

                    String description = new String();
                    description = scanner.nextLine();

                    System.out.println("Afficher une liste de type de travaux");
                    System.out.println("Choisissez un type de travaux");

                    String typeTravaux = new String();
                    typeTravaux = scanner.nextLine();

                    System.out.println("Votre requête à été envoyer à la ville");
                }

                if(reponse == 2) {
                    System.out.println("Afficher le suivi de la requête de l'utilisateur");
                }

                if(reponse == 3) {
                    reponse = -1;
                }
            }

            else if(reponse == 8) {
                mainMenu = false;
            }
        }
    }
}
