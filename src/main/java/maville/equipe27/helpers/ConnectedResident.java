package maville.equipe27.helpers;

import maville.equipe27.models.Resident;

/**
 * La classe {@code ConnectedResident} implémente un modèle de conception Singleton qui permet de gérer
 * le résident actuellement connecté à l'application. Elle offre un accès global à l'instance unique du
 * résident, permettant de stocker et de récupérer ses informations pendant la session.
 *
 */
public class ConnectedResident {

    /**
     * L'instance unique de la classe {@code ConnectedResident} (Singleton).
     */
    private static ConnectedResident instance;

    /**
     * Le résident actuellement connecté.
     */
    private Resident resident;

    /**
     * Constructeur privé pour empêcher la création d'instances multiples.
     */
    private ConnectedResident() { }

    /**
     * Retourne l'instance unique de {@code ConnectedResident}. Si l'instance n'existe pas, elle est créée.
     *
     * @return L'instance unique de {@code ConnectedResident}.
     */
    public static ConnectedResident getInstance() {
        if (instance == null) {
            instance = new ConnectedResident();
        }

        return instance;
    }

    /**
     * Retourne le résident actuellement connecté.
     *
     * @return Le résident actuellement connecté.
     */
    public Resident getResident() {
        return resident;
    }

    /**
     * Définit le résident actuellement connecté.
     *
     * @param resident Le résident à définir comme actuellement connecté.
     */
    public void setResident(Resident resident) {
        this.resident = resident;
    }
}
