package aed.practica1.A.ui;

import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.utils.Validador;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Dialog;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class RegistrarBoletinController implements Initializable {

    private Dialog<BoletinPublicado> dialog = new Dialog();

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
        dialog.setResultConverter(e -> {
            var data = List.of(
                    nombreText.getText(),
                    fechaText.getText(),
                    paginasText.getText(),
                    precioText.getText()
            );
            var resultado = Validador.comprobarDatos(data,false);
            return resultado == null ? null : (BoletinPublicado) resultado;
        });
    }

    public Optional<BoletinPublicado> show(){ return dialog.showAndWait(); }

}
