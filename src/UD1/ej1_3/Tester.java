package UD1.ej1_3;

import java.util.ArrayList;

public class Tester {
    private static void testStrings(){
        ArrayList<String> arrayStrings = GeneradorDatos.getStringPatterns();
        arrayStrings.forEach(e -> System.out.print(e + " "));
        ImplementaMinimoMaximo<String> i = new ImplementaMinimoMaximo<>(arrayStrings);
        System.out.println("\n[i] Valor máximo del conjunto de cadenas: " + i.maximo());
        System.out.println("[i] Valor mínimo del conjunto de cadenas: " + i.minimo());
        finishLine();
    }
    private static void finishLine(){
        System.out.println("---------------------------------------------------------");
    }
    private static void testNumbers(){
        ArrayList<Integer> arrayNumeros = GeneradorDatos.getNumbersList();
        arrayNumeros.forEach(e -> System.out.print(e + " "));
        ImplementaMinimoMaximo<Integer> j = new ImplementaMinimoMaximo<>(arrayNumeros);
        System.out.println("\n[i] Valor máximo del conjunto de enteros: " + j.maximo());
        System.out.println("[i] Valor mínimo del conjunto de enteros: " + j.minimo());
        finishLine();
    }

    private static void testCharacters(){
        ArrayList<Character> arrayChars = GeneradorDatos.getCharsList();
        arrayChars.forEach(e -> System.out.print(e + " "));
        ImplementaMinimoMaximo<Character> k = new ImplementaMinimoMaximo<>(arrayChars);
        System.out.println("\n[i] Valor máximo del conjunto de caracteres: "+ k.maximo());
        System.out.println("[i] Valor mínimo del conjunto de caracteres: " + k.minimo());
        finishLine();

    }
    public static void test(){
        GeneradorDatos.initializeRandom();
        testNumbers();
        testCharacters();
        testStrings();
    }
}
