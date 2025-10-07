package e1;

public class RayoTeutates extends TropaGala {
    private String nombre = "Theutates Thunder";
    private boolean armaduraPesada;
    public RayoTeutates(boolean armaduraPesada) {
        super(armaduraPesada ? (float) (100 * 0.75) : 100, armaduraPesada ? (float) (25 * 1.25) : 25);
        this.armaduraPesada = armaduraPesada;
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre + (armaduraPesada ? " with heavy armor" : "");
    }
}
