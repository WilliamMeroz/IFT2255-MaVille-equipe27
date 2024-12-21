package maville.equipe27.enums;

import com.google.gson.annotations.SerializedName;

/**
 * Enum représentant les deux types de compte que le système utilise, représentés par 1 pour résident
 * et 2 pour intervenant lorsque ceux-ci sont sauvegardés dans des fichiers JSON.
 */

public enum RoleChoices {
    @SerializedName("1")
    RÉSIDENT,

    @SerializedName("2")
    INTERVENANT
}
