package test;

import wrappers.SmileyWrapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    private static final SmileyWrapper<?> s = new SmileyWrapper<>();

    public static void testSmileyCtr_Speed() {
        double x = 200.0;
        double y = 150.0;

        Object smiley = s.constructor().invoke(x, y);

        double expectedX = 10.0;
        double expectedY = 10.0;
        double actualX = s.speedX().getValue(smiley);
        double actualY = s.speedY().getValue(smiley);
        assertThat(smiley).isNotNull();
        assertThat(actualX)
                .withFailMessage(String.format("Im Attribut speedX wurde zu Beginn (also im Konstruktor) eine falscher Wert gespeichert (%f statt %f)", actualX, expectedX))
                        .isEqualTo(expectedX);
        assertThat(actualY)
                .withFailMessage(String.format("Im Attribut speedY wurde zu Beginn (also im Konstruktor) eine falscher Wert gespeichert (%f statt %f)", actualY, expectedY))
                        .isEqualTo(expectedY);

        Object kopf = s.kopf().getValue(smiley);
        Object augeL = s.augeL().getValue(smiley);
        Object augeR = s.augeR().getValue(smiley);
        Object mund = s.mund().getValue(smiley);

        assertThat(kopf).isNotNull();
        assertThat(augeL).isNotNull();
        assertThat(augeR).isNotNull();
        assertThat(mund).isNotNull();

        assertThat(kopf.getClass().getSimpleName()).
                withFailMessage("Dem Referenz-Attribut kopf wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeL.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeL wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeR.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeR wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(mund.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut mund wurde kein Ellipse-Objekt zugewiesen.")
                .isEqualTo("Ellipse");
    }


    public static void testSmileyCtr_Objects() {
        double x = 200.0;
        double y = 150.0;

        Object smiley = s.constructor().invoke(x, y);


        Object kopf = s.kopf().getValue(smiley);
        Object augeL = s.augeL().getValue(smiley);
        Object augeR = s.augeR().getValue(smiley);
        Object mund = s.mund().getValue(smiley);

        assertThat(kopf).isNotNull();
        assertThat(augeL).isNotNull();
        assertThat(augeR).isNotNull();
        assertThat(mund).isNotNull();

        assertThat(kopf.getClass().getSimpleName()).
                withFailMessage("Dem Referenz-Attribut kopf wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeL.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeL wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeR.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeR wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(mund.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut mund wurde kein Ellipse-Objekt zugewiesen.")
                .isEqualTo("Ellipse");
    }


    public static void testSmileyCtr_Layout() {
        fail("Layout des Smileys kann gerade noch nicht überprüft werden.");
    }

    public static void testBewegen() {
        fail("Die Bewegung des Smileys kann gerade noch nicht überprüft werden.");
    }

    public static void testRumfliegen() {
        fail("Das Rumfliegen des Smileys kann gerade noch nicht überprüft werden.");
    }

    public static void testRandErreicht() {
        fail("Die Methode randErreicht kann gerade noch nicht überprüft werden.");
    }

}
