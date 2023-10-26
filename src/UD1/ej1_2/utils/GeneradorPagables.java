package UD1.ej1_2.utils;

import UD1.ej1_2.pagables.EmpleadoAsalariado;
import UD1.ej1_2.pagables.Factura;

public class GeneradorPagables {

    public static EmpleadoAsalariado generarEmpleado(int num){
        EmpleadoAsalariado e = new EmpleadoAsalariado();
        e.setNombre(ComprobadorDatos.introducirCadena("Introducir nombre del cliente nº" + num + ": "));
        e.setApellido(ComprobadorDatos.introducirCadena("Introducir apellido del cliente nº: " + num + ": "));
        e.setSeguridadSocial(ComprobadorDatos.introducirCodigoSeguridad());
        e.setSalarioSemanal(ComprobadorDatos.introducirNumeroDouble("Introducir salario con dos decimales: "));
        ComprobadorDatos.clear();
        return e;
    }

    public static Factura generarFactura(int num){
        Factura f = new Factura();
        f.setCodigo(ComprobadorDatos.introducirCodigo(num));
        f.setDescripcion(ComprobadorDatos.introducirCadena("Indicar la descripción del producto: "));
        f.setCantidad(ComprobadorDatos.introducirNumero("Indicar cantidad de productos: "));
        f.setPrecioPorProducto(ComprobadorDatos.introducirNumeroDouble("Indicar precio del producto: "));
        ComprobadorDatos.clear();
        return f;
    }


}
