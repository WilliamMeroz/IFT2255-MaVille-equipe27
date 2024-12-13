package maville.equipe27.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import maville.equipe27.controllers.ResidentController;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.ConnectedResident;
import maville.equipe27.models.Entrave;
import maville.equipe27.models.PrefHoraire;
import maville.equipe27.models.RequeteTravail;
import maville.equipe27.models.Travail;
import maville.equipe27.models.PrefHoraire;

import java.text.Normalizer;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

public class ResidentMenuViewControllerFx {

    private ResidentController residentController;

    @FXML
    private Button boutonNouvelleRequete;

    @FXML
    private Button entravesButton;

    @FXML
    private RadioButton entravesRadioRue;

    @FXML
    private RadioButton entravesRadioTravail;

    @FXML
    private TableView<Entrave> entravesTableView;

    @FXML
    private TextField entravesTextBox;

    @FXML
    private ComboBox<?> rechercheTravauxComboBox;

    @FXML
    private DatePicker requeteDateDebut;

    @FXML
    private TextArea requeteDesc;

    @FXML
    private TextField requeteTitre;

    @FXML
    private ComboBox<?> requeteType;

    @FXML
    private ToggleGroup t1;

    @FXML
    private ToggleGroup ta;

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
    private RadioButton travauxParQuartierRadio;

    @FXML
    private RadioButton travauxParTitreRadio;

    @FXML
    private RadioButton travauxParTypeRadio;

    @FXML
    private Button travauxRechercheButton;

    @FXML
    private TextField travauxRechercheTextBox;

    @FXML
    private TableView<Travail> travauxTableView;

    @FXML
    private TableColumn<Travail, LocalDate> colTravailDe;

    @FXML
    private TableColumn<Travail, LocalDate> colTravailA;

    @FXML
    private TableColumn<Travail, String> colTravailId;

    @FXML
    private TableColumn<Travail, String> colTravailIntervenant;

    @FXML
    private TableColumn<Travail, String> colTravailQuartier;

    @FXML
    private TableColumn<Travail, String> colTravailTitre;

    @FXML
    private TableColumn<Travail, TravauxTypes> colTravailType;

    @FXML
    private RadioButton radioTravauxCourrants;

    @FXML
    private RadioButton radioTravauxFuturs;

    @FXML
    private RadioButton radioTravauxTous;

    @FXML
    private RadioButton radioTravauxParType;

    @FXML
    private RadioButton radioTravauxParQuartier;

    @FXML
    private TextField voirTravauxTextBox;

    @FXML
    private ComboBox<?> voirTravauxComboBox;

    @FXML
    private Button voirTravauxButton;

    @FXML
    private Button residentPlanifButton;

    @FXML
    private Spinner<Integer> residentPrefDeHeure;

    @FXML
    private Spinner<Integer> residentPrefDeMin;

    @FXML
    private Spinner<Integer> residentPrefAHeure;

    @FXML
    private Spinner<Integer> residentPrefAMin;

    @FXML
    private TableColumn<PrefHoraire, LocalDate> residentPlanifTableA;

    @FXML
    private TableColumn<PrefHoraire, LocalDate> residentPlanifTableDe;

    @FXML
    private TableView<PrefHoraire> residentPlanifTableView;

    @FXML
    private Text residentPrefActuelleAText;

    @FXML
    private Text residentPrefActuelleDeText;

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
        travauxParTitreRadio.setOnAction(event -> {
            rechercheTravauxComboBox.setDisable(true);
            travauxRechercheTextBox.setDisable(false);
        });

        travauxParQuartierRadio.setOnAction(event -> {
            rechercheTravauxComboBox.setDisable(true);
            travauxRechercheTextBox.setDisable(false);
        });

        travauxParTypeRadio.setOnAction(event -> {
            rechercheTravauxComboBox.setDisable(false);
            travauxRechercheTextBox.setDisable(true);
        });

        travauxRechercheButton.setOnAction(event -> handleRechercheTravaux());

        ToggleGroup toggleGroupTravauxCourrantsFuturs = new ToggleGroup();
        radioTravauxCourrants.setToggleGroup(toggleGroupTravauxCourrantsFuturs);
        radioTravauxFuturs.setToggleGroup(toggleGroupTravauxCourrantsFuturs);

        ToggleGroup toggleGroupRechecheTravauxType = new ToggleGroup();
        radioTravauxTous.setToggleGroup(toggleGroupRechecheTravauxType);
        radioTravauxParQuartier.setToggleGroup(toggleGroupRechecheTravauxType);
        radioTravauxParType.setToggleGroup(toggleGroupRechecheTravauxType);

        radioTravauxTous.setOnAction(event -> {
            voirTravauxTextBox.setDisable(true);
            voirTravauxComboBox.setDisable(true);
        });

        radioTravauxParType.setOnAction(event -> {
            voirTravauxTextBox.setDisable(true);
            voirTravauxComboBox.setDisable(false);
        });

        radioTravauxParQuartier.setOnAction(event -> {
            voirTravauxTextBox.setDisable(false);
            voirTravauxComboBox.setDisable(true);
        });

