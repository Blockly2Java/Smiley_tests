package wrappers;

import levenshtein.*;

public class ShapeWrapper<T> extends ClassWrapper<T> {
	private final ConstructorWrapper<T> constructor;
	private final AttributeWrapper<T, Double> centerX;
	private final AttributeWrapper<T, Double> centerY;
	private final AttributeWrapper<T, Double> radius;
	private final AttributeWrapper<T, String> fillColor;
	private final MethodWrapper<T, ?> setFillColor;
	private final MethodWrapper<T, Double> getCenterX;
	private final MethodWrapper<T, Double> getCenterY;
	private final MethodWrapper<T, ?> move;
	private final MethodWrapper<T, ?> rotate;
	private final MethodWrapper<T, ?> scale;
	private final MethodWrapper<T, Object> getWorld;

	@SuppressWarnings("unchecked")
	public ShapeWrapper() {
		super(
			"Shape",
			"",
			"public"
		);

		constructor = new ConstructorWrapper<>(
			this,
			new Class<?>[] { double.class, double.class },
			"public"
		);

		centerX = new AttributeWrapper<>(
			this,
			"centerX",
			double.class,
			"private"
		);

		centerY = new AttributeWrapper<>(
			this,
			"centerY",
			double.class,
			"private"
		);

		radius = new AttributeWrapper<>(
			this,
			"radius",
			double.class,
			"private"
		);

		fillColor = new AttributeWrapper<>(
			this,
			"fillColor",
			String.class,
			"private"
		);

		setFillColor = new MethodWrapper<>(
			this,
			"setFillColor",
			void.class,
			new Class<?>[] { String.class },
			"public"
		);

		getCenterX = new MethodWrapper<>(
			this,
			"getCenterX",
			double.class,
			"public"
		);

		getCenterY = new MethodWrapper<>(
			this,
			"getCenterY",
			double.class,
			"public"
		);

		move = new MethodWrapper<>(
			this,
			"move",
			void.class,
			new Class<?>[] { double.class, double.class },
			"public"
		);

		rotate = new MethodWrapper<>(
			this,
			"rotate",
			void.class,
			new Class<?>[] { double.class },
			"public"
		);

		scale = new MethodWrapper<>(
			this,
			"scale",
			void.class,
			new Class<?>[] { double.class },
			"public"
		);

		Class<Object> worldClass = (Class<Object>) loadClass("World");
		getWorld = new MethodWrapper<>(
			this,
			"getWorld",
			worldClass,
			"public"
		);
	}


    public void setObj(Object obj) {
        super.obj = (T)obj;
    }

	@Override
	public Object getObj(boolean forceNew, boolean useByteBuddy) {
		// Shape has no default constructor; create it with default coordinates.
		return getObj(forceNew, false, constructor, 0.0, 0.0);
	}

	public Object getObj() {
		return getObj(false, false);
	}

	public ConstructorWrapper<T> constructor() {
		return constructor;
	}

	public AttributeWrapper<T, Double> centerX() {
		return centerX;
	}

	public AttributeWrapper<T, Double> centerY() {
		return centerY;
	}

	public AttributeWrapper<T, Double> radius() {
		return radius;
	}

	public AttributeWrapper<T, String> fillColor() {
		return fillColor;
	}

	public MethodWrapper<T, ?> setFillColor() {
		return setFillColor;
	}

	public MethodWrapper<T, Double> getCenterX() {
		return getCenterX;
	}

	public MethodWrapper<T, Double> getCenterY() {
		return getCenterY;
	}

	public MethodWrapper<T, ?> move() {
		return move;
	}

	public MethodWrapper<T, ?> rotate() {
		return rotate;
	}

	public MethodWrapper<T, ?> scale() {
		return scale;
	}

	public MethodWrapper<T, Object> getWorld() {
		return getWorld;
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
