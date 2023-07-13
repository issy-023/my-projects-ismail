package controllers;

import comparators.NummerBeoordeling;
import comparators.NummerNaamComparator;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Album;
import models.Nummer;
import practicumopdracht.MainApplication;
import practicumopdracht.data.AlbumDAO;
import practicumopdracht.data.NummerDAO;
import views.NummerView;
import views.View;

import java.util.Optional;


public class NummerController extends Controller {
    private  NummerView nummerView;
    private AlbumDAO albumDAO;
    private NummerDAO nummerDAO;
    private Nummer nummer;

    private ObservableList<Album> albumObservableList;
    private ObservableList<Nummer> nummerObservableList;


    public NummerController(Album album) {

        nummerView = new NummerView();
        nummer = new Nummer();
        albumDAO = MainApplication.getAlbumDAO();
        nummerDAO = MainApplication.getNummerDAO();
        listViewZien(album);
        comboBoxVullen(album);

        nummerView.getSchakelen().setOnAction(actionEvent -> veranderPagina());
        nummerView.getVerwijderen().setOnAction(actionEvent -> verwijderenAlert());
        nummerView.getNieuw().setOnAction(actionEvent -> nieuwAlert());
        nummerView.getOpslaan().setOnAction(actionEvent -> opslaanAlert());
        nummerView.getHoogsteBeordeling().setOnAction(actionEvent ->beoordeling_hoogNaarLaag());
        nummerView.getLaagsteBeoordeling().setOnAction(actionEvent ->beoordeling_laagNaarHoog());
        nummerView.getTitelNummerA_Z().setOnAction(actionEvent -> naamSoorteringA_Z());
        nummerView.getTitelNummerZ_A().setOnAction(actionEvent -> naamSoorteringZ_A());

        nummerView.getAlbumComboBox().setOnAction(actionEvent -> comboxNummerzien());

        beoordeling_hoogNaarLaag();

        voegChangeListenerListview();
    }

    public void veranderPagina() {
        MainApplication.switchController(new AlbumController());
    }

    private void opslaanAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("opslaan melding");




        String nummerInvoerNaam = nummerView.getNaam().getText();
        String nummerInvoerBeoordelingString = nummerView.getBeoordeling().getText();
        String nummerInvoerDuurInSeconden = nummerView.getDuurInSeconden().getText();

        StringBuilder stringBuilder = new StringBuilder();

