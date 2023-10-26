package UD1.ej1_1;

import UD1.ej1_1.clientes.Cliente;
import UD1.ej1_1.clientes.Empleado;
import UD1.ej1_1.clientes.Estudiante;
import UD1.ej1_1.clientes.Facultativo;
import UD1.ej1_1.exceptions.CodigoFueraDeRangoException;
import UD1.ej1_1.exceptions.NombreInvalidoException;

import java.util.*;

public class Ej1Main {

    static Scanner sc;
    private static Cliente generarCliente(String tipoCliente, int numCliente) {

        boolean continuar = false;
        Cliente client;
        String nombre = "";
        int codigo = 0;

        //Determinamos el tipo de cliente que devolveremos
        client = switch (tipoCliente) {
            case "estudiante" -> new Estudiante();
            case "empleado" -> new Empleado();
            case "facultativo" -> new Facultativo();
            default -> new Cliente();
        };

        //Creamos un bucle que permita el programa reiniciarse hasta tener los datos de forma correcta
        while (!continuar) {
            try {
                System.out.print("--> Introducir nombre del " + tipoCliente + " nº " + numCliente + ": ");
                nombre = sc.nextLine();
                for (char letra : nombre.toCharArray()) {
                    if (Character.isDigit(letra))
                        throw new NombreInvalidoException("[!] Las cadenas no admiten dígitos.");
                    else if (letra != ' ' && !Character.isLetter(letra))
                        throw new NombreInvalidoException("[!] Las cadenas no admiten símbolos.");
                }
                //Si llegamos aquí, es que el nombre es correcto.
                continuar = true;
            } catch (NombreInvalidoException e) {
                System.out.print(e.toString());
            }
            if (!continuar) {
                //Limpiamos buffer
                System.out.print(" <<Pulsar ENTER para continuar>>");
                sc.nextLine();
            }
        }
        continuar = false;
        if(!(client instanceof Estudiante) && !(client instanceof Empleado)){
            while (!continuar) {
                try {
                    System.out.print("--> Indicar código: ");
                    codigo = sc.nextInt();
                    sc.nextLine();
                    if (codigo < 100 || codigo > 300) {
                        throw new CodigoFueraDeRangoException();
                    }
                    //Si superamos esto, es que es válido
                    continuar = true;
                } catch (CodigoFueraDeRangoException e) {
                    System.out.print(e.toString());
                } catch (InputMismatchException e){
                    System.out.print("[!] Sólo puede ser un número entre 100 y 300");
                }
                if (!continuar) {
                    //Limpiamos buffer
                    sc.nextLine();
                    System.out.print(" <<Pulsar ENTER para continuar>>");
                    sc.nextLine();
                    }
                }
            client.setCodigo(codigo);
        }
        //Añadimos antes el código para evitar conflictos cuando intentemos agregar cualquier otro tipo de cliente
        client.setNombre(nombre);
        return client;
    }
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        ArrayList<Cliente> clientesList = new ArrayList<>();
        //3 clientes, 3 estudiantes 3 empleados 3 facultativos
        //Clientes
        for (int i = 0; i < 3; i++) {
            Cliente client = generarCliente("cliente",i+1);
            clientesList.add(client);
        }
        //Estudiantes
        for (int i = 0; i < 3; i++) {
            Cliente client = generarCliente("estudiante",i+1);
            clientesList.add(client);
        }
        //Empleados
        for (int i = 0; i < 3; i++) {
            Cliente client = generarCliente("empleado",i+1);
            clientesList.add(client);
        }
        //Facultativo
        for (int i = 0; i < 3; i++) {
            Cliente client = generarCliente("facultativo",i+1);
            clientesList.add(client);
        }
        sc.close();
        System.out.println("***********ACCESO A DATOS************");
        clientesList.forEach(e -> System.out.println("[i] " + e));
        System.out.println("*************************************");


    }
}