        colTravailId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colTravailIntervenant.setCellValueFactory(new PropertyValueFactory<>("nomIntervenant"));
        colTravailQuartier.setCellValueFactory(new PropertyValueFactory<>("quartier"));
        colTravailType.setCellValueFactory(new PropertyValueFactory<>("type"));
        colTravailTitre.setCellValueFactory(new PropertyValueFactory<>("titre"));
        colTravailDe.setCellValueFactory(new PropertyValueFactory<>("debut"));
        colTravailA.setCellValueFactory(new PropertyValueFactory<>("fin"));

        voirTravauxButton.setOnAction(event -> handleVoirTravaux());

        residentPlanifButton.setOnAction(event -> handleNewPref());
        SpinnerValueFactory<Integer> heureFactDe = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        SpinnerValueFactory<Integer> minFactDe = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 59, 1);
        SpinnerValueFactory<Integer> heureFactA = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 24, 1);
        SpinnerValueFactory<Integer> minFactA = new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 59, 1);
        residentPrefDeHeure.setValueFactory(heureFactDe);
        residentPrefDeMin.setValueFactory(minFactDe);
        residentPrefAHeure.setValueFactory(heureFactA);
        residentPrefAMin.setValueFactory(minFactA);

        residentPlanifTableDe.setCellValueFactory(new PropertyValueFactory<>("de"));
        residentPlanifTableA.setCellValueFactory(new PropertyValueFactory<>("a"));

        String[] parts = ConnectedResident.getInstance().getResident().getAddress().split(",");
        String quartier = parts[parts.length - 1].substring(0, 3).toUpperCase();
        List<PrefHoraire> prefsTable = residentController.getHorairesByQuartier(quartier).stream().filter(prefHoraire -> !prefHoraire.getEmail().equals(ConnectedResident.getInstance().getResident().getEmail())).toList();

        if (!prefsTable.isEmpty()) {
            ObservableList<PrefHoraire> dataHoraires = FXCollections.observableArrayList(prefsTable);
            residentPlanifTableView.setItems(dataHoraires);
        }

        Optional<PrefHoraire> horaireUser = residentController.getHoraireFromUser(ConnectedResident.getInstance().getResident().getEmail());
        if (horaireUser.isPresent()) {
            residentPrefActuelleDeText.setText(horaireUser.get().getDe().getHour() + "h" + horaireUser.get().getDe().getMinute());
            residentPrefActuelleAText.setText(horaireUser.get().getA().getHour() + "h" + horaireUser.get().getA().getMinute());

        }
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

    private void handleVoirTravaux() {
        boolean isFutur = radioTravauxFuturs.isSelected();
        int typeRecherche = 1;
        String filtre = "";

        if (radioTravauxTous.isSelected()) {
            typeRecherche = 1;
        }

        if (radioTravauxParType.isSelected()) {
            String s = (String) voirTravauxComboBox.getSelectionModel().getSelectedItem();
            String normalizedType = Normalizer.normalize(s, Normalizer.Form.NFD).replaceAll("\\p{M}", "");
            TravauxTypes t = TravauxTypes.valueOf(normalizedType.toUpperCase());
            typeRecherche = 2;
            filtre = t.toString();
        }
        if (radioTravauxParQuartier.isSelected()){
            typeRecherche = 3;
            if (voirTravauxTextBox.getText().isEmpty()) {
                showAlert("Recherche invalide", "Entrez un nom de quartier");
                voirTravauxTextBox.setStyle("-fx-border-color: red;");
            } else {
                filtre = voirTravauxTextBox.getText();
            }
        }

        List<Travail> travaux = residentController.consulterTravaux(isFutur, typeRecherche, filtre);

        if (!travaux.isEmpty()) {
            ObservableList<Travail> data = FXCollections.observableArrayList(travaux);
            travauxTableView.setItems(data);
        }
    }

    private void handleRechercheTravaux() {
        if (travauxParTitreRadio.isSelected()) {
            String rechercheTitre = travauxRechercheTextBox.getText();
            if (rechercheTitre.isEmpty()) {
                travauxRechercheTextBox.setStyle("-fx-border-color: red;");
                showAlert("Recherche vide", "La recherche ne peut pas être vide");
            } else {

            }
        }

        if (travauxParTypeRadio.isSelected()) {

        }

        if (travauxParQuartierRadio.isSelected()) {

        }
    }

    private void handleNewPref() {
        int deHeure = residentPrefDeHeure.getValue();
        int deMin = residentPrefDeMin.getValue();
        int aHeure = residentPrefAHeure.getValue();
        int aMin = residentPrefAMin.getValue();

        if (deHeure > aHeure) {
            showAlert("Erreur horaires", "Assurez-vous que les heures et minutes soient valides.");
        } else {
            String[] parts = ConnectedResident.getInstance().getResident().getAddress().split(",");
            String quartier = parts[parts.length - 1].substring(0, 3);
            PrefHoraire prefHoraire = new PrefHoraire(ConnectedResident.getInstance().getResident().getEmail(),
                    LocalTime.of(deHeure, deMin),
                    LocalTime.of(aHeure, aMin),
                    quartier.toUpperCase());

            if (residentController.saveNewHoraire(prefHoraire)) {
                showAlertSuccess("Horaire ajoutée avec succès", "Tout va bien!");
                residentPrefActuelleDeText.setText(deHeure + "h" + deMin + "min");
                residentPrefActuelleAText.setText(aHeure + "h" + aMin + "min");
            } else {
                showAlert("Erreur inconnue pour horaire", "Woops");
            }
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
