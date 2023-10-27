package aed.practica1.C;

import aed.practica1.C.ui.home.HomeController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class App extends Application {

    public static Stage stage;


    public static void main( String[] args ) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        App.stage = new Stage();
        stage = App.stage;
        HomeController hc = new HomeController();
        stage.setMinWidth(600);
        stage.setMinHeight(400);
        stage.setTitle("Alquiler de Vehículos");
        stage.setScene(new Scene(hc.getView()));
        stage.show();



    }
}
