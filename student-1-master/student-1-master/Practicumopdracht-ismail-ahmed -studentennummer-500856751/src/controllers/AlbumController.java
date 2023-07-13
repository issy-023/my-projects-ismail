package controllers;

import comparators.AlbumArtiestComparator;
import comparators.AlbumNaamComparator;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import models.Album;
import models.Nummer;
import practicumopdracht.MainApplication;
import practicumopdracht.data.AlbumDAO;
import practicumopdracht.data.NummerDAO;
import practicumopdracht.data.TextAlbumDAO;
import views.AlbumView;
import views.View;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;
import java.util.Optional;


public class AlbumController extends Controller {

    private AlbumView albumView;
    private AlbumDAO albumDAO;

    private NummerDAO nummerDAO;
    private Album album;
    private ObservableList<Album> albumObservableList;


    public AlbumController() {

        albumView = new AlbumView();
        album = new Album();


        albumDAO = MainApplication.getAlbumDAO();
        nummerDAO = MainApplication.getNummerDAO();


        albumView.getOpslaan().setOnAction(actionEvent -> opslaanAlert());
        albumView.getAfsluitenMenu().setOnAction(actionEvent -> afsluitenMenuAlert());
        albumView.getLaadMenu().setOnAction(actionEvent -> laadMenuAlert());


        albumView.getaZsoorteren().setOnAction(actionEvent -> soorteerNaamA_Z());
        albumView.getzAsoorteren().setOnAction(actionEvent -> soorteerNaamZ_A());
        albumView.getOpslaanMenu().setOnAction(actionEvent -> opslaanMenuAlert());
        albumView.getVerwijderen().setOnAction(actionEvent -> verwijderenAlert());
        albumView.getNieuw().setOnAction(actionEvent -> nieuwAlert());
        albumView.getSchakelen().setOnAction(actionEvent -> veranderPagina());
        listViewZien();
        voegChangeListenerListview();
        soorteerNaamA_Z();

    }

    /**
     * hier heb ik snel een methode geschreven om de album die iemand heeft gezoken te paken
     *
     * @return ik return de gelecteerde album
     */
    private Album gelesecteerdeAlbum() {
        return albumView.getAlbumListView().getSelectionModel().getSelectedItem();
    }

    /**
     * de ze methode laat je veranderen naar een anderen paginga allen als je een album heb geslecteerde
     * dus ik heb een methode geschreven met in de parameter dat je het allen kan acpteren als je een album meegeeft
     */
    public void veranderPagina() {
        if (gelesecteerdeAlbum() == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("geen toegang");
            alert.setHeaderText(" je moet een album hebben geselecteerd");
            alert.showAndWait();
            return;
        }
        MainApplication.switchController(new NummerController(gelesecteerdeAlbum()));
    }

    private void verwijderenAlert() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);

        Album geselecteerdAlbum = albumView.getAlbumListView().getSelectionModel().getSelectedItem();

        List<Nummer> nummersAlbum = nummerDAO.getAllfor(geselecteerdAlbum);


        alert.setTitle("verwijdert");
        alert.setHeaderText(" wordt verwijderd");
        alert.setResizable(false);
        alert.setContentText("Select okay or cancel this alert.");
        alert.getButtonTypes().add(ButtonType.YES);
        alert.getButtonTypes().add(ButtonType.NO);
        alert.getButtonTypes().remove(ButtonType.OK);
        alert.getButtonTypes().remove(ButtonType.CANCEL);


        Optional<ButtonType> antwoord = alert.showAndWait();


