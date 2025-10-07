package e1;

public class TropaTeutona extends Tropa {
    private String nombre = "Teutons ";
    public TropaTeutona(float ataque, float defensa){
        super(ataque,defensa);
    }

    public String toString() {
        return nombre +super.toString();
    }
}
