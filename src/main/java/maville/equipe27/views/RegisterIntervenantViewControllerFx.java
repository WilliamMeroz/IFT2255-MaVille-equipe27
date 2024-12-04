package maville.equipe27.views;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import maville.equipe27.enums.CompanyChoices;
import maville.equipe27.validators.AuthValidator;

import javax.xml.validation.Validator;

public class RegisterIntervenantViewControllerFx {

    @FXML
    private Button registerButtonIntervenant;

    @FXML
    private TextField registerCompanyNameIntervenant;

    @FXML
    private ComboBox<?> registerCompanyTypeIntervenant;

    @FXML
    private TextField registerEmailIntervenant;

    @FXML
    private TextField registerFirstNameIntervenant;

    @FXML
    private TextField registerIdentifierIntervenant;

    @FXML
    private TextField registerLastNameIntervenant;

    @FXML
    private TextField registerPasswordIntervenant;

    @FXML
    public void initialize() {
        registerButtonIntervenant.setOnAction(event -> handleRegister());
    }

    private void handleRegister() {
        String email = registerEmailIntervenant.getText();
        String password = registerPasswordIntervenant.getText();
        String firstName = registerFirstNameIntervenant.getText();
        String lastName = registerLastNameIntervenant.getText();
        String companyName = registerCompanyNameIntervenant.getText();
        String identifier = registerIdentifierIntervenant.getText();
        String companyTypeStr = (String) registerCompanyTypeIntervenant.getSelectionModel().getSelectedItem();

        CompanyChoices companyType;
        switch (companyTypeStr) {
            case "Public":
                companyType = CompanyChoices.PUBLIC;
                break;
            case "Privé":
                companyType = CompanyChoices.PRIVÉ;
                break;
            case "Particulier":
                companyType = CompanyChoices.PARTICULIER;
                break;
        }

        if (!AuthValidator.validateEmail(email)) {
            showAlert("Courriel invalide", "Le courriel est vide ou n'a pas le bon format");
            registerEmailIntervenant.setStyle("-fx-border-color: red;");
            return;
        }

        if (password.length() < 6) {
            showAlert("Mot de passe invalide", "Mot de passe trop court");
            registerPasswordIntervenant.setStyle("-fx-border-color: red;");
        }

        if (firstName.isEmpty()) {
            showAlert("Prénom invalide", "Le prénom ne peut pas être vide");
            registerFirstNameIntervenant.setStyle("-fx-border-color: red;");
        }

        if (lastName.isEmpty()) {
            showAlert("Nom de famille invalide", "Le nom de famille ne peut pas être vide");
            registerLastNameIntervenant.setStyle("-fx-border-color: red;");
        }

        if (companyName.isEmpty()) {
            showAlert("Nom de compagnie invalide", "Le nom de compagnie ne peut pas être vide");
            registerCompanyNameIntervenant.setStyle("-fx-border-color: red;");
        }

        if (identifier.length() != 8) {
            showAlert("L'identifiant est invalide", "L'identifiant est un nombre d'exactement 8 chiffres");
            registerIdentifierIntervenant.setStyle("-fx-border-color: red;");
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
