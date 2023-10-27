package aed.practica1.A.utils;

import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.objs.RevistaPublicada;

public class ListarArticulos {

    public static String listarRevistas(){
        var sb = new StringBuilder();
        Almacen.getAlmacen().stream().filter(articulo -> articulo instanceof RevistaPublicada).forEach(sb::append);
        return sb.toString();
    }
    public static String listarBoletines(){
        var sb = new StringBuilder();
        Almacen.getAlmacen().stream().filter(articulo -> articulo instanceof BoletinPublicado).forEach(sb::append);
        return sb.toString();
    }

}
