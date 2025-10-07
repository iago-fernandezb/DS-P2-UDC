package e1;

public class GuerreroHacha extends TropaTeutona {
    private String nombre = "Axeman";
    public GuerreroHacha() {
        super(60, 30);
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre;
    }
}
