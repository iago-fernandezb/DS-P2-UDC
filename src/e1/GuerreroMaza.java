package e1;

public class GuerreroMaza extends TropaTeutona {
    private String nombre = "Maceman";
    private boolean mazaMetal;
    public GuerreroMaza(boolean mazaMetal) {
        super(mazaMetal ? (float) (40 * 1.25) : 40, 20);
        this.mazaMetal = mazaMetal;
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre + (mazaMetal ? " with iron mace" : "");
    }
}