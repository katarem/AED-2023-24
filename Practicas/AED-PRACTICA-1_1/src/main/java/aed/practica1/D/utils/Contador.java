package aed.practica1.D.utils;

import java.text.Normalizer;
import java.util.Arrays;

public class Contador {


    public static int[] contarLetras(String content){
        char[] vocales = {'a','e','i','o','u'};
        int[] recuento = {0,0,0,0,0};
        Normalizer.normalize(content,Normalizer.Form.NFD); //para que también cuenten con acento
        for (char letra: content.toCharArray()) {
            for (int i = 0; i < vocales.length; i++) {
                if(vocales[i]==letra) recuento[i] += 1;
            }
        }
        return recuento;
    }

    public static int contarPalabras(String content){
        return (int)Arrays.stream(content.split(" ")).count();
    }







}
