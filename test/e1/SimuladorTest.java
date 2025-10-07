package e1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

public class SimuladorTest {
    private AldeaGala aldeaGala;
    private AldeaTeutona aldeaTeutona;
    private AldeaRomana aldeaRomana;

    @BeforeEach
    public void setUp() {
        aldeaGala = new AldeaGala("Gala", 500, 10);
        aldeaTeutona = new AldeaTeutona("Teutona", 500, 10);
        aldeaRomana = new AldeaRomana("Romana", 500, 15);
    }

    @Test
    public void testAgregarTropasGala() {
        aldeaGala.addTropaGala(new Druida());
        aldeaGala.addTropaGala(new RayoTeutates(false));
        aldeaGala.addTropaGala(new Falange());

        assertEquals(3, aldeaGala.gettropas().split("\n").length - 1);
    }

    @Test
    public void testAgregarTropasNoValidasAGala() {
        aldeaGala.addTropaGala(new Legionario());
        assertEquals(1, aldeaGala.gettropas().split("\n").length);
    }

    @Test
    public void testCalcularAtaqueYDefensaAldeaGala() {
        aldeaGala.addTropaGala(new Druida());
        aldeaGala.addTropaGala(new RayoTeutates(false));
        aldeaGala.addTropaGala(new Falange());

        float ataqueEsperado = (45 + 100 + 15) * 1.2f;
        float defensaEsperada = (115 + 25 + 40) + (10 * 1.5f);

        assertEquals(ataqueEsperado, aldeaGala.calcularAtaque(), 0.01);
        assertEquals(defensaEsperada, aldeaGala.calcularDefensa(), 0.01);
    }

    @Test
    public void testSimulacionBatallaAldeaGalaVsTeutona() {
        aldeaGala.addTropaGala(new Druida());
        aldeaGala.addTropaGala(new RayoTeutates(false));
        aldeaGala.addTropaGala(new Falange());

        aldeaTeutona.addTropaTeutona(new GuerreroHacha());
        aldeaTeutona.addTropaTeutona(new GuerreroMaza(false));
        aldeaTeutona.addTropaTeutona(new Paladin());

        String resultado = Simulador.simularBatalla(aldeaGala, aldeaTeutona);
        assertTrue(resultado.contains("gana") || resultado.contains("defiende"));
    }

    @Test
    public void testCargarTropasRomanas() {
        aldeaRomana.addTropaRomana(new Legionario());
        aldeaRomana.addTropaRomana(new Pretoriano(false));
        aldeaRomana.addTropaRomana(new EquitesImperatoris());

        assertEquals(3, aldeaRomana.gettropas().split("\n").length - 1);
    }

    @Test
    public void testAgregarTropasNoValidasARomana() {
        aldeaRomana.addTropaRomana(new Druida());
        assertEquals(1, aldeaRomana.gettropas().split("\n").length);
    }

    @Test
    public void testVictoriaAtacante() {
        aldeaGala.addTropaGala(new Druida());
        aldeaGala.addTropaGala(new RayoTeutates(false));
        aldeaGala.addTropaGala(new Falange());

        aldeaTeutona.addTropaTeutona(new GuerreroMaza(false));

        String resultado = Simulador.simularBatalla(aldeaGala, aldeaTeutona);
        assertTrue(resultado.contains("Gala gana la batalla contra Teutona"));
    }

    @Test
    public void testDefensaExitosa() {
        aldeaGala.addTropaGala(new Druida());

        aldeaTeutona.addTropaTeutona(new GuerreroHacha());
        aldeaTeutona.addTropaTeutona(new Paladin());
        aldeaTeutona.addTropaTeutona(new GuerreroMaza(true));

        String resultado = Simulador.simularBatalla(aldeaGala, aldeaTeutona);
        assertTrue(resultado.contains("Teutona defiende exitosamente contra Gala"));
    }

    @Test
    public void testValoresExtremosVictoriaAtacante() {
        aldeaGala.addTropaGala(new RayoTeutates(false));

        aldeaTeutona.addTropaTeutona(new GuerreroMaza(false));

        String resultado = Simulador.simularBatalla(aldeaGala, aldeaTeutona);
        assertTrue(resultado.contains("Gala gana la batalla contra Teutona"));
    }

    @Test
    public void testValoresExtremosDefensaExitosa() {
        aldeaGala.addTropaGala(new Falange());

        aldeaTeutona.addTropaTeutona(new Paladin());

        String resultado = Simulador.simularBatalla(aldeaGala, aldeaTeutona);
        assertTrue(resultado.contains("Teutona defiende exitosamente contra Gala"));
    }

    @Test
    public void testSimularSerieDeBatallas() {
        Aldea[] aldeas = {aldeaGala, aldeaTeutona, aldeaRomana};

        ByteArrayOutputStream salidaConsola = new ByteArrayOutputStream();
        PrintStream consolaOriginal = System.out;
        System.setOut(new PrintStream(salidaConsola));

        try {
            Simulador.simularSerieDeBatallas(aldeas);

            System.out.flush();
            String salida = salidaConsola.toString();

            assertTrue(salida.contains("Simulación de batalla entre Gala y Teutona"));
            assertTrue(salida.contains("Simulación de batalla entre Gala y Romana"));
            assertTrue(salida.contains("Simulación de batalla entre Teutona y Romana"));
        } finally {
            System.setOut(consolaOriginal);
        }
    }
}
