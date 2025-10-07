package e1;

public class Falange extends TropaGala {
    private String nombre = "Phalanx";
    public Falange() {
        super(15, 40);
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre;
    }
}
