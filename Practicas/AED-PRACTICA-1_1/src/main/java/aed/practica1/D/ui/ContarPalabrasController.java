package aed.practica1.D.ui;

import aed.practica1.D.utils.Checker;
import aed.practica1.D.utils.Contador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ContarPalabrasController implements Initializable {

    @FXML
    private BorderPane vista;

    @FXML
    private TextField palabrasView, urlField;

    @FXML
    private TextArea contentArea;

    private File fichero;

    private Stage stage = new Stage();
    public ContarPalabrasController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_D/ContarPalabrasView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setTitle("Contar Palabras");
        stage.setScene(new Scene(vista));

    }

    public void setFichero(File f){
        this.fichero = f;
        urlField.setText(fichero.getAbsolutePath());
        contentArea.setText(Checker.getFileContent(fichero));
    }

    public void show(){
        stage.show();
    }

    @FXML
    private void contar() {
        var content = contentArea.getText();
        palabrasView.setText(Integer.toString(Contador.contarPalabras(content)));

    }

    @FXML
    private void exit(){
        stage.close();
    }
}
