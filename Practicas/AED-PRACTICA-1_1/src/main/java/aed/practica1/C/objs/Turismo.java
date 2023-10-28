package aed.practica1.C.objs;

public class Turismo extends Vehiculo implements Coste{

    public static int COCHES_TOTALES = 0;

    protected double precioDia;
    protected int diasAlquiler = 0, diasDevolucion = 0;


    public Turismo(String matricula, boolean estadoAlquiler){ super(matricula, estadoAlquiler); COCHES_TOTALES++; }
    @Override
    public double importeFinal() {
        return (diasAlquiler+diasDevolucion) * precioDia;
    }

    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    @Override
    public void alquilar(int numDias) {
        alquilado = true;
        diasAlquiler = numDias;
    }

    @Override
    public boolean devolver(int numDias) {
       if(numDias>=diasAlquiler){
           diasDevolucion = numDias-diasAlquiler;
           alquilado = false;
           return true;
       }
       return false;
    }

    public boolean isAlquilado(){
        return alquilado;
    }

    public void setPrecioDia(double precioDia) {
        this.precioDia = precioDia;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }
    @Override
    public String mostrarInformacion() {
        return String.format("""
                --> Datos de un %s:
                Matrícula: %s
                Alquilado: %s
                Precio por día: %.2f €
                -------------------------------------------
                ""","Turismo", matricula, (alquilado ? "Si" : "No"), precioDia);
    }
}
