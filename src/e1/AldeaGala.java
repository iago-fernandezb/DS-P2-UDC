package e1;


public class AldeaGala extends Aldea {
    public AldeaGala(String nombre, int edad, int resistenciaMuralla){
        super(nombre, edad, resistenciaMuralla);
    }

    public void addTropaGala(Tropa nuevaTropa) {
        if (nuevaTropa instanceof Falange || nuevaTropa instanceof RayoTeutates || nuevaTropa instanceof Druida) {
            addTropa(nuevaTropa);
        } else {
            System.out.println("Solo puedes añadir tropas galas a esta aldea.");
        }
    }

    @Override
    public float calcularAtaque() {
        return (super.calcularAtaque()*(float)1.2);
    }

    @Override
    public float calcularDefensa() {
        int resistencia = this.resistenciaMuralla;
        return super.calcularDefensa()+(resistencia*(float)1.5);
    }
}
