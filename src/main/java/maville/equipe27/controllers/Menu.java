import java.util.Scanner;

public class Menu {
    public static int residentMain() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. Consulter les travaux en cours");
        System.out.println("2. Consulter les travaux à venir");
        System.out.println("3. Recherche des travaux");
        System.out.println("4. Notifications");
        System.out.println("5. Planification participative");
        System.out.println("6. Signaler un problème à la ville");
        System.out.println("7. Requête de travail");
        System.out.println("8. Déconnecter");

        while(reponse > 8 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 8 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
            }
        }

        return reponse;

    }

    public static int resident_1() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. filtrer par rue");
        System.out.println("2. filtrer par type de travaux");
        System.out.println("3. Filtrer par quartier");
        System.out.println("4. Retour");

        while(reponse > 4 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 4 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }

        return reponse;
    }

    public static int resident_2() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. filtrer par rue");
        System.out.println("2. filtrer par type de travaux");
        System.out.println("3. Filtrer par quartier");
        System.out.println("4. Retour");

        while(reponse > 4 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 4 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }
        return reponse;
    }

    public static int resident_3() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. Recherche par titre");
        System.out.println("2. Recherche par type de travaux");
        System.out.println("3. Rechercher par quartier");
        System.out.println("4. retour");


        while(reponse > 4 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 4 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }

        return reponse;
    }

    public static int resident_4() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. Mes notifications");
        System.out.println("2. S'abonner aux notification d'une rue");
        System.out.println("3. S'sabonner aux notification d'un quartier");
        System.out.println("4. Désabonnement des notifications");
        System.out.println("5. Retour");


        while(reponse > 5 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 5 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }

        return reponse;
    }

    public static int resident_5() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. Plage(s) horaire(s) préfèré pour les travaux dans votre quartier");
        System.out.println("2. consulter les plages horaires préfèré des voisins");
        System.out.println("3. Forum des travaux.");
        System.out.println("4. Retour");


        while(reponse > 4 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 4 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }

        return reponse;
    }

    public static int resident_7() {
        Scanner scanner = new Scanner(System.in);
        int reponse = -1;

        System.out.println("Entrez le numero désiré");
        System.out.println("1. Soumettre une requête de travaille");
        System.out.println("2. Consulter votre requête(s)");
        System.out.println("3. Retour");


        while(reponse > 3 || reponse < 1) {
        try {
            String userInput = scanner.nextLine();
            reponse = Integer.parseInt(userInput);
            if(reponse > 3 || reponse < 1) {
                System.out.println("Pas une option");
            }

        } catch(NumberFormatException e) {
            System.out.println("Pas un numero");
        }

        }

        return reponse;
    }
}
