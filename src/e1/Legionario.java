package e1;

public class Legionario extends TropaRomana {
    private String nombre = "Legionary";
    public Legionario(){
        super(40,35);
    }
    @Override
    public String toString() {
        return super.toString() +" - " + nombre;
    }
}
