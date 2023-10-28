package aed.practica1.D.ui;

import aed.practica1.C.App;
import aed.practica1.D.utils.Alerts;
import aed.practica1.D.utils.Checker;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {

    @FXML
    private BorderPane vista;


    private File ficheroSeleccionado;


    public HomeController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_D/HomeView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    @FXML
    private void exit(){
        System.exit(0);
    }

    @FXML
    private void seleccionarFichero(){
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Elige un archivo o crea uno nuevo.");
        chooser.setInitialDirectory(Checker.getDefaultDirectory());
        File f = chooser.showOpenDialog(App.stage);
        if(Checker.isFileCreated(f)){
            ficheroSeleccionado = f;
            Alerts.info("Archivo seleccionado con éxito",f.getAbsolutePath());
        }
    }

    @FXML
    private void letrasScreen(){
        ContarLetrasController c = new ContarLetrasController();
        c.setFichero(ficheroSeleccionado);
        c.show();
    }

    @FXML
    private void palabrasScreen(){
        ContarPalabrasController p = new ContarPalabrasController();
        p.setFichero(ficheroSeleccionado);
        p.show();
    }


    public BorderPane getView() {
        return vista;
    }
}
