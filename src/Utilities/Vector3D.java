package Utilities;
public class Vector3D implements Vector{

	double[] vector;

	public Vector3D(double x, double y, double z) {
		vector[0] = x;
		vector[1] = y;
		vector[2] = z;
	}

	public double getX() {
		return vector[0];
	}

	public double getY() {
		return vector[1];
	}

	public double getZ() {
		return vector[2];
	}

	public void setX(double x) {
		vector[0] = x;
	}

	public void setY(double y) {
		vector[1] = y;
	}

	public void setZ(double z) {
		vector[2] = z;
	}

	public double getLength() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)
				+ Math.pow(getZ(), 2));
	}

	public double getLengthSquared() {
		return Math.pow(getX(), 2) + Math.pow(getY(), 2) + Math.pow(getZ(), 2);
	}

	public void makePositive() {
		this.setX(Math.abs(getX()));
		this.setY((getY()));
		this.setZ(Math.abs(getZ()));
	}

	public void makeNegative() {
		this.setX(getX() * -1);
		this.setY(getY() * -1);
		this.setZ(getZ() * -1);
	}

	public void scaleUp(double scale) {
		this.setX(getX() * scale);
		this.setY(getY() * scale);
		this.setZ(getZ() * scale);
	}

	public void scaleDown(double scale) {
		this.setX(getX() / scale);
		this.setY(getY() / scale);
		this.setZ(getZ() / scale);
	}

	public Vector3D add(Vector3D b) {
		double x = getX() + b.getX();
		double y = getY() + b.getY();
		double z = getZ() + b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D subtract(Vector3D b) {
		double x = getX() - b.getX();
		double y = getY() - b.getY();
		double z = getZ() - b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D multiply(Vector3D b) {
		double x = getX() * b.getX();
		double y = getY() * b.getY();
		double z = getZ() * b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D divide(Vector3D b) {
		double x = getX() / b.getX();
		double y = getY() / b.getY();
		double z = getZ() / b.getZ();
		return new Vector3D(x, y, z);
	}

	// Used to compute cosine of angle between two vectors
	public double getDotProduct(Vector3D b) {
		return getX() * b.getX() + getY() * b.getY() + getZ() + b.getZ();
	}

	// Resulting vector is perpendicular to both vectors, can be used to find
	// sine of angle
	public Vector3D getCrossProduct(Vector3D b) {
		double x = getX() * b.getX();
		double y = getY() * b.getY();
		double z = getZ() * b.getZ();
		return new Vector3D(x, y, z);
	}

}
