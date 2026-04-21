package test;

import wrappers.ShapeWrapper;
import wrappers.SmileyWrapper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.within;
import static org.junit.jupiter.api.Assertions.fail;

public class Tests {
    private static final SmileyWrapper<?> s = new SmileyWrapper<>();
    private static final ShapeWrapper<?> k = new ShapeWrapper<>();
    private static final ShapeWrapper<?> l = new ShapeWrapper<>();
    private static final ShapeWrapper<?> r = new ShapeWrapper<>();
    private static final ShapeWrapper<?> m = new ShapeWrapper<>();

    private static void setShapeWrappers(Object smiley) {
        k.setObj(s.kopf().getValue(smiley));
        l.setObj(s.augeL().getValue(smiley));
        r.setObj(s.augeR().getValue(smiley));
        m.setObj(s.mund().getValue(smiley));
    }

    private static void assertMovedBy(ShapeWrapper<?> shape, String teilName, double oldX, double oldY, double dx, double dy) {
        double newX = shape.getCenterX().invoke();
        double newY = shape.getCenterY().invoke();

        assertThat(newX)
                .withFailMessage(String.format(
                        "Das Teil '%s' wurde in X nicht korrekt bewegt (%f statt %f). Hinweis: In bewegen(dx, dy) sollten alle Teilobjekte mit genau diesen Werten verschoben werden.",
                        teilName, newX, oldX + dx))
                .isCloseTo(oldX + dx, within(1e-9));

        assertThat(newY)
                .withFailMessage(String.format(
                        "Das Teil '%s' wurde in Y nicht korrekt bewegt (%f statt %f). Hinweis: Nutze fuer jedes Teil move(dx, dy).",
                        teilName, newY, oldY + dy))
                .isCloseTo(oldY + dy, within(1e-9));
    }

    private static double getWorldDimension(Object smiley, String methodName) {
        setShapeWrappers(smiley);
        Object world = k.getWorld().invoke();

        assertThat(world)
                .withFailMessage("Die Welt des Kopfes konnte nicht ermittelt werden. Hinweis: Nutze die Welt-Grenzen der Formen statt fester Zahlen.")
                .isNotNull();

        try {
            Object value = world.getClass().getMethod(methodName).invoke(world);
            return ((Number) value).doubleValue();
        }
        catch (Exception e) {
            fail("Die Welt liefert keine gueltige Methode '" + methodName + "'.");
            return 0.0;
        }
    }

    private static void assertRandErreichtAt(double x, double y, boolean expected, String failMessage) {
        Object smiley = s.constructor().invoke(x, y);
        boolean actual = s.randErreicht().invokeOnSpecificObject(smiley);

        if (expected) {
            assertThat(actual).withFailMessage(failMessage).isTrue();
        }
        else {
            assertThat(actual).withFailMessage(failMessage).isFalse();
        }
    }

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
        Object smiley = s.constructor().invoke(260.0, 180.0);
        setShapeWrappers(smiley);

        double kXBefore = k.getCenterX().invoke();
        double kYBefore = k.getCenterY().invoke();
        double lXBefore = l.getCenterX().invoke();
        double lYBefore = l.getCenterY().invoke();
        double rXBefore = r.getCenterX().invoke();
        double rYBefore = r.getCenterY().invoke();
        double mXBefore = m.getCenterX().invoke();
        double mYBefore = m.getCenterY().invoke();

        double dx = -17.5;
        double dy = 23.25;
        s.bewegen().invokeOnSpecificObject(smiley, dx, dy);

