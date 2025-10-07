package e1;

import java.util.ArrayList;
import java.util.List;

public class Aldea {
    public String nombreAldea;
    public int edadAldea;
    public int resistenciaMuralla;
    private int resistenciaMaxima;
    private List<Tropa> tropas;

    public Aldea(String nombre, int edad, int resistenciaMuralla) {
        this.nombreAldea = nombre;
        this.edadAldea = edad;
        this.resistenciaMuralla = resistenciaMuralla;
        this.tropas = new ArrayList<>();
    }

    protected void addTropa(Tropa nuevaTropa) {
        tropas.add(nuevaTropa);
    }

    public float calcularAtaque() {
        float ataqueTotal = 0;
        for (Tropa t : tropas) {
            ataqueTotal += t.getataque();
        }
        return ataqueTotal;
    }

    public float calcularDefensa() {
        float defensaTotal = 0;
        for (Tropa t : tropas) {
            defensaTotal += t.getdefensa();
        }
        return defensaTotal;
    }

    public String gettropas() {
        StringBuilder resultado = new StringBuilder();
        resultado.append(" ").append(tropas.size()).append("\n");
        for (Tropa t : tropas) {
            resultado.append(t.toString()).append("\n");
        }
        return resultado.toString();
    }


}
