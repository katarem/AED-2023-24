package UD1.ej1_2;

import UD1.ej1_2.pagables.*;
import UD1.ej1_2.utils.ComprobadorDatos;
import UD1.ej1_2.utils.GeneradorPagables;

import java.util.ArrayList;

public class Ej1_2Main {

    public static void main(String[] args) {
        ComprobadorDatos.abrirFlujo();
        ArrayList<Pagable> almacen = new ArrayList<>();
        for (int i = 0; i < 6; i++) {
            if (i < 3) almacen.add(GeneradorPagables.generarFactura(i+1)); //Facturas
            else almacen.add(GeneradorPagables.generarEmpleado(i-2));  //Empleado -> Como en la ejecución introducen salario se sobreentiende que son EmpleadoAsalariado
        }
        ComprobadorDatos.cerrarFlujo();
        mostrarInfo(almacen);
    }

    private static void mostrarInfo(ArrayList<Pagable> array){
        array.forEach(e -> {
                System.out.println(e.toString());
                System.out.printf("--> Importe de factura: %,.2f€ (Eur)\n",e.obtenerImportePagable());
        });

    }

}
