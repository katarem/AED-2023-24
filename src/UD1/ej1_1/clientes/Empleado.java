package UD1.ej1_1.clientes;

import java.util.Calendar;

public class Empleado extends  Cliente{

    protected String turno;
    protected float salario;
    protected Calendar fechaContrato;

    @Override
    public String toString(){
        return "Empleado: " + this.nombre;
    }

}
