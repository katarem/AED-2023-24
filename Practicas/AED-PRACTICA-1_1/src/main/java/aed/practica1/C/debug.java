package aed.practica1.C;

import aed.practica1.C.objs.Camion;
import aed.practica1.C.objs.Vehiculo;
import aed.practica1.C.utils.Listar;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;

public class debug {

    public static void main(String[] args) {
        var list = Listar.generarDatos(3);
        ObservableList<Vehiculo> oList = FXCollections.observableArrayList(list);
        oList.addListener(new ListChangeListener<Vehiculo>() {
            @Override
            public void onChanged(Change<? extends Vehiculo> change) {
                if(change.wasPermutated()) System.out.println("CAMBIO");
            }
        });
        list.add(new Camion("AA-1234-BBB",20));
    }


}
