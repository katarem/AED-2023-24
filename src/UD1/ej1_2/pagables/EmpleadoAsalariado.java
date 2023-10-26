package UD1.ej1_2.pagables;

public class EmpleadoAsalariado extends Empleado{

    private double salarioSemanal;

    public double getSalarioSemanal() {
        return salarioSemanal;
    }

    public void setSalarioSemanal(double salarioSemanal) {
        this.salarioSemanal = salarioSemanal;
    }

    @Override
    public double obtenerImportePagable() {
        return salarioSemanal;
    }

    @Override
    public String toString() {
        return String.format("%s\n--> Salario semanal: %,.2f€ (Eur)",super
                .toString(),salarioSemanal);
    }
}
