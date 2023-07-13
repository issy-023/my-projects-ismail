package views;

import javafx.scene.Parent;


public abstract class View {

    private Parent root;

    public Parent getRoot() {
        return root;
    }



    protected abstract Parent initializeView();

}


