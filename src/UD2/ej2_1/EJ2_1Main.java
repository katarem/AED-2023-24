package UD2.ej2_1;

import UD2.ej2_1.utilidades.CSVReader;

public class EJ2_1Main {
    public static void main(String[] args) {
        //CONFIGURAMOS RUTAS
        CSVReader.CSVPATH = "./src/resources/personas.csv";
        CSVReader.BINARIOPATH = "/home/rem/Desktop/binario.dat";
        CSVReader.readCSV();
        CSVReader.writeBinario();
        CSVReader.readBinario();
    }





}
