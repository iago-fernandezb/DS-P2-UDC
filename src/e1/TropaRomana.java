package e1;


public class TropaRomana extends Tropa {
    private String nombre = "Roman ";
    public TropaRomana(float ataque, float defensa){
        super(ataque,defensa);
    }

    public String toString() {
        return nombre +super.toString();
    }
}
