package maville.equipe27.validators;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class AuthValidator {
    private static final String PHONE_REGEX = "^(\\d{3}-\\d{3}-\\d{4})$";
    private static final String POSTAL_CODE_REGEX = "^[ABCEGHJ-NPRSTVXY]\\d[ABCEGHJ-NPRSTV-Z][ -]?\\d[ABCEGHJ-NPRSTV-Z]\\d$";
    private static final String EMAIL_REGEX = "^[A-Za-z0-9._%+\\-]+@[A-Za-z0-9.\\-]+\\.[A-Za-z]{2,}$";

    public static boolean validatePhone(String phone) {
        return validateGeneric(PHONE_REGEX, phone);
    }

    public static boolean validatePostalCode(String code) {
        return validateGeneric(POSTAL_CODE_REGEX, code);
    }

    public static boolean validateEmail(String email) { return validateGeneric(EMAIL_REGEX, email); }

    private static boolean validateGeneric(String regex, String value) {
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(value);
        return matcher.find();
    }
}
