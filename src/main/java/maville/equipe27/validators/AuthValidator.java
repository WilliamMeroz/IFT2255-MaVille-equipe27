package maville.equipe27.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 * La classe {@link AuthValidator} contient des méthodes utilitaires pour valider différents types de données
 * telles que les numéros de téléphone, les codes postaux et les adresses email. Elle utilise des expressions régulières
 * pour effectuer ces validations.
 */

public class AuthValidator {
    private static final String PHONE_REGEX = "^(\\d{3}-\\d{3}-\\d{4})$";
    private static final String POSTAL_CODE_REGEX = "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$";
    /**
     * Valide un numéro de téléphone canadien.
     *
     * @param phone Le numéro de téléphone à valider (au format xxx-xxx-xxxx).
     * @return true si le numéro de téléphone est valide, sinon false.
     */
    public static boolean validatePhone(String phone) {
        return validateGeneric(PHONE_REGEX, phone);
    }
    /**
     * Valide un code postal canadien.
     *
     * @param code Le code postal à valider (au format A1B 2C3 ou A1B-2C3).
     * @return true si le code postal est valide, sinon false.
     */
    public static boolean validatePostalCode(String code) {
        return validateGeneric(POSTAL_CODE_REGEX, code);
    }
    /**
     * Valide une adresse email.
     *
     * @param email L'adresse email à valider (au format nom@example.com).
     * @return true si l'adresse email est valide, sinon false.
     */
    public static boolean validateEmail(String email) { return validateGeneric(EMAIL_REGEX, email); }

    /**
     * Méthode générique utilisée pour valider une valeur avec une expression régulière donnée.
     *
     * @param regex L'expression régulière à utiliser pour la validation.
     * @param value La valeur à valider.
     * @return true si la valeur correspond à l'expression régulière, sinon false.
     */
    private static boolean validateGeneric(String regex, String value) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
