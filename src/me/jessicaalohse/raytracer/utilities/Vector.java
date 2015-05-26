package me.jessicaalohse.raytracer.utilities;

public interface Vector {
	
	public float getX();

	public float getY();

	public void setX(float x);

	public void setY(float y);

	public double getLength();

	public double getLengthSquared();

	public void makePositive();

	public void makeNegative();

	public void scaleUp(double scale);

	public void scaleDown(double scale);
	
}