/**
 * hier kijk ik wel keuzen ze hebben gemaakt als de keuzen gelijk is aan yes dan wordt de album verwijderen
 * en door een foreach loop verwijder ik alle de nummers die in de album zitten ook.
 */
        if (Objects.equals(antwoord.get(), ButtonType.YES)) {
            albumDAO.remove(geselecteerdAlbum);
            for (Nummer nummer : nummersAlbum) {
                nummerDAO.remove(nummer);
            }
// hier verwijder ik heb ook in de listview
            albumView.getAlbumListView().getItems().remove(geselecteerdAlbum);
            maakInvoerveldenLeeg();
        }
    }


    private void nieuwAlert() {
        maakInvoerveldenLeeg();

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("nieuw");
        alert.setHeaderText(" je maakt nu nieuw album");
        alert.setResizable(false);
        alert.setContentText("Select okay or cancel this alert.");
        alert.showAndWait();
    }

    /**
     * hier heb ik de methode maak invoervelden leeg geschreven dit doe ik omdat ik deze methode een paar keer gebruik
     * en ik ook in de listvieuw het deselecteer.
     */
    private void maakInvoerveldenLeeg() {
        albumView.getNaam().clear();
        albumView.getArtiest().clear();
        albumView.getBeschrijving().clear();
        albumView.getPublicatiedatum().getEditor().clear();
        albumView.getNederlandstalig().setSelected(false);

        albumView.getAlbumListView().getSelectionModel().clearSelection();
    }

    private void opslaanMenuAlert() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("Opslaan");
        alertConf.setHeaderText("wil je deze albums opslaan");
        alertConf.setContentText("Je gaat dit album opslaan");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);

        Optional<ButtonType> antwoord = alertConf.showAndWait();
/**
 * als het antwoord ja is sla ik de album en nummer te gelijk op 
 */
        if (antwoord.get().equals(ButtonType.YES)) {
            MainApplication.getAlbumDAO().save();
            MainApplication.getNummerDAO().save();
            alertInfo.setTitle(" opgeslagen");
            alertInfo.setHeaderText(" je hebt de album opgeslagen");
        } else {
            alertInfo.setTitle("niet opgeslagen");
        }

    }

    private void laadMenuAlert() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("inladen");
        alertConf.setHeaderText("wil je albums inladen");
        alertConf.setContentText("als je op yes klikt worden je albums en nummers ingelanden");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);

        Optional<ButtonType> antwoord = alertConf.showAndWait();
