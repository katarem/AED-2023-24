package UD1.ej1_1.clientes;

public class Facultativo extends Empleado{
    public final int TECNICO = 1;
    public final int LICENCIADO = 2;
    protected String horario;
    protected int especialidad;

    @Override
    public String toString(){
        return "Facultativo: " + this.nombre;
    }

}
