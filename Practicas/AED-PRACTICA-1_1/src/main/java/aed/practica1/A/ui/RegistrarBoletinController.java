package aed.practica1.A.ui;

import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.utils.Alertas;
import aed.practica1.A.utils.Validador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarBoletinController implements Initializable {

    private Dialog<BoletinPublicado> dialog = new Dialog();

    @FXML
    private AnchorPane vista;

    @FXML
    private TextField nombreText, fechaText, paginasText, precioText;


    public RegistrarBoletinController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_A/RegistrarBoletinView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        DialogPane dp = new DialogPane();
        dp.setContent(vista);
        dp.getButtonTypes().add(ButtonType.OK);
        dialog.setDialogPane(dp);
        dialog.setResultConverter(e -> {
            var data = List.of(
                    nombreText.getText(),
                    fechaText.getText(),
                    paginasText.getText(),
                    precioText.getText()
            );
            var resultado = Validador.comprobarDatos(data,false);
            if(resultado == null){
                Alertas.error(Validador.pilaErrores.toString());
                Validador.pilaErrores.setLength(0);
                return null;
            }
            else return (BoletinPublicado) resultado;
        });
    }

    public Optional<BoletinPublicado> show(){ return dialog.showAndWait(); }

}