/**
 * als het antwoord ja is laad  ik de album data van de bestand
 */
        if (antwoord.get().equals(ButtonType.YES)) {
            MainApplication.getAlbumDAO().load();
            MainApplication.getNummerDAO().load();
            listViewZien();
            alertInfo.setTitle(" je opgeslaagte data");
            alertInfo.setHeaderText(" je hebt de album info");
        } else {
            alertInfo.setTitle("niet ingeladen");
        }

    }

    private void afsluitenMenuAlert() {
        Alert alertInfo = new Alert(Alert.AlertType.INFORMATION);
        Alert alertConf = new Alert(Alert.AlertType.CONFIRMATION);

        alertConf.setTitle("afsluiten");
        alertConf.setHeaderText(" opslaan keuzen?");
        alertConf.setContentText("als je wil dat de data moet worden opgeslagen kik yes ");
        alertConf.getButtonTypes().add(ButtonType.NO);
        alertConf.getButtonTypes().add(ButtonType.YES);
        alertConf.getButtonTypes().remove(ButtonType.CANCEL);
        alertConf.getButtonTypes().remove(ButtonType.OK);

        Optional<ButtonType> antwoord = alertConf.showAndWait();

        if (antwoord.get().equals(ButtonType.YES)) {
            opslaanMenuAlert();
            alertInfo.setTitle(" je opgeslaagte data");
            alertInfo.setHeaderText(" je hebt de album info");
            Platform.exit();
        } else {
            alertInfo.setTitle("je data word niet opgeslagen");
            Platform.exit();
        }
    }

    private void opslaanAlert() {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("ontbreekt of is ongeldig");
        String albumNameInvoer = albumView.getNaam().getText();
        String artiestInvoer = albumView.getArtiest().getText();
        String beschrijvingInvoer = albumView.getBeschrijving().getText();
        LocalDate publicatiedatum = albumView.getPublicatiedatum().getValue();
        boolean nederlandstaligInvoer = albumView.getNederlandstalig().isSelected();


        StringBuilder stringBuilder = new StringBuilder();

        if (albumNameInvoer.isBlank()) {
            stringBuilder.append("je moet de album naam invullen ");
            albumView.getNaam().setStyle("-fx-border-color: red");
        }

        if (artiestInvoer.isBlank()) {
            stringBuilder.append("\nje moet de artiest naam invullen ");
            albumView.getArtiest().setStyle("-fx-border-color: red");
        }

        if (beschrijvingInvoer.isBlank()) {
            stringBuilder.append("\nje moet de beschrijving invullen ");
            albumView.getBeschrijving().setStyle("-fx-border-color: red");
        }
        if (publicatiedatum == (null)) {
            stringBuilder.append("\nje datum is niet ingevuld ");
            albumView.getPublicatiedatum().setStyle("-fx-border-color: red");
        }
        if (stringBuilder.isEmpty()) {
            Album geslecteerdeAlbum = albumView.getAlbumListView().getSelectionModel().getSelectedItem();
            if (geslecteerdeAlbum == null) {
                geslecteerdeAlbum = new Album(albumNameInvoer, publicatiedatum, nederlandstaligInvoer, artiestInvoer, beschrijvingInvoer);
                albumView.getAlbumListView().getItems().add(geslecteerdeAlbum);
            } else {

                // hier zet ik de data in de module.
                geslecteerdeAlbum.setNaam(albumNameInvoer);
                geslecteerdeAlbum.setArtiest(artiestInvoer);
                geslecteerdeAlbum.setBeschrijving(beschrijvingInvoer);
                geslecteerdeAlbum.setPublicatiedatum(publicatiedatum);
                geslecteerdeAlbum.setNederlandstalig(nederlandstaligInvoer);

// hier plaats ik het in de listview
                albumView.getAlbumListView().refresh();
            }
//hier zet ik de verandering in de dao
            albumDAO.addOrUpdate(geslecteerdeAlbum);


            alert.setTitle("opgeslagen");
            alert.setHeaderText("opslaan");
            alert.setContentText(geslecteerdeAlbum.toString());
            alert.showAndWait();

            maakInvoerveldenLeeg();


        } else {
            alert.setHeaderText(stringBuilder.toString());
            alert.setContentText("Select okay or cancel this alert.");
            alert.showAndWait();

        }

        listViewZien();
    }

    /**
     * in deze mehthode zet ik de data in de listview
     */

    private void listViewZien() {
        albumObservableList = FXCollections.observableArrayList(albumDAO.getAll());
        albumView.getAlbumListView().setItems(albumObservableList);

    }

    /**
     * eerst zet ik een eventlistener op de listview en dan pak oud en nieuw geselecteerde item. ik maak van de nieuuw gesecleeterde een pak de album model en zet het in de invoervelden
     */
    private void voegChangeListenerListview() {
        albumView.getAlbumListView().getSelectionModel().selectedItemProperty().addListener(((observableValue, oldAlbum, selectetedAlbum) ->
        {
            Album geslecteerdeAlbum = selectetedAlbum;
            if (geslecteerdeAlbum == null) {

            } else {
                albumView.getNaam().setText(geslecteerdeAlbum.getNaam());
                albumView.getArtiest().setText(geslecteerdeAlbum.getArtiest());
                albumView.getBeschrijving().setText(geslecteerdeAlbum.getBeschrijving());
                albumView.getPublicatiedatum().setValue(geslecteerdeAlbum.getPublicatiedatum());
                albumView.getNederlandstalig().setSelected(geslecteerdeAlbum.isNederlandstalig());
            }
        }));
    }

    private void soorteerNaamA_Z() {
        FXCollections.sort(albumView.getAlbumListView().getItems(), new AlbumNaamComparator());

    }

    private void soorteerNaamZ_A() {
        FXCollections.sort(albumView.getAlbumListView().getItems(), new AlbumNaamComparator().reversed());

    }


    @Override
    public View getView() {
        return albumView;
    }
}
