package maville.equipe27.views;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import maville.equipe27.controllers.AuthController;
import maville.equipe27.enums.RoleChoices;
import maville.equipe27.helpers.ConnectedResident;
import maville.equipe27.models.Resident;
import maville.equipe27.models.User;
import maville.equipe27.validators.AuthValidator;

import java.io.IOException;
import java.time.LocalDate;
import java.util.StringJoiner;

public class RegisterResidentViewControllerFx {

    private AuthController authController;

    @FXML
    private Button registerButtonResident;

    @FXML
    private TextField registerCivicResident;

    @FXML
    private DatePicker registerDOBResident;

    @FXML
    private TextField registerEmailResident;

    @FXML
    private TextField registerFirstNameResident;

    @FXML
    private TextField registerLastNameResident;

    @FXML
    private PasswordField registerPasswordResident;

    @FXML
    private TextField registerPhoneResident;

    @FXML
    private TextField registerPostalCodeResident;

    @FXML
    private TextField registerStreetResident;

    @FXML
    private TextField registerUnitResident;

    @FXML
    public void initialize() {
        authController = new AuthController();

        registerButtonResident.setOnAction(event -> handleRegister());
    }

    private void handleRegister() {
        String email = registerEmailResident.getText();
        String password = registerPasswordResident.getText();
        String firstName = registerFirstNameResident.getText();
        String lastName = registerLastNameResident.getText();
        LocalDate dob = registerDOBResident.getValue();
        String phone = registerPhoneResident.getText();
        String civicStr = registerCivicResident.getText();
        String street = registerStreetResident.getText();
        String unitStr = registerUnitResident.getText();
        String postalCode = registerPostalCodeResident.getText();


        if (!AuthValidator.validateEmail(email)) {
            showAlert("Courriel invalide", "Donner un courriel valide.");
            registerEmailResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (password.length() < 6) {
            showAlert("Mot de passe invalide", "Donner un mot de passe valide");
            registerPasswordResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (firstName.isEmpty()) {
            showAlert("Prénom invalide", "Prénom est vide");
            registerFirstNameResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (lastName.isEmpty()) {
            showAlert("Nom de famille invalide", "Nom de famille vide.");
            registerLastNameResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (dob.isAfter(LocalDate.now())) {
            showAlert("Date de naissance invalide", "Date de naissance invalide (dans le futur)");
            registerDOBResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (!AuthValidator.validatePhone(phone)) {
            showAlert("Numéro de téléphone invalide", "Numéro de téléphone doit respecter le format (###-###-####)");
            registerPhoneResident.setStyle("-fx-border-color: red;");
            return;
        }

        int civic;
        try {
            civic = Integer.parseInt(civicStr);
            if (civic < 0) throw new IllegalArgumentException();
        } catch (IllegalArgumentException e) {
            showAlert("Numéro civic invalide", "Numéro civic doit être un numéro positif");
            registerCivicResident.setStyle("-fx-border-color: red;");
            return;
        }

        if (street.isEmpty()) {
            showAlert("Rue invalide", "La rue ne doit pas être vide");
            registerStreetResident.setStyle("-fx-border-color: red;");
            return;
        }

        int unit = 0;
        if (!unitStr.isEmpty()) {
            try {
                unit = Integer.parseInt(unitStr);
                if (unit < 0) throw new IllegalArgumentException();
            } catch (IllegalArgumentException e) {
                showAlert("Unité invalide.", "Unité (facultatif) doit être un nombre positif");
                registerUnitResident.setStyle("-fx-border-color: red;");
                return;
            }
        }

        if (!AuthValidator.validatePostalCode(postalCode)) {
            showAlert("Code postal invalid", "Le code postal doit respecter le format A1B 2C3");
            registerPostalCodeResident.setStyle("-fx-border-color: red;");
            return;
        }

        StringJoiner joiner = new StringJoiner(",");
        joiner.add(String.valueOf(civic)).add(street).add(String.valueOf(unit)).add(postalCode);

        Resident user = new Resident(email, password, RoleChoices.RÉSIDENT, firstName, lastName, dob, phone, joiner.toString());
        performRegister(user);
    }

    private void performRegister(Resident user) {
        User returnedUser = authController.register(user);
        if (returnedUser != null) {
            ConnectedResident.getInstance().setResident((Resident) returnedUser);
            switchScene("/residentMenu.fxml", "Menu résident");
        } else showAlert("Erreur lors de l'inscription", "Un compte evec les mêmes infos existe probablement déjà.");
    }

    private void switchScene(String resssource, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(resssource));
            Parent root = loader.load();

            Stage stage = (Stage) registerButtonResident.getScene().getWindow();
            stage.setTitle(title);
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
