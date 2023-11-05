package UD2.ej2_1.modelo;

public class Estudiante extends Alumnado {
    private Fecha fechaMatricula;

    public Estudiante(String dni, String nombre, int edad, Fecha fechaMatricula) {
        super(dni, nombre, edad);
        this.fechaMatricula = fechaMatricula;
    }

    public Fecha getFechaMatricula() {
        return fechaMatricula;
    }

    public void setFechaMatricula(Fecha fechaMatricula) {
        this.fechaMatricula = fechaMatricula;
    }

    @Override
    public String toString() {
        return String.format("%-15s %-20s %-7d %-7d %-7d %-7d",dni,nombre,edad,fechaMatricula.dia(),fechaMatricula.mes(),fechaMatricula.anio());
    }
}
