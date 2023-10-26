package UD1.ej1_1.clientes;

public class Estudiante extends Cliente{
    public final int TITULADO = 1;
    public final int TEMPORAL = 2;

    @Override
    public String toString(){
        return "Estudiante: " + this.nombre;
    }
}
