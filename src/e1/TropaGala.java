package e1;

public class TropaGala extends Tropa {
    private String nombre = "Gauls ";
    public TropaGala(float ataque, float defensa){
        super(ataque,defensa);
    }
    @Override
    public String toString() {
        return nombre +super.toString();
    }
}
