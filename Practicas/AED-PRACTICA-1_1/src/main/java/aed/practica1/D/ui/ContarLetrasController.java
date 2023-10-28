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

public class ContarLetrasController implements Initializable {

    @FXML
    private BorderPane vista;

    private File fichero;

    @FXML
    private TextField letraA, letraE, letraI, letraO, letraU, urlField;

    @FXML
    private TextArea contentArea;

    private Stage stage = new Stage();

    public ContarLetrasController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_D/ContarLetrasView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void setFichero(File f){
        this.fichero = f;
        urlField.setText(fichero.getAbsolutePath());
        contentArea.setText(Checker.getFileContent(fichero));
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        stage.setTitle("Contar letras");
        stage.setScene(new Scene(vista));
    }

    @FXML
    private void contar() {
        var content = contentArea.getText();
        int[] frecuenciaLetras = Contador.contarLetras(content);
        letraA.setText(Integer.toString(frecuenciaLetras[0]));
        letraE.setText(Integer.toString(frecuenciaLetras[1]));
        letraI.setText(Integer.toString(frecuenciaLetras[2]));
        letraO.setText(Integer.toString(frecuenciaLetras[3]));
        letraU.setText(Integer.toString(frecuenciaLetras[4]));
    }

    public void show(){
        stage.show();
    }

    @FXML
    private void exit(){
        stage.close();
    }
}
