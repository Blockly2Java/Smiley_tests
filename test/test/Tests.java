package test;

import wrappers.ShapeWrapper;
import wrappers.SmileyWrapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    private static final SmileyWrapper<?> s = new SmileyWrapper<>();
    private static final ShapeWrapper<?> k = new ShapeWrapper<>();
    private static final ShapeWrapper<?> l = new ShapeWrapper<>();
    private static final ShapeWrapper<?> r = new ShapeWrapper<>();
    private static final ShapeWrapper<?> m = new ShapeWrapper<>();

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


    public static Object testSmileyCtr_Objects() {
        double x = 200.0;
        double y = 150.0;

        Object smiley = s.constructor().invoke(x, y);


        Object kopf = s.kopf().getValue(smiley);
        Object augeL = s.augeL().getValue(smiley);
        Object augeR = s.augeR().getValue(smiley);
        Object mund = s.mund().getValue(smiley);

        assertThat(kopf).withFailMessage("Dem Referenz-Attribut kopf wurde kein Objekt zugewiesen.").isNotNull();
        assertThat(augeL).withFailMessage("Dem Referenz-Attribut augeL wurde kein Objekt zugewiesen.").isNotNull();
        assertThat(augeR).withFailMessage("Dem Referenz-Attribut augeR wurde kein Objekt zugewiesen.").isNotNull();
        assertThat(mund).withFailMessage("Dem Referenz-Attribut mund wurde kein Objekt zugewiesen.").isNotNull();

        assertThat(kopf.getClass().getSimpleName()).
                withFailMessage("Dem Referenz-Attribut kopf wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeL.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeL wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(augeR.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut augeR wurde kein Circle-Objekt zugewiesen.")
                .isEqualTo("Circle");
        assertThat(mund.getClass().getSimpleName()).withFailMessage("Dem Referenz-Attribut mund wurde kein Ellipse-Objekt zugewiesen.")
                .isEqualTo("Ellipse");

        return smiley;
    }


    public static void testSmileyCtr_Layout() {
        Object smiley = testSmileyCtr_Objects();

        Object kopf = s.kopf().getValue(smiley);
        Object augeL = s.augeL().getValue(smiley);
        Object augeR = s.augeR().getValue(smiley);
        Object mund = s.mund().getValue(smiley);
        k.setObj(kopf);
        l.setObj(augeL);
        r.setObj(augeR);
        m.setObj(mund);


        double expectedKopfX = 200.0;
        double expectedKopfY = 150.0;
        
        double kX = k.getCenterX().invoke();
        double kY = k.getCenterY().invoke();


        assertThat(kX)
                .withFailMessage(String.format("Der Kopf des Smileys hat die falschen Koordinaten. X ist %f statt %f. Nutze die Parameter des Konstruktors für die Position!", kX, expectedKopfX))
                .isEqualTo(expectedKopfX);
        assertThat(kY)                
                .withFailMessage(String.format("Der Kopf des Smileys hat die falschen Koordinaten. Y ist %f statt %f. Nutze die Parameter des Konstruktors für die Position!", kY, expectedKopfY))
                .isEqualTo(expectedKopfY);
        
        double lX = l.getCenterX().invoke();
        double lY = l.getCenterY().invoke();
        double rX = r.getCenterX().invoke();
        double rY = r.getCenterY().invoke();


        // die Augen sollen oberhalb der Mitte des Kopfes sein (oberhalb bedeutet y-Koordinate kleiner als kopf center y)
        assertThat(lY)
                .withFailMessage(String.format("Das linke Auge des Smileys ist nicht oberhalb der Mitte des Kopfes. Das linke Auge hat Y=%f, die Mitte des Kopfes hat Y=%f. Das Auge soll oberhalb der Mitte sein!", lY, kY))
                .isLessThan(kY);
        assertThat(rY)
                .withFailMessage(String.format("Das rechte Auge des Smileys ist nicht oberhalb der Mitte des Kopfes. Das rechte Auge hat Y=%f, die Mitte des Kopfes hat Y=%f. Das Auge soll oberhalb der Mitte sein!", rY, kY))
                .isLessThan(kY);


        // linkes und rechtes Auge sollen gleiche Y Koordinate haben
        assertThat(lY)
                .withFailMessage(String.format("Die Augen des Smileys sind auf unterschiedlichen Höhen. Das linke Auge hat Y=%f, das rechte Auge hat Y=%f. Beide Augen sollen die gleiche Y-Koordinate haben!", lY, rY))
                .isEqualTo(rY);


        // der Abstand der beiden Augen zur mitte soll gleich sein (also die X Koordinaten sollen spiegelbildlich zum Kopf sein)
        assertThat(Math.abs(lX - kX))
                .withFailMessage(String.format("Die Augen des Smileys sind nicht symmetrisch zum Kopf angeordnet. Der Abstand des linken Auges zur Mitte ist %f, der Abstand des rechten Auges zur Mitte ist %f. Beide Abstände sollen gleich sein!", Math.abs(lX - kX), Math.abs(rX - kX)))
                .isEqualTo(Math.abs(rX - kX)); 

        // die Augen sollen nicht weiter als der Radius des Kopfes von der Mitte des Kopfs entfernt sein
        double radiusKopf = 100.0;
        assertThat(Math.sqrt(Math.pow(lX - kX, 2) + Math.pow(lY - kY, 2)))
                .withFailMessage(String.format("Das linke Auge des Smileys ist zu weit von der Mitte des Kopfes entfernt. Der Abstand beträgt %f, der Radius des Kopfes beträgt %f. Das Auge soll nicht weiter von der Mitte entfernt sein als der Radius des Kopfes!", Math.sqrt(Math.pow(lX - kX, 2) + Math.pow(lY - kY, 2)), radiusKopf))
                .isLessThanOrEqualTo(radiusKopf);
        assertThat(Math.sqrt(Math.pow(rX - kX, 2) + Math.pow(rY - kY, 2)))
                .withFailMessage(String.format("Das rechte Auge des Smileys ist zu weit von der Mitte des Kopfes entfernt und dadurch außerhalb des Kopfes. Der Abstand beträgt %f, der Radius des Kopfes beträgt %f. Das Auge soll nicht weiter von der Mitte entfernt sein als der Radius des Kopfes!", Math.sqrt(Math.pow(rX - kX, 2) + Math.pow(rY - kY, 2)), radiusKopf))
                .isLessThanOrEqualTo(radiusKopf);
        
        double mX = m.getCenterX().invoke();
        double mY = m.getCenterY().invoke();

        // der Mund soll unterhalb der Mitte des Kopfes sein (unterhalb bedeutet y-Koordinate groesser als kopf center y)
        assertThat(mY)
                .withFailMessage(String.format("Der Mund des Smileys ist nicht unterhalb der Mitte des Kopfes. Der Mund hat Y=%f, die Mitte des Kopfes hat Y=%f. Der Mund soll unterhalb der Mitte sein!", mY, kY))
                .isGreaterThan(kY);

        // der Mund soll horizontal zentriert am kopf sein
        assertThat(mX)
                .withFailMessage(String.format("Der Mund des Smileys ist am Kopf. Der Mund hat X=%f, die Mitte des Kopfes hat X=%f. Der Mund soll horizontal zentriert am Kopf sein!", mX, kX))
                .isEqualTo(kX);

         // der Mund soll nicht mehr als der Radius des Kopfes weiter unten als die Mitte des Kopfes sein
        assertThat(mY - kY)
                .withFailMessage(String.format("Der Mund des Smileys ist zu weit von der Mitte des Kopfes entfernt und damit außerhalb des Kopfes. Der Abstand beträgt %f, der Radius des Kopfes beträgt %f. Der Mund soll nicht weiter von der Mitte entfernt sein als der Radius des Kopfes!", mY - kY, radiusKopf))
                .isGreaterThanOrEqualTo(-radiusKopf);
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
