package aed.practica1.A.utils;

import aed.practica1.A.objs.ArticulosPublicados;

import java.util.ArrayList;
import java.util.List;

public class Almacen {

    private static List<ArticulosPublicados> almacen;

    public static void setAlmacen(List<ArticulosPublicados> almacen) {
        Almacen.almacen = almacen;
    }

    public static List<ArticulosPublicados> getAlmacen() {
        return almacen;
    }

    public static void add(ArticulosPublicados a){
        almacen.add(a);
    }

}

