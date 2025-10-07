package e1;

public class Paladin extends TropaTeutona {
    private String nombre = "Paladin";
    public Paladin() {
        super(55, 100);
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre;
    }
}
