package views;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import models.Album;
import models.Nummer;


public class NummerView extends View {
    public static final int COMBOBOX_OPSLAAN_BUTTON_SIZE=500;

    public static final int NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE=140;

    // hier plaats ik alle text fields

    private final TextField naam = new TextField();
    private final TextField duurInSeconden = new TextField();
    private final TextField beoordeling= new TextField();

    // hier plaats ik alle buttons
    private final Button opslaan  = new Button("opslaan");
    private final Button verwijderen = new Button("verwijderen");

    private final Button schakelen = new Button("schakelen");
    private final Button nieuw  = new Button("nieuw");

    private final HBox radioBoxHbox = new HBox();


    private  final RadioButton laagsteBeoordeling = new RadioButton("laag-hoog");

    private final RadioButton hoogsteBeordeling = new RadioButton("hoog-laag");

    private final RadioButton titelNummerA_Z= new RadioButton("a-z op naam");

    private final RadioButton titelNummerZ_A = new RadioButton("z-a op naam");



    // hier plaats ik de labels
    private final Label naamLabel  = new Label("Naam van het nummer ");
    private final Label duurInSecondenLabel  = new Label("Lengte van het nummer");
    private final Label beoordelingLabel = new Label("Geef Je beoordeling");
    private final  Label comboBoxlabel = new Label("Album");

    // hier plaats ik de listview en combo box
    private final ListView<Nummer> nummerListView = new ListView<>();
    private final   ComboBox albumComboBox= new ComboBox<>();



    // hier plaats ik alle panes

    private final VBox nummerVbox = new VBox();
    private final HBox nummerHbox  = new HBox();
    private final HBox nummerHbox3Buttons =   new HBox();

    private final GridPane nummerGridPane = new GridPane();



    @Override
    protected Parent initializeView() {

        radioBoxHbox.getChildren().addAll(laagsteBeoordeling,hoogsteBeordeling,titelNummerA_Z,titelNummerZ_A);

        nummerGridPane.add(comboBoxlabel, 0, 1);
        albumComboBox.setPrefWidth(COMBOBOX_OPSLAAN_BUTTON_SIZE);
        nummerGridPane.add(albumComboBox, 1, 1);

        nummerGridPane.add(naamLabel, 0, 2);
        naam.setPrefWidth(COMBOBOX_OPSLAAN_BUTTON_SIZE);
        nummerGridPane.add(naam, 1, 2);

        nummerGridPane.add(duurInSecondenLabel, 0, 3);
        beoordeling.setPrefWidth(COMBOBOX_OPSLAAN_BUTTON_SIZE);
        nummerGridPane.add(duurInSeconden, 1, 3);

        nummerGridPane.add(beoordelingLabel, 0, 4);
        beoordeling.setPrefWidth(COMBOBOX_OPSLAAN_BUTTON_SIZE);
        nummerGridPane.add(beoordeling, 1, 4);

        opslaan.setPrefWidth(COMBOBOX_OPSLAAN_BUTTON_SIZE);

        nummerHbox.getChildren().addAll(opslaan);
        nummerGridPane.add(nummerHbox, 1, 5);

        nummerGridPane.add(radioBoxHbox,1,7);

        nummerGridPane.add(nummerListView, 1, 8);
        nummerListView.setMaxSize(750, 450);

        verwijderen.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);
        nieuw.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);
        schakelen.setPrefWidth(NIEUW_VERWIJDERER_SCHAKELEN_BUTTON_SIZE);
        nummerHbox3Buttons.getChildren().addAll(nieuw, verwijderen, schakelen);

        nummerGridPane.add(nummerHbox3Buttons, 1, 9);
        /////////////////////////////////////////////////////////////
        /**
         * hier zet ik de spacing ook zet ik de tussen ruimte tussen de kolemen.
         */
        nummerGridPane.setHgap(10);
        nummerGridPane.setVgap(10);
        nummerHbox3Buttons.setSpacing(5);
        nummerHbox3Buttons.setAlignment(Pos.CENTER);



        nummerVbox.setPadding(new Insets(10, 10, 10, 10));
        nummerVbox.getChildren().addAll(nummerGridPane);



        return nummerVbox;

    }

    @Override
    public Parent getRoot() {
        return initializeView();
    }

    public TextField getNaam() {
        return naam;
    }

    public TextField getDuurInSeconden() {
        return duurInSeconden;
    }

    public TextField getBeoordeling() {
        return beoordeling;
    }

    public ComboBox getAlbumComboBox() {
        return albumComboBox;
    }

    public ListView<Nummer> getNummerListView() {
        return nummerListView;
    }

    public RadioButton getLaagsteBeoordeling() {
        return laagsteBeoordeling;
    }

    public RadioButton getHoogsteBeordeling() {
        return hoogsteBeordeling;
    }

    public RadioButton getTitelNummerA_Z() {
        return titelNummerA_Z;
    }

    public RadioButton getTitelNummerZ_A() {
        return titelNummerZ_A;
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

}
