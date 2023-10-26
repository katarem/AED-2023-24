package UD1.ej1_3;

import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.List;

public class GeneradorDatos<T extends  Comparable<T>> {
    private static SecureRandom sr;

    public static void initializeRandom(){
        sr = new SecureRandom();
    }

    public static ArrayList<Character> getCharsList(){
        final ArrayList<Character> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            array.add((char)(sr.nextInt(26) + 'a'));
        }

        return array;
    }

    public static ArrayList<Integer> getNumbersList(){
        ArrayList<Integer> array = new ArrayList<>();
        for (int i = 0; i < 10; i++) { array.add(Integer.valueOf(sr.nextInt()));}
        return array;
    }

    public static ArrayList<String> getStringPatterns(){
        ArrayList<String> array = new ArrayList<>();
        final String MAP = "ABCDEFGHIJKLMNÑOPQRSTUVWXYZabcdefghijklmnñopqrstuvwxyz";
        StringBuilder pattern = new StringBuilder();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                pattern.append(MAP.charAt(sr.nextInt(52)));
            }
            array.add(pattern.toString());
            pattern = new StringBuilder();
        }
        return array;
    }






}
