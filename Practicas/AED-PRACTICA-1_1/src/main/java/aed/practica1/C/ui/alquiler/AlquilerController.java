package aed.practica1.C.ui.alquiler;

import aed.practica1.C.exceptions.MatriculaInvalidaException;
import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Validator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

public class AlquilerController implements Initializable {

    private Dialog<Vehiculo> dialog = new Dialog<>();

    @FXML
    private DialogPane vista;

    @FXML
    private Label titleLabel, matriculaLabel;

    @FXML
    private TextField matriculaText, diasText;

    @FXML
    private Button diasButton;

    private String matricula;
    private int diasAlquiler;

    private boolean isCoche;

    private List<Vehiculo> alquilerList;

    private Turismo coche;
    private Camion camion;
    public AlquilerController(){
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_C/AlquilerView.fxml"));
            f.setController(this);
            f.load();
        } catch(IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        dialog.setDialogPane(vista);
        dialog.setResizable(false);
        dialog.setResultConverter(e -> isCoche ? coche : camion);
    }
    public DialogPane getView() {
        return this.vista;
    }

    public void setOption(String option){
        matriculaLabel.setText("INDICAR MATRÍCULA DEL " + option.toUpperCase() + " CONTRATADO:");
        titleLabel.setText("ALQUILER DE " + option.toUpperCase() + ":");
        isCoche = option.equals("coches");
        alquilerList = getCochesOCamiones();
    }

    @FXML
    private void checkMatricula(){
        var garaje = getCochesOCamiones();
        try{
            Validator.validateMatricula(matriculaText.getText());
            Optional<Vehiculo> optionalVehiculo = alquilerList.stream().filter(e -> e.getMatricula().equals(matriculaText.getText())).findAny();
            if(optionalVehiculo.isEmpty()) throw new MatriculaInvalidaException("No existe ese " + (isCoche ? "coche" : "camión") + " en nuestra tienda");

            if(isCoche) {
                coche = (Turismo) optionalVehiculo.get();
                if(coche.isAlquilado()) throw new MatriculaInvalidaException("El coche elegido está alquilado");
            }
            else {
                camion = (Camion) optionalVehiculo.get();
                if(camion.isAlquilado()) throw new MatriculaInvalidaException("El camión elegido está alquilado");
            }

            diasText.setDisable(false);
            diasButton.setDisable(false);
        } catch(MatriculaInvalidaException e){
            showError(e, "Matrícula inválida");
        }
    }

    @FXML
    private void checkDiasAlquiler(){
        try {
            diasAlquiler = Validator.validateDiasAlquiler(diasText.getText());
            if (isCoche) coche.alquilar(diasAlquiler);
            else camion.alquilar(diasAlquiler);
        } catch(NumberFormatException e){
            showError(e, "Días inválidos");
        }
    }

    private void showError(Exception e, String title){
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setHeaderText(title);
        a.setContentText(e.getMessage());
        a.showAndWait();
    }

    public Optional<Vehiculo> deposito(){
        return dialog.showAndWait();
    }

    private List<Vehiculo> getCochesOCamiones(){
        var garaje = Garaje.getGaraje();
        if(isCoche) return garaje.stream().filter(e -> e instanceof Turismo).toList();
        else return garaje.stream().filter(e -> e instanceof Camion).toList();
    }




}
