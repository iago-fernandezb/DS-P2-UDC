package e1;

public class EquitesImperatoris extends TropaRomana {
    private String nombre = "Equites Imperatoris";
    public EquitesImperatoris(){
        super(120,65);
    }
    @Override
    public String toString() {
        return super.toString() + " - " + nombre;
    }
}