        assertMovedBy(k, "kopf", kXBefore, kYBefore, dx, dy);
        assertMovedBy(l, "augeL", lXBefore, lYBefore, dx, dy);
        assertMovedBy(r, "augeR", rXBefore, rYBefore, dx, dy);
        assertMovedBy(m, "mund", mXBefore, mYBefore, dx, dy);
    }

    public static void testRumfliegen() {
        Object probe = s.constructor().invoke(200.0, 150.0);
        double worldWidth = getWorldDimension(probe, "getWidth");
        double worldHeight = getWorldDimension(probe, "getHeight");

        Object innen = s.constructor().invoke(worldWidth / 2.0, worldHeight / 2.0);
        setShapeWrappers(innen);

        double oldSXInside = s.speedX().getValue(innen);
        double oldSYInside = s.speedY().getValue(innen);
        double oldKXInside = k.getCenterX().invoke();
        double oldKYInside = k.getCenterY().invoke();

        s.rumfliegen().invokeOnSpecificObject(innen);

        double newSXInside = s.speedX().getValue(innen);
        double newSYInside = s.speedY().getValue(innen);
        double newKXInside = k.getCenterX().invoke();
        double newKYInside = k.getCenterY().invoke();

        assertThat(newSXInside)
                .withFailMessage("Innerhalb der Welt sollte speedX nicht umgedreht werden. Hinweis: Vor dem Umdrehen zuerst randErreicht() pruefen.")
                .isCloseTo(oldSXInside, within(1e-9));
        assertThat(newSYInside)
                .withFailMessage("Innerhalb der Welt sollte speedY nicht umgedreht werden. Hinweis: Nur am Rand die Richtung wechseln.")
                .isCloseTo(oldSYInside, within(1e-9));
        assertThat(newKXInside)
                .withFailMessage("Im normalen Flug sollte der Kopf mit speedX bewegt werden. Hinweis: Rumfliegen sollte am Ende bewegen(speedX, speedY) aufrufen.")
                .isCloseTo(oldKXInside + oldSXInside, within(1e-9));
        assertThat(newKYInside)
                .withFailMessage("Im normalen Flug sollte der Kopf mit speedY bewegt werden. Hinweis: Verwende beide Geschwindigkeiten beim Bewegen.")
                .isCloseTo(oldKYInside + oldSYInside, within(1e-9));

        Object rechtsAussen = s.constructor().invoke(worldWidth + 5.0, worldHeight / 2.0);
        s.speedX().setValue(rechtsAussen, 7.0);
        s.speedY().setValue(rechtsAussen, 4.0);
        setShapeWrappers(rechtsAussen);

        double oldKXRight = k.getCenterX().invoke();
        double oldKYRight = k.getCenterY().invoke();

        s.rumfliegen().invokeOnSpecificObject(rechtsAussen);

        double newSXRight = s.speedX().getValue(rechtsAussen);
        double newKXRight = k.getCenterX().invoke();
        double newKYRight = k.getCenterY().invoke();

        assertThat(newSXRight)
                .withFailMessage("Wenn der Smiley rechts ausserhalb ist, muss speedX die Richtung wechseln. Hinweis: Vorzeichen umdrehen, z.B. speedX = -speedX.")
                .isLessThan(0.0);
        assertThat(newKXRight)
                .withFailMessage("Nach dem Erreichen des rechten Randes muss sich der Smiley wieder nach links bewegen. Hinweis: Nach dem Richtungswechsel bewegen() aufrufen.")
                .isLessThan(oldKXRight);
        assertThat(newKYRight)
                .withFailMessage("Der Flug sollte auch nach einem Randkontakt weiterhin die Y-Geschwindigkeit verwenden. Hinweis: rumfliegen() sollte mit speedX und speedY bewegen.")
                .isNotEqualTo(oldKYRight);
    }

    public static void testRandErreicht() {
        Object probe = s.constructor().invoke(200.0, 150.0);
        double worldWidth = getWorldDimension(probe, "getWidth");
        double worldHeight = getWorldDimension(probe, "getHeight");

        assertRandErreichtAt(
                worldWidth / 2.0,
                worldHeight / 2.0,
                false,
                "In der Mitte der Welt darf randErreicht() nicht true liefern. Hinweis: Pruefe, ob die Kopfmitte ausserhalb des Bereichs [0..Breite] bzw. [0..Hoehe] liegt."
        );

        assertRandErreichtAt(
                worldWidth - 1.0,
                worldHeight / 2.0,
                false,
                "Knapp innerhalb des rechten Randes darf randErreicht() noch false sein. Hinweis: Geprueft werden soll die Mitte des Smileys, nicht der Radius."
        );

        assertRandErreichtAt(
                worldWidth + 1.0,
                worldHeight / 2.0,
                true,
                "Rechts ausserhalb der Welt muss randErreicht() true liefern. Hinweis: Vergleiche centerX mit der Weltbreite."
        );

        assertRandErreichtAt(
                -1.0,
                worldHeight / 2.0,
                true,
                "Links ausserhalb der Welt muss randErreicht() true liefern. Hinweis: centerX < 0 sollte erkannt werden."
        );

        assertRandErreichtAt(
                worldWidth / 2.0,
                worldHeight + 1.0,
                true,
                "Unterhalb ausserhalb der Welt muss randErreicht() true liefern. Hinweis: Vergleiche centerY mit der Welthoehe."
        );

        assertRandErreichtAt(
                worldWidth / 2.0,
                -1.0,
                true,
                "Oberhalb ausserhalb der Welt muss randErreicht() true liefern. Hinweis: centerY < 0 sollte erkannt werden."
        );
    }

}
