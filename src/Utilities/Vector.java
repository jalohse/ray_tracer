package Utilities;

public interface Vector {
	
	public double getX();

	public double getY();

	public void setX(double x);

	public void setY(double y);

	public double getLength();

	public double getLengthSquared();

	public void makePositive();

	public void makeNegative();

	public void scaleUp(double scale);

	public void scaleDown(double scale);
	
}
