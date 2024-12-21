package maville.equipe27.helpers;

import maville.equipe27.models.Intervenant;

/**
 * La classe {@code ConnectedIntervenant} implémente un modèle de conception Singleton qui permet de gérer
 * l'intervenant actuellement connecté à l'application. Elle offre un accès global à l'instance unique de
 * l'intervenant, permettant de stocker et de récupérer ses informations pendant la session.
 *
 */
public class ConnectedIntervenant {

    /**
     * L'instance unique de la classe {@code ConnectedIntervenant} (Singleton).
     */
    private static ConnectedIntervenant instance;

    /**
     * L'intervenant actuellement connecté.
     */
    private Intervenant intervenant;

    /**
     * Constructeur privé pour empêcher la création d'instances multiples.
     */
    private ConnectedIntervenant() { }

    /**
     * Retourne l'instance unique de {@code ConnectedIntervenant}. Si l'instance n'existe pas, elle est créée.
     *
     * @return L'instance unique de {@code ConnectedIntervenant}.
     */
    public static ConnectedIntervenant getInstance() {
        if (instance == null) {
            instance = new ConnectedIntervenant();
        }

        return instance;
    }

    /**
     * Retourne l'intervenant actuellement connecté.
     *
     * @return L'intervenant actuellement connecté.
     */
    public Intervenant getIntervenant() {
        return intervenant;
    }

    /**
     * Définit l'intervenant actuellement connecté.
     *
     * @param intervenant L'intervenant à définir comme actuellement connecté.
     */
    public void setIntervenant(Intervenant intervenant) {
        this.intervenant = intervenant;
    }
}
