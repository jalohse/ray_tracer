package Utilities;
public class Vector2D implements Vector{

	double[] vector;

	public Vector2D(double x, double y) {
		vector[0] = x;
		vector[1] = y;
	}

	public double getX() {
		return vector[0];
	}

	public double getY() {
		return vector[1];
	}

	public void setX(double x) {
		vector[0] = x;
	}

	public void setY(double y) {
		vector[1] = y;
	}

	public double getLength() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2));
	}

	public double getLengthSquared() {
		return Math.pow(getX(), 2) + Math.pow(getY(), 2);
	}

	public void makePositive() {
		this.setX(Math.abs(getX()));
		this.setY((getY()));
	}

	public void makeNegative() {
		this.setX(getX() * -1);
		this.setY(getY() * -1);
	}

	public void scaleUp(double scale) {
		this.setX(getX() * scale);
		this.setY(getY() * scale);
	}

	public void scaleDown(double scale) {
		this.setX(getX() / scale);
		this.setY(getY() / scale);
	}

	public Vector2D add(Vector2D b) {
		double x = getX() + b.getX();
		double y = getY() + b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D subtract(Vector2D b) {
		double x = getX() - b.getX();
		double y = getY() - b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D multiply(Vector2D b) {
		double x = getX() * b.getX();
		double y = getY() * b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D divide(Vector2D b) {
		double x = getX() / b.getX();
		double y = getY() / b.getY();
		return new Vector2D(x, y);
	}

}
