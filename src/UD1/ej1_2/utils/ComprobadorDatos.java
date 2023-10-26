package UD1.ej1_2.utils;

import UD1.ej1_1.exceptions.CodigoFueraDeRangoException;
import UD1.ej1_1.exceptions.NumeroInvalidoException;
import UD1.ej1_2.exceptions.CadenaInvalidaException;
import UD1.ej1_2.exceptions.CodigoSeguridadInvalidoException;

import java.security.SecureRandom;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ComprobadorDatos {

    private static Scanner sc;

    public static void abrirFlujo(){
        sc = new Scanner(System.in);
    }

    public static void cerrarFlujo(){ sc.close(); }


    public static String introducirCadena(String mensaje){
        String cadena = "";
        boolean continuar = false;
        while (!continuar) {
            try {
                System.out.print("--> " + mensaje);
                cadena = sc.nextLine();
                for (char letra : cadena.toCharArray()) {
                    if (Character.isDigit(letra))
                        throw new CadenaInvalidaException("[!] Las cadenas no admiten dígitos.");
                    else if (letra != ' ' && !Character.isLetter(letra))
                        throw new CadenaInvalidaException("[!] Las cadenas no admiten símbolos.");
                }
                //Si llegamos aquí, es que el nombre es correcto.
                continuar = true;
                //Aquí no hago uso de cleanBuffer porque al usar nextLine tenemos ya un retorno de carro,
                // tampoco veo necesario un método más para esto
            } catch (CadenaInvalidaException e) {
                System.out.print(e.toString());
                System.out.print(" Pulse <Enter> para continuar.");
                sc.nextLine();
            } catch (Exception e){
                System.out.print("[!] Error inesperado: " + e.toString());
                System.out.print(" Pulse <Enter> para continuar.");
                sc.nextLine();
            }
        }
        return cadena;
    }

    public static String introducirCodigo(int num){
        int codigo = 0;
        boolean continuar = false;
        while (!continuar) {
            try {
                System.out.print("--> Indicar código de 3 dígitos [factura nº" + num + "]: ");
                codigo = sc.nextInt();
                if (codigo < 100 || codigo > 300) {
                    throw new CodigoFueraDeRangoException();
                }
                //Si superamos esto, es que es válido
                sc.nextLine();
                continuar = true;
            } catch (CodigoFueraDeRangoException e) {
                System.out.print(e.toString());
                clearBuffer();
            } catch (InputMismatchException e){
                System.out.print("[!] Debe ser un número entero entre 100 y 300.");
                clearBuffer();
            } catch (Exception e){
                System.out.print("[!] Error inesperado: " + e.toString());
                clearBuffer();
            }
        }
        SecureRandom sr = new SecureRandom();
        codigo += sr.nextInt(1000000000);
        return String.valueOf(codigo);
    }


    public static int introducirNumero(String mensaje){
        int numero = 0;
        boolean continuar = false;
        while (!continuar) {
            try {
                System.out.print("--> " + mensaje);
                numero = sc.nextInt();
                if (numero < 0) {
                    throw new NumeroInvalidoException();
                }
                //Si superamos esto, es que es válido
                continuar = true;
            } catch (NumeroInvalidoException e) {
                System.out.print(e.toString());
                clearBuffer();
            } catch(InputMismatchException e){
                System.out.print("[!] Debe ser un número entero positivo");
                clearBuffer();
            } catch(NoSuchElementException e){
                System.out.print("[!] No se puede dejar el campo vacío.");
                clearBuffer();
            } catch (Exception e){
                System.out.print("[!] Error inesperado: " + e.getMessage());
                clearBuffer();
            }
        }
        return numero;
    }

    public static double introducirNumeroDouble(String mensaje){
        double codigo = 0;
        boolean continuar = false;
        while (!continuar) {
            try {
                System.out.print("--> " + mensaje);
                codigo = sc.nextDouble();
                if (codigo < 0) {
                    throw new NumeroInvalidoException();
                }
                //Si superamos esto, es que es válido
                continuar = true;
            } catch (NumeroInvalidoException e) {
                System.out.print(e.toString());
                clearBuffer();
            } catch (InputMismatchException e) {
                System.out.print("[!] Sólo pueden ser números positivos.");
                clearBuffer();
            } catch(NoSuchElementException e){
                System.out.print("[!] No se puede dejar el campo vacío.");
                clearBuffer();
            } catch(Exception e){
                System.out.print("[!] Error inesperado: " + e.getMessage());
                System.exit(1);
            }
        }
        return codigo;
    }

    public static String introducirCodigoSeguridad(){
        boolean continuar = false;
        String codigo = "";
        Pattern patron = Pattern.compile("\\d{3}-\\d{2}-\\d{4}");
        Matcher matcher;
            while (!continuar) {
                try {
                    System.out.print("--> Indicar número de la Seguridad Social en formato XXX-XX-XXXX: ");
                    codigo = sc.next();
                    matcher = patron.matcher(codigo);
                    if(!matcher.matches())
                        throw new CodigoSeguridadInvalidoException();
                    continuar = true;
                } catch(CodigoSeguridadInvalidoException e){
                    System.out.print(e.toString());
                    clearBuffer();
                } catch(NoSuchElementException e){
                    System.out.print("[!] El campo no puede estar vacío.");
                    clearBuffer();
                } catch (IllegalStateException e ){
                    System.out.print("[!] El flujo de datos está cerrado");
                    System.exit(1);
                } catch (Exception e){
                    System.out.print("[!] Error inesperado: " + e.toString());
                    System.exit(1);
                }
            }
            System.out.printf("[ %s ] es un código válido.",String.valueOf(codigo));
            clearBuffer();
            return codigo;
    }

    private static void clearBuffer(){
        sc.nextLine();
        System.out.print(" Pulse <Enter> para continuar.");
        sc.nextLine();
    }

    public static void clear(){
        sc.nextLine();
    }
}







