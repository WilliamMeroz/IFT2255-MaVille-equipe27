package maville.equipe27.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.RequeteTravail;

import java.text.Normalizer;
import java.time.LocalDate;
import java.util.List;

public class ResidentMenuViewControllerFx {

    private ResidentController residentController;

    @FXML
    private Button entravesButton;

    @FXML
    private RadioButton entravesRadioRue;

    @FXML
    private RadioButton entravesRadioTravail;

    @FXML
    private TableView<Entrave> entravesTableView;

    @FXML
    private TableColumn<Entrave, String> tableFromCol;

    @FXML
    private TableColumn<Entrave, String> tableLargObCol;

    @FXML
    private TableColumn<Entrave, String> tableStatusPisteCol;

    @FXML
    private TableColumn<Entrave, String> tableStatusTroitCol;

    @FXML
    private TableColumn<Entrave, String> tableToCol;

    @FXML
    private TableColumn<Entrave, String> tableTypeCol;

    @FXML
    private TextField entravesTextBox;

    @FXML
    private ToggleGroup ta;

    @FXML
    private TextArea requeteDesc;

    @FXML
    private TextField requeteTitre;

    @FXML
    private ComboBox<?> requeteType;

    @FXML
    private DatePicker requeteDateDebut;

    @FXML
    private Button boutonNouvelleRequete;

    @FXML
    public void initialize() {
        residentController = new ResidentController();

        entravesButton.setOnAction(event -> handleEntraveSearch());
        entravesRadioRue.setOnAction(event -> entravesTextBox.setPromptText("Nom de rue"));
        entravesRadioTravail.setOnAction(event -> entravesTextBox.setPromptText("numéro de travail"));

        tableFromCol.setCellValueFactory(new PropertyValueFactory<>("from"));
        tableLargObCol.setCellValueFactory(new PropertyValueFactory<>("impactWidth"));
        tableStatusPisteCol.setCellValueFactory(new PropertyValueFactory<>("bikepathStatus"));
        tableStatusTroitCol.setCellValueFactory(new PropertyValueFactory<>("sidewalkStatus"));
        tableToCol.setCellValueFactory(new PropertyValueFactory<>("to"));
        tableTypeCol.setCellValueFactory(new PropertyValueFactory<>("impactType"));


        boutonNouvelleRequete.setOnAction(event -> handleNouvelleRequete());
    }

    private void handleEntraveSearch() {
        String text = entravesTextBox.getText();
        if (text.isEmpty()) {
            showAlert("Recherche invalide", "Entrez une valeur de recherche");
            entravesTextBox.setStyle("-fx-border-color: red;"); // Highlight the email field
        } else entravesTextBox.setStyle("");

        if (entravesRadioTravail.isSelected()) {
            List<Entrave> entraves = residentController.consulterEntravesTravail(text);
            ObservableList<Entrave> data = FXCollections.observableArrayList(entraves);
            entravesTableView.setItems(data);
        }

        if (entravesRadioRue.isSelected()) {
            List<Entrave> entraves = residentController.consulterEntravesRue(text);
            ObservableList<Entrave> data = FXCollections.observableArrayList(entraves);
            entravesTableView.setItems(data);
        }
    }

    private void handleNouvelleRequete() {
        String requestTitle = requeteTitre.getText();
        String requeteTypeStr = (String) requeteType.getSelectionModel().getSelectedItem();
        String requeteDescStr = requeteDesc.getText().replaceAll("\n", System.lineSeparator());
        LocalDate dateDebut = requeteDateDebut.getValue();

        String normalizedType = Normalizer.normalize(requeteTypeStr, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
        TravauxTypes type = TravauxTypes.valueOf(normalizedType.toUpperCase());

        if (requestTitle.isEmpty()) {
            showAlert("Titre invalide", "Le titre de la requête ne peut pas être vide.");
            entravesTextBox.setStyle("-fx-border-color: red;");
        }

        if (requeteDescStr.isEmpty()) {
            showAlert("Description invalide", "La description de la requête ne peut pas être vide.");
            entravesTextBox.setStyle("-fx-border-color: red;");
        }

        if (dateDebut.isBefore(LocalDate.now())) {
            showAlert("Date de début invalide", "La date de début doit être dans le futur.");
            entravesTextBox.setStyle("-fx-border-color: red;");
        }

        RequeteTravail requeteTravail = new RequeteTravail(requestTitle, requeteDescStr, type, dateDebut);
        if (residentController.saveRequest(requeteTravail)) {
            requeteTitre.setText("");
            requeteDesc.setText("");
            showAlertSuccess("Requête créée avec succès", "Les intervenants seront notifiées");
        } else {
            showAlert("Erreur durant la création", "Un erreur est survenue durant la création de la requête");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void showAlertSuccess(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
