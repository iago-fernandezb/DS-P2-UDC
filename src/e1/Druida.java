package e1;

public class Druida extends TropaGala {
    private String nombre = "Druidrider";
    public Druida(){
        super(45, 115);
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre;
    }
}
