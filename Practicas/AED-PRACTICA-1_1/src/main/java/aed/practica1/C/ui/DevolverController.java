package aed.practica1.C.ui;

import aed.practica1.C.exceptions.DiasInvalidosException;
import aed.practica1.C.exceptions.MatriculaInvalidaException;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Dialog;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DevolverController implements Initializable {
    private Dialog<Vehiculo> dialog;

    @FXML
    private DialogPane view;

    @FXML
    private TextField matriculaText, tipoText, diasText;

    private String matricula;
    private int diasDevolucion;

    private Vehiculo vehiculoAlquilado;

    public DevolverController() {
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("devolver.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialog.setDialogPane(view);
        dialog.setResizable(false);
        dialog.setResult(vehiculoAlquilado);
    }

    private void checkMatricula(){
        try {
            var matriculaDada = matriculaText.getText();
            Validator.validateMatricula(matriculaDada);
            var vehiculoAlquilado = Garaje.getGaraje().stream().filter(v -> v.getMatricula().equals(matriculaDada)).findAny().orElseThrow();
            tipoText.setText(vehiculoAlquilado instanceof Turismo ? "TURISMO" : "CAMIÓN");
            matricula = matriculaDada; //llegamos aquí, significa que orElseThrows no nos ha echado del try
        } catch(MatriculaInvalidaException e){
            showError(e, "Matrícula inválida");
        } catch(NoSuchElementException e){
            var e2 = new MatriculaInvalidaException("Ese vehículo no existe en la empresa.");
            showError(e2,"Vehículo inexistente");
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }

    private void checkDias(){
        try{
            Validator.validateDiasAlquiler(diasText.getText());
            diasDevolucion = Integer.parseInt(diasText.getText());
            vehiculoAlquilado.devolver(diasDevolucion);
        } catch(DiasInvalidosException e){
            showError(e,"Número de días inválido");
        }
    }


    private void showError(Exception e, String title){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }


    public Optional<Vehiculo> show(){ return dialog.showAndWait(); }


}
