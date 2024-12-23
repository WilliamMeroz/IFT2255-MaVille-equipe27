package maville.equipe27.views;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import maville.equipe27.controllers.IntervenantController;
import maville.equipe27.enums.ProjetStatus;
import maville.equipe27.enums.TravauxTypes;
import maville.equipe27.helpers.ConnectedIntervenant;
import maville.equipe27.models.Projet;
import maville.equipe27.models.RequeteTravail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class IntervenantMenuViewControllerFx {

    private IntervenantController intervenantController;

    @FXML
    private TableColumn<RequeteTravail, LocalDate> colRequetesDate;

    @FXML
    private TableColumn<RequeteTravail, String> colRequetesDesc;

    @FXML
    private TableColumn<RequeteTravail, String> colRequetesTitre;

    @FXML
    private TableColumn<RequeteTravail, TravauxTypes> colRequetesType;

    @FXML
    private TableView<RequeteTravail> tableViewRequetes;

    @FXML
    private Spinner<Integer> nouveauProjetA;

    @FXML
    private Button nouveauProjetButton;

    @FXML
    private Spinner<Integer> nouveauProjetDe;

    @FXML
    private DatePicker nouveauProjetDebut;

    @FXML
    private TextField nouveauProjetDesc;

    @FXML
    private DatePicker nouveauProjetFin;

    @FXML
    private ComboBox<String> nouveauProjetQuartier;

    @FXML
    private TextField nouveauProjetRues;

    @FXML
    private TextField nouveauProjetTitre;

    @FXML
    private ComboBox<TravauxTypes> nouveauProjetType;

    @FXML
    private Button nouveauProjetAjouterButton;

    @FXML
    private Button nouveauProjetRetirerButton;

    @FXML
    private Button updateProjectButton;

    @FXML
    private TextArea updateProjectDesc;

    @FXML
    private ComboBox<String> updateProjectList;

    @FXML
    private TextField updateProjectTitre;

    @FXML
    private ComboBox<ProjetStatus> updateProjectType;

    @FXML
    private TableView<RequeteTravail> candidaturesAcceptTableView;

    @FXML
    private TableColumn<RequeteTravail, String> colCandidatureAcceptTitre;

    @FXML
    private TableColumn<RequeteTravail, String> colCandidatureAcceptStatus;

    @FXML
    private TableColumn<RequeteTravail, String> colCandidatureAcceptResident;

    @FXML
    private Text candidatureAcceptLabel;

    @FXML
    private Button refuserCandidatureButton;

    @FXML
    private Button confirmerCandidatureButton;

    private ArrayList<String> quartiers = new ArrayList<>(Arrays.asList("Plateau-Mont-Royal", "Mile End", "Vieux-Montréal", "Centre-ville", "Petite Italie", "Griffintown", "Hochelaga-Maisonneuve", "Saint-Henri", "Outremont", "Westmount", "Côte-des-Neiges", "Notre-Dame-de-Grâce", "Le Village", "Villeray", "Rosemont–La Petite-Patrie", "Pointe-Saint-Charles", "Lachine", "LaSalle", "Ahuntsic", "Verdun"));
    private ObservableList<String> quartiersSelects;
    private Set<String> selectedQuartiers = new HashSet<>();

    private ArrayList<Projet> userProjects;
    private ObservableList<String> projectsUpdates;

    private RequeteTravail chosenAcceptedRequete;

    @FXML
    public void initialize() {
        intervenantController = new IntervenantController();

        List<RequeteTravail> requetes = intervenantController.consulterRequetes();

        colCandidatureAcceptTitre.setCellValueFactory(new PropertyValueFactory<>("titreTravail"));
        colCandidatureAcceptStatus.setCellValueFactory(new PropertyValueFactory<>("status"));
        colCandidatureAcceptResident.setCellValueFactory(new PropertyValueFactory<>("owner"));

        ArrayList<RequeteTravail> requetesCandidatures = new ArrayList<>();
        if (!requetes.isEmpty()) {
            for (RequeteTravail r : requetes) {
                if (r.getChosenCandidate().equals(ConnectedIntervenant.getInstance().getIntervenant().getEmail())) {
                    if (r.getStatus().equals("Candidature acceptée")) {
                        requetesCandidatures.add(r);
                    }
                }
            }
        }

        refuserCandidatureButton.setOnAction(evt -> {
            if (chosenAcceptedRequete != null) {
                candidatureAcceptLabel.setText("Requête: ");
                chosenAcceptedRequete = null;

            }
        });

        if (!requetesCandidatures.isEmpty()) {
            ObservableList<RequeteTravail> data = FXCollections.observableArrayList(requetesCandidatures);
            candidaturesAcceptTableView.setItems(data);
            candidaturesAcceptTableView.setRowFactory(tv -> {
                TableRow<RequeteTravail> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        candidatureAcceptLabel.setText("Requête: " + row.getItem().getTitreTravail());
                        acceptRequete(row.getItem());
                    }
                });
                return row;
            });
        }

        colRequetesTitre.setCellValueFactory(new PropertyValueFactory<>("titreTravail"));
        colRequetesDesc.setCellValueFactory(new PropertyValueFactory<>("description"));
        colRequetesType.setCellValueFactory(new PropertyValueFactory<>("typeTravail"));
        colRequetesDate.setCellValueFactory(new PropertyValueFactory<>("dateDebut"));

        if (!requetes.isEmpty()) {
            ObservableList<RequeteTravail> data = FXCollections.observableArrayList(requetes);
            tableViewRequetes.setItems(data);

            tableViewRequetes.setRowFactory(tv -> {
                TableRow<RequeteTravail> row = new TableRow<>();
                row.setOnMouseClicked(event -> {
                    if (event.getClickCount() == 2 && (!row.isEmpty())) {
                        RequeteTravail rowData = row.getItem();
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Soumission candiature");
                        alert.setHeaderText("Êtes-vous certain de vouloir soumettre votre candiature (OK pour oui) ?");
                        Optional<ButtonType> result = alert.showAndWait();
                        if (result.get().equals(ButtonType.OK)) {
                            if (intervenantController.soumettreCandiatureRequete(rowData)) {
                                alert.close();
                                showAlertSuccess("Candidature envoyée avec succès", "merci");
                            }
                        } else {
                            alert.close();
                        }
                    }
                });
                return row;
            });
        }

        // Nouveau projet logique et setup
        nouveauProjetButton.setOnAction(actionEvent -> handleNewProjet());

        nouveauProjetType.setItems(FXCollections.observableArrayList(TravauxTypes.values()));
        nouveauProjetType.setValue(TravauxTypes.ROUTIER);

        quartiersSelects = FXCollections.observableArrayList(quartiers);
        nouveauProjetQuartier.setItems(quartiersSelects);
        nouveauProjetQuartier.setValue("Plateau-Mont-Royal");

        SpinnerValueFactory<Integer> deFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);
        SpinnerValueFactory<Integer> aFactor = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0);

        nouveauProjetDe.setValueFactory(deFactory);
        nouveauProjetA.setValueFactory(aFactor);

        nouveauProjetAjouterButton.setOnAction(event -> handleAjouterQuartier());
        nouveauProjetRetirerButton.setOnAction(event -> handleRemoveQuartier());

        // Update project page.
        ObservableList<ProjetStatus> projetTypesStr = FXCollections.observableArrayList(ProjetStatus.values());
        updateProjectType.setItems(projetTypesStr);
        userProjects = intervenantController.getUserProjects();
        List<String> titres = userProjects.stream().map(Projet::getTitre).toList();
        projectsUpdates = FXCollections.observableArrayList(titres);
        updateProjectList.setItems(projectsUpdates);


        if (!titres.isEmpty()) {
            updateProjectList.setValue(titres.getFirst());
        } else {
            updateProjectList.setValue("Aucune project");
        }

        updateProjectList.setOnAction(event -> {
            String selectedTitle = updateProjectList.getSelectionModel().getSelectedItem();
            Projet selectedProject = userProjects.stream().filter(p -> p.getTitre().equals(selectedTitle)).toList().getFirst();
            updateProjectTitre.setText(selectedProject.getTitre());
            updateProjectDesc.setText(selectedProject.getDesc());
            updateProjectType.setValue(selectedProject.getStatus());
        });
        updateProjectButton.setOnAction(event -> handleProjectUpdate());
    }

    private void acceptRequete(RequeteTravail requeteTravail) {
        if (intervenantController.acceptRequete(requeteTravail)) {

        }
    }

    private void handleNewProjet() {
        Pattern pattern = Pattern.compile("^([^,]+(,[^,]+)*)$");
        Matcher matcher = pattern.matcher(nouveauProjetRues.getText());

        if (nouveauProjetTitre.getText().isEmpty()) {
            showAlert("Titre invalide", "Le titre ne peut pas être vide.", nouveauProjetTitre);
        } else if (nouveauProjetDesc.getText().isEmpty()) {
            showAlert("Description invalide", "La description ne peut pas être vide", nouveauProjetDesc);
        } else if (nouveauProjetRues.getText().isEmpty()) {
            showAlert("Rues invalides", "Les rues ne peuvent pas être vides", nouveauProjetRues);
        } else if (nouveauProjetDebut.getValue() == null) {
            showAlert("Date de début invalide", "La date de début ne peut pas être vide", nouveauProjetDebut);
        } else if (nouveauProjetFin.getValue() == null) {
            showAlert("Date de fin invalide", "La date de fin ne peut pas être vide", nouveauProjetFin);
        } else if (!matcher.matches()) {
            showAlert("Rues invalides", "Les rues sont vides ou ne sont pas séparées par des virgules (,)", nouveauProjetRues);
        } else if (nouveauProjetDebut.getValue().isAfter(nouveauProjetFin.getValue())) {
            showAlert("Date de début invalide.", "La date de début ne peut pas être après la date de fin", nouveauProjetDebut);
        } else {
            String[] rues = nouveauProjetRues.getText().split(",");
            String projetTitre = nouveauProjetTitre.getText();
            String projetDesc = nouveauProjetDesc.getText();

            LocalDate projetDebut = nouveauProjetDebut.getValue();
            LocalDate projetFin = nouveauProjetFin.getValue();

            int projetDe = nouveauProjetDe.getValue();
            int projetA = nouveauProjetA.getValue();

            LocalTime projetDeHour = LocalTime.of(projetDe, 0);
            LocalTime projetAHour = LocalTime.of(projetA, 0);

            if (projetDeHour.isAfter(projetAHour)) {
                showAlert("Horaire des travaux invalide", "La première heure ne doit pas être après la deuxième heure", nouveauProjetDe);
            } else {
                String[] returnedQuartiers = Arrays.stream(selectedQuartiers.toArray()).map(q -> q.toString().substring(0, q.toString().length() - 2)).toArray(String[]::new);

                if (returnedQuartiers.length == 0) {
                    showAlert("Quartiers manquants", "Il faut choisir au moins un quartier", nouveauProjetQuartier);
                } else {
                    Projet projet = new Projet(ConnectedIntervenant.getInstance().getIntervenant().getCityIdentifier(),
                            projetTitre, projetDesc, nouveauProjetType.getValue(), returnedQuartiers, projetDebut, projetFin, projetAHour, projetDeHour, ProjetStatus.PRÉVUE, rues);
                    if(!intervenantController.createNewProject(projet)) {
                        showAlert("Erreur durant la création du projet", "Le projet n'a pas de nom unique ou une autre erreur est survenue", nouveauProjetAjouterButton);
                    }
                    else {
                        showAlertSuccess("Projet ajouté avec succès!", "Merci");
                        nouveauProjetTitre.setText("");
                        nouveauProjetTitre.setStyle("");
                        nouveauProjetDesc.setText("");
                        nouveauProjetDesc.setStyle("");
                        nouveauProjetRues.setText("");
                        nouveauProjetRues.setStyle("");
                        nouveauProjetQuartier.setStyle("");
                        selectedQuartiers.clear();
                        quartiersSelects = FXCollections.observableArrayList(quartiers);
                        nouveauProjetQuartier.setItems(quartiersSelects);
                        nouveauProjetQuartier.setValue("Plateau-Mont-Royal");
                        projectsUpdates.add(projet.getTitre());
                        userProjects.add(projet);
                    }
                }


            }
        }
    }

    private void handleAjouterQuartier() {
        int selectedIndex = nouveauProjetQuartier.getSelectionModel().getSelectedIndex();
        String selectedItem = nouveauProjetQuartier.getSelectionModel().getSelectedItem();
        if (selectedItem.charAt(selectedItem.length() - 1) != '*') {
            String newItem = selectedItem + " *";
            quartiersSelects.set(selectedIndex, newItem);
            selectedQuartiers.add(newItem);
        }
    }

    private void handleRemoveQuartier() {
        int selectedIndex = nouveauProjetQuartier.getSelectionModel().getSelectedIndex();
        String selectedItem = quartiersSelects.get(selectedIndex);
        if (selectedItem.charAt(selectedItem.length() - 1) == '*') {
            selectedQuartiers.remove(selectedItem);
            quartiersSelects.set(selectedIndex, selectedItem.substring(0, selectedItem.length() - 1));
        }
    }

    private void handleProjectUpdate() {
        String updatedProjectTitre = updateProjectList.getSelectionModel().getSelectedItem();
        String updateProjectNewTitre = updateProjectTitre.getText();
        ProjetStatus updateProjectNewStatus = updateProjectType.getValue();
        String updateProjectNewDesc = updateProjectDesc.getText();

        Projet foundProject = userProjects.stream().filter(p -> p.getTitre().equals(updatedProjectTitre)).findFirst().get();

        foundProject.setStatus(updateProjectNewStatus);
        foundProject.setDesc(updateProjectNewDesc);

        String newTitre = "";
        if (!updatedProjectTitre.equals(updateProjectNewTitre))
            newTitre = updateProjectNewTitre;

        if (!intervenantController.updateProject(foundProject, newTitre)) {
            showAlert("Erreur durant update", "Le projet que vous mettez à jour n'existe sûrement pas", updateProjectButton);
        } else {
            showAlertSuccess("Projet mis à jour avec succès", "Tout va bien");

        }
    }

    private void showAlert(String title, String message, Control control) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);

        control.setStyle("-fx-border-color: red;");

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