        if (nummerInvoerNaam.isBlank()) {
            stringBuilder.append(" de naam invoerveld is leeg\n");
            nummerView.getNaam().setStyle("-fx-border-color: red");
        }
        if (nummerInvoerBeoordelingString.isBlank()|| Double.parseDouble(nummerInvoerBeoordelingString)<1) {
            stringBuilder.append("de beoordeling invoerveld is leeg\n");
            nummerView.getBeoordeling().setStyle("-fx-border-color: red");
        } else {
            try {
                double invoerBeoordeling = Double.parseDouble(nummerInvoerBeoordelingString);
                System.out.println(invoerBeoordeling);
            } catch (NumberFormatException ex) {
                stringBuilder.append(" de invoer dat je hebt in de kolom geef je beoordeling is niet een getal \n");
                nummerView.getBeoordeling().setStyle("-fx-border-color: red");

            }
        }
        if (nummerInvoerDuurInSeconden.isBlank()||Integer.parseInt(nummerInvoerDuurInSeconden)<1) {
            stringBuilder.append("de lengte van het nummer invoerveld is leeg\n");
            nummerView.getDuurInSeconden().setStyle("-fx-border-color: red");
        } else {
            try {
                int invoerDuur = Integer.parseInt(nummerInvoerDuurInSeconden);
                System.out.println(invoerDuur);
            } catch (NumberFormatException ex) {
                stringBuilder.append(" de invoer dat je hebt in de kolom lengte van het nummer  is niet een getal \n");
                nummerView.getBeoordeling().setStyle("-fx-border-color: red");

            }
        }
        if (stringBuilder.isEmpty()) {
            Album albumGeslecteerd = (Album) nummerView.getAlbumComboBox().getSelectionModel().getSelectedItem();
            Nummer nummerGeselecteerd = nummerView.getNummerListView().getSelectionModel().getSelectedItem();
            if (nummerGeselecteerd==null) {
                nummerGeselecteerd= new Nummer(albumGeslecteerd, nummerInvoerNaam, Double.parseDouble(nummerInvoerBeoordelingString), Integer.parseInt(nummerInvoerDuurInSeconden));
                nummerView.getNummerListView().getItems().add(nummerGeselecteerd);

            }else {

            nummerGeselecteerd.setNaam(nummerInvoerNaam);
            nummerGeselecteerd.setDuurInSeconden(Integer.parseInt(nummerInvoerDuurInSeconden));
            nummerGeselecteerd.setBeoordeling(Double.parseDouble(nummerInvoerBeoordelingString));
                nummerView.getNummerListView().refresh();
            }
            nummerDAO.addOrUpdate(nummerGeselecteerd);

            alert.setTitle("opgeslagen");
            alert.setHeaderText("opslaan");
            alert.setContentText(nummerGeselecteerd.toString());
            alert.showAndWait();

            alertWitMaken();


        } else {

            alert.setContentText(stringBuilder.toString());
            alert.showAndWait();
        }
    }
    private void alertWitMaken(){
        //            hier maak ik de velden weer wit
        nummerView.getBeoordeling().setStyle("-fx-border-color: white");
        nummerView.getDuurInSeconden().setStyle("-fx-border-color: white");
        nummerView.getNaam().setStyle("-fx-border-color: white");
    }
    private void nieuwAlert() {
        maakInvoerveldenLeeg();
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("nieuw");
        alert.setHeaderText(" je maakt nu nieuw nummer");
        alert.setResizable(false);
        alert.setContentText("Select okay or cancel this alert.");
        alert.showAndWait();
    }
    private void maakInvoerveldenLeeg(){
        nummerView.getNaam().clear();
        nummerView.getBeoordeling().clear();
        nummerView.getDuurInSeconden().clear();

        nummerView.getNummerListView().getSelectionModel().clearSelection();
    }


    private void verwijderenAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Nummer geselcteerdeNummer = nummerView.getNummerListView().getSelectionModel().getSelectedItem();



        alert.setTitle("verwijdert");
        alert.setHeaderText(" wordt verwijderd");
        alert.setResizable(false);
        alert.setContentText("Select okay or cancel this alert.");
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);

        Optional<ButtonType> antwoord =alert.showAndWait();
        if (antwoord.get()==ButtonType.YES){
            nummerDAO.remove(geselcteerdeNummer);
            nummerView.getNummerListView().getItems().remove(geselcteerdeNummer);
            maakInvoerveldenLeeg();
        }



    }
    private void comboBoxVullen(Album album){
        albumObservableList = FXCollections.observableArrayList(albumDAO.getAll());
        nummerView.getAlbumComboBox().setItems(albumObservableList);
        nummerView.getAlbumComboBox().setValue(album);
    }

    private void listViewZien(Album album){
        nummerObservableList = FXCollections.observableArrayList(MainApplication.getNummerDAO().getAllfor(album));
        nummerView.getNummerListView().setItems(nummerObservableList);

    }
    private void voegChangeListenerListview(){
        nummerView.getNummerListView().getSelectionModel().selectedItemProperty().addListener(((observableValue,oudeNummer,geselecteerdeNummer )->{
            Nummer gekozenNummer = geselecteerdeNummer;
            if (gekozenNummer == null){

            }else {
                nummerView.getNaam().setText(gekozenNummer.getNaam());
                // hier ben er achter gekomen dat set text niet werkt dus do
                nummerView.getDuurInSeconden().setText(String.valueOf(gekozenNummer.getDuurInSeconden()));
                nummerView.getBeoordeling().setText(String.valueOf(gekozenNummer.getBeoordeling()));
            }
        } ));
    }

    private void beoordeling_hoogNaarLaag(){
        FXCollections.sort(nummerView.getNummerListView().getItems(),new NummerBeoordeling().reversed());
        nummerView.getTitelNummerA_Z().setSelected(false);
        nummerView.getTitelNummerZ_A().setSelected(false);
        nummerView.getLaagsteBeoordeling().setSelected(false);
    }
    private void beoordeling_laagNaarHoog(){
        FXCollections.sort(nummerView.getNummerListView().getItems(),new NummerBeoordeling());
        nummerView.getTitelNummerA_Z().setSelected(false);
        nummerView.getTitelNummerZ_A().setSelected(false);
        nummerView.getHoogsteBeordeling().setSelected(false);
    }

    private void naamSoorteringA_Z(){
        FXCollections.sort(nummerView.getNummerListView().getItems(),new NummerNaamComparator());
        nummerView.getTitelNummerZ_A().setSelected(false);
        nummerView.getHoogsteBeordeling().setSelected(false);
        nummerView.getLaagsteBeoordeling().setSelected(false);
    }
    private void naamSoorteringZ_A(){
        FXCollections.sort(nummerView.getNummerListView().getItems(),new NummerNaamComparator().reversed());
        nummerView.getTitelNummerA_Z().setSelected(false);
        nummerView.getHoogsteBeordeling().setSelected(false);
        nummerView.getLaagsteBeoordeling().setSelected(false);
    }


    private  void comboxNummerzien(){
        Album geselecteerdeAlbum  = (Album) nummerView.getAlbumComboBox().getValue();
        listViewZien(geselecteerdeAlbum);
        maakInvoerveldenLeeg();
    }

    @Override
    public View getView() {
        return nummerView;
    }
}
