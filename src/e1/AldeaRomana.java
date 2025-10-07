package e1;

public class AldeaRomana extends Aldea {
    public AldeaRomana(String nombre, int edad, int resistenciaMuralla){
        super(nombre, edad, resistenciaMuralla);
    }

    public void addTropaRomana(Tropa nuevaTropa) {
        if (nuevaTropa instanceof Legionario || nuevaTropa instanceof Pretoriano || nuevaTropa instanceof EquitesImperatoris) {
            addTropa(nuevaTropa);
        } else {
            System.out.println("Solo puedes añadir tropas romanas a esta aldea.");
        }
    }

    @Override
    public float calcularAtaque() {
        return super.calcularAtaque()*(float)1.1;
    }

    @Override
    public float calcularDefensa() {
        int resistencia = this.resistenciaMuralla;
        return super.calcularDefensa()+(resistencia*(float)2);
    }
}
