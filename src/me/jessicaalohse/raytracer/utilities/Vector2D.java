package me.jessicaalohse.raytracer.utilities;

public class Vector2D implements Vector {

	float[] vector = new float[2];

	public Vector2D(float x, float y) {
		vector[0] = x;
		vector[1] = y;
	}

	public float getX() {
		return vector[0];
	}

	public float getY() {
		return vector[1];
	}

	public void setX(float x) {
		vector[0] = x;
	}

	public void setY(float y) {
		vector[1] = y;
	}

	public double getLength() {
		return Math.sqrt(this.getX() * this.getX() + this.getY() * this.getY());
	}

	public double getLengthSquared() {
		return Math.pow(getLength(), 2);
	}

	public void makePositive() {
		this.setX(Math.abs(getX()));
		this.setY((getY()));
	}

	public void makeNegative() {
		this.setX(getX() * -1);
		this.setY(getY() * -1);
	}

	public Vector scaleUp(double scale) {
		float x = (float) (getX() * scale);
		float y = (float) (getY() * scale);
		return new Vector2D(x, y);
	}

	public void scaleDown(double scale) {
		this.setX((float) (getX() / scale));
		this.setY((float) (getY() / scale));
	}

	public Vector2D add(Vector2D b) {
		float x = getX() + b.getX();
		float y = getY() + b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D subtract(Vector2D b) {
		float x = getX() - b.getX();
		float y = getY() - b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D multiply(Vector2D b) {
		float x = getX() * b.getX();
		float y = getY() * b.getY();
		return new Vector2D(x, y);
	}

	public Vector2D divide(Vector2D b) {
		float x = getX() / b.getX();
		float y = getY() / b.getY();
		return new Vector2D(x, y);
	}

}
