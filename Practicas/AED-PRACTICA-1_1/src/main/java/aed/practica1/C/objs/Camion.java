package aed.practica1.C.objs;

public class Camion extends Vehiculo implements Coste{

    public static int CAMIONES_TOTALES = 0;

    protected double precioDia;
    protected int diasAlquiler = 0, diasDevolucion = 0;
    public Camion(String matricula, double precioDia, boolean estadoAlquiler) { super(matricula, estadoAlquiler); this.precioDia = precioDia; CAMIONES_TOTALES++;}

    //Constrructor pedido
    public Camion(String matricula, boolean estadoAlquiler) { super(matricula, estadoAlquiler); }

    public Camion(String matricula, double precioDia) { super(matricula, false); this.precioDia = precioDia; CAMIONES_TOTALES++;}

    public Camion(String matricula, int diasAlquiler) { this(matricula, true); this.diasAlquiler = diasAlquiler;}


    public void setDiasAlquiler(int diasAlquiler) {
        this.diasAlquiler = diasAlquiler;
    }

    public int getDiasAlquiler() {
        return diasAlquiler;
    }

    public int getDiasDevolucion() {
        return diasDevolucion;
    }

    @Override
    public double importeFinal() {
        return (diasAlquiler+diasDevolucion)*precioDia;
    }

    @Override
    public void alquilar(int numDias) {
        alquilado = true;
        diasAlquiler = numDias;
        System.out.printf("El importe final será de %.2f € (Eur)",importeFinal());
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

    public double getPrecioDia(){
        return precioDia;
    }

    public void setPrecioDia(double precioDia){
        this.precioDia = precioDia;
    }



    @Override
    public String mostrarInformacion() {
        return String.format("""
                --> Datos de un %s:
                Matrícula: %s
                Alquilado: %s
                Precio por día: %.2f €
                ------------------------------
                ""","Camión", matricula, (alquilado ? "Si" : "No"), precioDia);
    }
}
