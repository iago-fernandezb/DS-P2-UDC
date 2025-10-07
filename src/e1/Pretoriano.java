package e1;

public class Pretoriano extends TropaRomana {
    private String nombre = "Pretorian";
    private boolean armadura;
    public Pretoriano(boolean armadura){
        super(30, armadura ? (float) (65 * 1.1) : 65);
        this.armadura = armadura;
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre + (armadura ? " with armor" : "");
    }
}
