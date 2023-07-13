package practicumopdracht;

import controllers.AlbumController;
import controllers.Controller;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

import practicumopdracht.data.*;


public class MainApplication extends Application {

 private static Stage stage;
 private static AlbumDAO albumDAO;
 private static NummerDAO nummerDAO;

 private static final String TITLE="Practicumopdracht OOP2 - %s";
 private static final int WIDTH =720;
 private static final int HEIGHTE =550;


    @Override
    public void start(Stage stage) {
        MainApplication.stage=stage;
        if (!Main.launchedFromMain) {
            System.err.println("Je moet deze applicatie opstarten vanuit de Main-class, niet de MainApplication-class!");
            System.exit(1337);

            return;
        }

        albumDAO = new TextAlbumDAO();
        nummerDAO = new TextNummerDAO();
//
//        albumDAO = new DummyAlbumDAO();
//        nummerDAO = new DummyNummerDAO();


//
//        albumDAO = new BinaryAlbumDAO();
//        nummerDAO = new ObjectNummerDAO();



        albumDAO.load();
        nummerDAO.load();



        stage.setTitle(String.format(TITLE, Main.studentNaam));
        stage.setWidth(WIDTH);
        stage.setHeight(HEIGHTE);



        stage.show();
        switchController(new AlbumController());


    }
    /**
     * dit is de switch controller methode om de views te wiselen
     *
     * @param controller waar ik de view mee geef
     */
    public static void switchController(Controller controller) {
        stage.setScene (new Scene(controller.getView().getRoot()));


    }

    public static AlbumDAO getAlbumDAO() {
        return albumDAO;
    }

    public static NummerDAO getNummerDAO() {
        return nummerDAO;
    }
}
