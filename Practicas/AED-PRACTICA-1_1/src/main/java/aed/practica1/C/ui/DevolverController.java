package aed.practica1.C.ui;

import aed.practica1.C.exceptions.AlquilerNoAcabadoException;
import aed.practica1.C.exceptions.DiasInvalidosException;
import aed.practica1.C.exceptions.MatriculaInvalidaException;
import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Turismo;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Garaje;
import aed.practica1.C.utils.Listar;
import aed.practica1.C.utils.Validador;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.io.IOException;
import java.net.URL;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.ResourceBundle;

public class DevolverController implements Initializable {
    private final Dialog<Vehiculo> dialog = new Dialog<>();

    @FXML
    private DialogPane vista;

    @FXML
    private TextField matriculaText, tipoText, diasText;

    @FXML
    private Label alquiladosText;
    @FXML
    private Button diasButton, matriculaBuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuuu;

    private String matricula;
    private int diasDevolucion;

    private boolean isCoche = true;

    private Vehiculo vehiculoAlquilado;


    private Turismo turismo;
    private Camion camion;

    public DevolverController() {
        try{
            FXMLLoader f = new FXMLLoader(ClassLoader.getSystemResource("1_C/DevolverView.fxml"));
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
        dialog.setResultConverter(e -> vehiculoAlquilado);
    }

    @FXML
    private void checkMatricula(){
        try {
            var matriculaDada = matriculaText.getText();
            Validador.validateMatricula(matriculaDada);
            vehiculoAlquilado = Garaje.getGaraje().stream().filter(v -> v.getMatricula().equals(matriculaDada)).findAny().orElseThrow();
            isCoche = vehiculoAlquilado instanceof Turismo;
            int diasAlquiler;
            if(isCoche){
                turismo = (Turismo) vehiculoAlquilado;
                if(!turismo.isAlquilado()) throw new MatriculaInvalidaException("El vehículo no está alquilado");
                diasAlquiler = turismo.getDiasAlquiler();
            } else{
                camion = (Camion) vehiculoAlquilado;
                if(!camion.isAlquilado()) throw new MatriculaInvalidaException("El vehiculo no está alquilado");
                diasAlquiler = camion.getDiasAlquiler();
            }
            tipoText.setText(isCoche ? "TURISMO" : "CAMIÓN");
            matricula = matriculaDada; //llegamos aquí, significa que orElseThrows no nos ha echado del try

            alquiladosText.setText(alquiladosText.getText() + " " + diasAlquiler);
            diasButton.setDisable(false);
            diasText.setDisable(false);

        } catch(MatriculaInvalidaException e){
            showError(e, "Matrícula inválida");
        } catch(NoSuchElementException e){
            var e2 = new MatriculaInvalidaException("Ese vehículo no existe en la empresa.");
            showError(e2,"Vehículo inexistente");
        } catch(Exception e){
            throw new RuntimeException(e);
        }
    }
    @FXML
    private void checkDias() {
        try {
            diasDevolucion = Validador.validateDias(diasText.getText());
            if (isCoche) {
                Listar.contCochesAlquilados--;
                alquiladosText.setText("Días alquiler: " + turismo.getDiasAlquiler());
            }
            else{
                Listar.contCamionesAlquilados--;
                alquiladosText.setText("Días alquiler: " + camion.getDiasAlquiler());
            }
            if (!vehiculoAlquilado.devolver(diasDevolucion)) throw new AlquilerNoAcabadoException("Aún no ha acabado el periodo de alquiler");
        } catch (DiasInvalidosException | NoSuchElementException e) {
            showError(e, "Número de días inválido");
        } catch(AlquilerNoAcabadoException e){
            showError(e, "ALquiler no vencido");
        } catch(Exception e){
            throw new RuntimeException(e);
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
