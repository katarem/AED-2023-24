package aed.practica1.A.utils;

import aed.practica1.A.objs.ArticulosPublicados;

import java.util.ArrayList;
import java.util.List;

public class Almacen {

    private static List<ArticulosPublicados> almacen = new ArrayList<>();

    public static void addAll(List<ArticulosPublicados> articulosPublicados){ almacen.addAll(articulosPublicados);}

    public static List<ArticulosPublicados> getAlmacen() {
        return almacen;
    }

    public static void add(ArticulosPublicados a){
        almacen.add(a);
    }

}

