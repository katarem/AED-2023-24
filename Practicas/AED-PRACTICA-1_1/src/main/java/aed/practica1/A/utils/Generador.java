package aed.practica1.A.utils;

import aed.practica1.A.objs.ArticulosPublicados;
import aed.practica1.A.objs.BoletinPublicado;
import aed.practica1.A.objs.RevistaPublicada;

import java.util.List;

public class Generador {

    public static List<ArticulosPublicados> generarArticulos(){
        return List.of(
                new BoletinPublicado("Reparto de dividendos","5/10/2023",300,30.95f),
                new RevistaPublicada("HobbyConsolas #120","Impreso","hobbyconsolas@gmail.com","Álvaro ALonso","Hobby",60,11.50f)
        );
    }





}
