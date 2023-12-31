package aed.practica1.A.ui;

import aed.practica1.A.objs.Formatos;
import aed.practica1.A.objs.RevistaPublicada;
import aed.practica1.A.utils.Alertas;
import aed.practica1.A.utils.Validador;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class RegistrarRevistaController implements Initializable {

    private Dialog<RevistaPublicada> dialog = new Dialog<>();

    @FXML
    private AnchorPane vista;

    @FXML
    private TextField titleText, autorText, editorialText, emailText, precioText, paginasText;

    @FXML
    private ChoiceBox<String> formatoChoice;


    public RegistrarRevistaController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_A/RegistrarRevistaView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ArrayList<String> valores = new ArrayList<>();
        for (Formatos f: Formatos.values()) {
            valores.add(f.name());
        }
        formatoChoice.setItems(FXCollections.observableList(valores));
        formatoChoice.setValue("Formato");
        DialogPane dp = new DialogPane();
        dp.setContent(vista);
        dp.getButtonTypes().add(ButtonType.OK);
        dialog.setDialogPane(dp);
        dialog.setResultConverter(e -> {
            var data = List.of(
                    titleText.getText(),
                    formatoChoice.getValue(),
                    emailText.getText(),
                    autorText.getText(),
                    editorialText.getText(),
                    paginasText.getText(),
                    precioText.getText()
            );
            var resultado = Validador.comprobarDatos(data,true);
            if(resultado == null){
                Alertas.error(Validador.pilaErrores.toString());
                Validador.pilaErrores.setLength(0);
                return null;
            }
            else return (RevistaPublicada) resultado;
        });

    }

    public Optional<RevistaPublicada> show() {
        return dialog.showAndWait();
    }
}
