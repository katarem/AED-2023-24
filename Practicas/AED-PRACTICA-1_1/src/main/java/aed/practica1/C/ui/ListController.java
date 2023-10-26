package aed.practica1.C.ui;

import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Listar;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextArea;
import javafx.stage.Modality;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ListController implements Initializable {

    private Dialog dialog;
    @FXML
    private DialogPane vista;
    @FXML
    private TextArea listText;

    private StringBuilder sb = new StringBuilder();

    public ListController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_C/ListaView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void update(){
        var garaje = Garaje.getGaraje();
        sb = new StringBuilder();
        sb.append("------------------------------\n");
        garaje.forEach(v -> sb.append(v.mostrarInformacion()));
        listText.setText(sb.toString() + Listar.mostrar());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        try{
            dialog = new Dialog();
            vista.getButtonTypes().add(ButtonType.CANCEL);
            dialog.setDialogPane(vista);
            dialog.initModality(Modality.NONE);
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    public void show(){
        dialog.show();
    }
}
