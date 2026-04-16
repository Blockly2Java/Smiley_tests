package wrappers;

import levenshtein.*;

public class SmileyWrapper<T> extends ClassWrapper<T>
{
    private final ConstructorWrapper<T> constructor;
    private final AttributeWrapper<T, Double> speedX;
    private final AttributeWrapper<T, Double> speedY;
    private final AttributeWrapper<T, Object> kopf;
    private final AttributeWrapper<T, Object> augeL;
    private final AttributeWrapper<T, Object> augeR;
    private final AttributeWrapper<T, Object> mund;
    private final MethodWrapper<T, Boolean> randErreicht;
    private final MethodWrapper<T, ?> bewegen;
    private final MethodWrapper<T, ?> rumfliegen;

    @SuppressWarnings("unchecked")
    public SmileyWrapper()
    {
        super(
            "Smiley",
            "",
            "public"
        );

        constructor = new ConstructorWrapper<>(
            this,
            new Class<?>[] { double.class, double.class },
            "public"
        );

        speedX = new AttributeWrapper<>(
            this,
            "speedX",
            double.class,
            "private"
        );

        speedY = new AttributeWrapper<>(
            this,
            "speedY",
            double.class,
            "private"
        );

        Class<Object> circleClass = (Class<Object>) loadClass("Circle");
        Class<Object> ellipseClass = (Class<Object>) loadClass("Ellipse");

        kopf = new AttributeWrapper<>(
            this,
            "kopf",
            circleClass,
            "private"
        );

        augeL = new AttributeWrapper<>(
            this,
            "augeL",
            circleClass,
            "private"
        );

        augeR = new AttributeWrapper<>(
            this,
            "augeR",
            circleClass,
            "private"
        );

        mund = new AttributeWrapper<>(
            this,
            "mund",
            ellipseClass,
            "private"
        );

        randErreicht = new MethodWrapper<>(
            this,
            "randErreicht",
            boolean.class,
            "public"
        );

        bewegen = new MethodWrapper<>(
            this,
            "bewegen",
            void.class,
            new Class<?>[] { double.class, double.class },
            "public"
        );

        rumfliegen = new MethodWrapper<>(
            this,
            "rumfliegen",
            void.class,
            "public"
        );
    }

    @Override
    public Object getObj(boolean forceNew, boolean useByteBuddy) {
        // Smiley has no default constructor; create it with default coordinates.
        return getObj(forceNew, false, constructor, 0.0, 0.0);
    }

    public Object getObj() {
        return getObj(false, false);
    }

    public ConstructorWrapper<T> constructor() {
        return constructor;
    }

    public AttributeWrapper<T, Double> speedX() {
        return speedX;
    }

    public AttributeWrapper<T, Double> speedY() {
        return speedY;
    }

    public AttributeWrapper<T, Object> kopf() {
        return kopf;
    }

    public AttributeWrapper<T, Object> augeL() {
        return augeL;
    }

    public AttributeWrapper<T, Object> augeR() {
        return augeR;
    }

    public AttributeWrapper<T, Object> mund() {
        return mund;
    }

    public MethodWrapper<T, Boolean> randErreicht() {
        return randErreicht;
    }

    public MethodWrapper<T, ?> bewegen() {
        return bewegen;
    }

    public MethodWrapper<T, ?> rumfliegen() {
        return rumfliegen;
    }

    private Class<?> loadClass(String className) {
        try {
            return Class.forName(className);
        }
        catch (ClassNotFoundException e) {
            throw new IllegalStateException("Expected class not found: " + className, e);
        }
    }
}
