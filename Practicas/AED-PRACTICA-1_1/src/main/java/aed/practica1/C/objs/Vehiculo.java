package aed.practica1.C.objs;

public abstract class Vehiculo {

    protected String matricula;
    protected boolean alquilado;

    public Vehiculo(String matricula, boolean estadoAlquiler){ this.matricula = matricula; this.alquilado = estadoAlquiler; }

    public abstract void alquilar(int numDias);
    public abstract boolean devolver(int numDias);

    public String getMatricula(){ return this.matricula; }

    public abstract String mostrarInformacion();






}
