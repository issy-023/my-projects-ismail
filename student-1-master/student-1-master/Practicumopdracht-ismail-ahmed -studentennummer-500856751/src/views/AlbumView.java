package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.*;

import models.Album;

public class AlbumView extends View {
    public static final int OPSLAAN_BUTTON_SIZE = 500;
    public static final int SIZE_FIELD = 500;
    public static final int NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE = 140;

    // hier plaats ik alle text fields
    private final TextField naam = new TextField();
    private final TextArea beschrijving = new TextArea();
    private final TextField artiest = new TextField();

    // hier plaats ik alle datepicker,checkbox,listview
    private final DatePicker publicatiedatum = new DatePicker();

    private final CheckBox nederlandstalig = new CheckBox();


    private final ListView<Album> albumListView = new ListView<>();


    // hier plaats ik al mijn radio buttons
    private MenuItem aZsoorteren = new MenuItem("[a-z}");
    private MenuItem zAsoorteren = new MenuItem("[z-a]");


    // hier plaats ik alle buttons

    private final Button opslaan = new Button("opslaan");
    private final Button verwijderen = new Button("verwijderen");
    private final Button schakelen = new Button("schakelen");
    private final Button nieuw = new Button("nieuw");

    // hier plaats ik alle labels
    private final Label naamLabel = new Label("naam van het album");
    private final Label beschrijvingLabel = new Label("beschrijving");
    private final Label publicatiedatumLabel = new Label("gepubliceerd");
    private final Label artiestLabel = new Label("artiest naam ");
    private final Label nederlandstaligLabel = new Label("nederlandstalig");

    // hier plaats ik alle panes
    private final Menu menu = new Menu("Bestand");
    private final Menu sortMenu = new Menu("sorteren");

    private final VBox albumVbox = new VBox();
    private final VBox borderVbox = new VBox();
    private final HBox albumHbox = new HBox();
    private final BorderPane borderPane = new BorderPane();

    private final HBox albumHbox3Buttons = new HBox();

    private final GridPane albumGridPane = new GridPane();
    private MenuBar menuBar = new MenuBar();

    private MenuItem opslaanMenu = new MenuItem("opslaan");
    private MenuItem laadMenu = new MenuItem("load");
    private MenuItem afsluitenMenu = new MenuItem("afsluiten");

    private MenuItem aSorteren = new MenuItem("A-Z ");
    private MenuItem zSorteren = new MenuItem("Z-A ");


    @Override
    protected Parent initializeView() {

        /**
         * hier zet ik alles in de grind pane op de juiste postie
         */

        sortMenu.getItems().addAll(aZsoorteren, zAsoorteren);

        menu.getItems().addAll(opslaanMenu, laadMenu, afsluitenMenu);
        menuBar.getMenus().addAll(menu, sortMenu);
        borderPane.setTop(menuBar);



        borderVbox.getChildren().addAll(borderPane);


//
        albumGridPane.add(borderVbox, 0, 0);
        albumGridPane.add(naamLabel, 0, 1);
        albumGridPane.add(naam, 1, 1);

        albumGridPane.add(artiestLabel, 0, 2);
        albumGridPane.add(artiest, 1, 2);

        albumGridPane.add(beschrijvingLabel, 0, 3);
        albumGridPane.add(beschrijving, 1, 3);


        albumGridPane.add(publicatiedatumLabel, 0, 4);
        albumGridPane.add(publicatiedatum, 1, 4);


        albumGridPane.add(nederlandstaligLabel, 0, 5);
        albumGridPane.add(nederlandstalig, 1, 5);
/**
 * hier zet ik de knoppen size EN FIELD SIZE
 */
        opslaan.setPrefWidth(OPSLAAN_BUTTON_SIZE);
        publicatiedatum.setPrefWidth(SIZE_FIELD);
        verwijderen.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);
        nieuw.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);
        schakelen.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);

        albumHbox.getChildren().addAll(opslaan);


        albumGridPane.add(albumHbox, 1, 6);


        albumGridPane.add(albumListView, 1, 8);


        albumHbox3Buttons.getChildren().addAll(nieuw, verwijderen, schakelen);
        albumGridPane.add(albumHbox3Buttons, 1, 9);


/////////////////////////////////////////////////////////////////////////////////////////////
        /**
         * hier zet ik de spacing ook zet ik de tussen ruimte tussen de kolemen.
         */
        albumGridPane.setHgap(10);
        albumGridPane.setVgap(10);
        albumHbox3Buttons.setSpacing(5);
        albumHbox3Buttons.setAlignment(Pos.CENTER);


        albumVbox.setPadding(new Insets(10, 10, 10, 10));


        albumVbox.getChildren().addAll(albumGridPane);


        return albumVbox;
    }

    @Override
    public Parent getRoot() {
        return initializeView();
    }

    public Button getOpslaan() {
        return opslaan;
    }


    public Button getVerwijderen() {
        return verwijderen;
    }

    public Button getSchakelen() {
        return schakelen;
    }

    public Button getNieuw() {
        return nieuw;
    }

    public TextField getNaam() {
        return naam;
    }

    public TextArea getBeschrijving() {
        return beschrijving;
    }

    public DatePicker getPublicatiedatum() {
        return publicatiedatum;
    }

    public ListView<Album> getAlbumListView() {
        return albumListView;
    }

    public CheckBox getNederlandstalig() {
        return nederlandstalig;
    }

    public TextField getArtiest() {
        return artiest;
    }

    public MenuItem getOpslaanMenu() {
        return opslaanMenu;
    }

    public MenuItem getLaadMenu() {
        return laadMenu;
    }

    public MenuItem getAfsluitenMenu() {
        return afsluitenMenu;
    }

    public MenuItem getaZsoorteren() {
        return aZsoorteren;
    }


    public MenuItem getzAsoorteren() {
        return zAsoorteren;
    }
}
