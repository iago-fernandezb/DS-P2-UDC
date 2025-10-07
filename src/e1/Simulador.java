package e1;

public class Simulador {

    public static String simularBatalla(Aldea aldeaAtacante, Aldea aldeaDefensora) {
        float ataqueAtacante = aldeaAtacante.calcularAtaque();
        float defensaDefensora = aldeaDefensora.calcularDefensa();

        System.out.println("Aldea Atacante: " + aldeaAtacante.nombreAldea + " (Ataque Total: " + ataqueAtacante + ")");
        System.out.println("Aldea Defensora: " + aldeaDefensora.nombreAldea + " (Defensa Total: " + defensaDefensora + ")");

        if (ataqueAtacante > defensaDefensora) {
            String resultado = aldeaAtacante.nombreAldea + " gana la batalla contra " + aldeaDefensora.nombreAldea;
            System.out.println(resultado);
            return resultado;
        } else if (ataqueAtacante < defensaDefensora) {
            String resultado = aldeaDefensora.nombreAldea + " defiende exitosamente contra " + aldeaAtacante.nombreAldea;
            System.out.println(resultado);
            return resultado;
        } else {
            String resultado = "La batalla entre " + aldeaAtacante.nombreAldea + " y " + aldeaDefensora.nombreAldea + " termina en empate";
            System.out.println(resultado);
            return resultado;
        }
    }

    public static void simularSerieDeBatallas(Aldea[] aldeas) {
        for (int i = 0; i < aldeas.length - 1; i++) {
            for (int j = i + 1; j < aldeas.length; j++) {
                System.out.println("\nSimulación de batalla entre " + aldeas[i].nombreAldea + " y " + aldeas[j].nombreAldea + ":");
                simularBatalla(aldeas[i], aldeas[j]);
            }
        }
    }

}
