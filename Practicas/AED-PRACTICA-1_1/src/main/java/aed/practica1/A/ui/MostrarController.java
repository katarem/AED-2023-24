package aed.practica1.A.ui;

import aed.practica1.A.utils.ListarArticulos;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MostrarController implements Initializable {

    @FXML
    private DialogPane vista;

    @FXML
    private TextArea contentArea;

    private Dialog dialog;

    public MostrarController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_A/MostrarView.fxml"));
            f.setController(this);
            f.load();
        } catch (IOException e){

        }
    }

    public void setContent(String type){
        if(type.equals("revistas")) contentArea.setText(ListarArticulos.listarRevistas());
        else contentArea.setText(ListarArticulos.listarBoletines());
    }



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialog.setDialogPane(vista);
    }


    public void show(){ dialog.show(); }


}
