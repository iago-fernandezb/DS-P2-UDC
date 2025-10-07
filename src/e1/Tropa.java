package e1;

public class Tropa {
    float ataque;
    float defensa;

    public Tropa(float ataque, float defensa) {
        this.ataque = ataque;
        this.defensa = defensa;
    }

    public float getataque() {
        return ataque;
    }

    public float getdefensa() {
        return defensa;
    }

    @Override
    public String toString() {
        return "Soldiery";
    }
}
