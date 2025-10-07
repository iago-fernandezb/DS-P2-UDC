package e1;


public class AldeaTeutona extends Aldea {
    public AldeaTeutona(String nombre, int edad, int resistenciaMuralla){
        super(nombre, edad, resistenciaMuralla);
    }
    public void addTropaTeutona(Tropa nuevaTropa) {
        if (nuevaTropa instanceof GuerreroHacha || nuevaTropa instanceof GuerreroMaza || nuevaTropa instanceof Paladin) {
            addTropa(nuevaTropa);
        } else {
            System.out.println("Solo puedes añadir tropas teutonas a esta aldea.");
        }
    }

    @Override
    public float calcularAtaque() {
        return super.calcularAtaque()*(float)0.95;
    }

    @Override
    public float calcularDefensa() {
        int resistencia = this.resistenciaMuralla;
        return super.calcularDefensa()+resistencia;
    }
}
