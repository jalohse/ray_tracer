package utilities;

public class Vector3D implements Vector {

	float[] vector = new float[3];

	public Vector3D(float x, float y, float z) {
		vector[0] = x;
		vector[1] = y;
		vector[2] = z;
	}

	public float getX() {
		return vector[0];
	}

	public float getY() {
		return vector[1];
	}

	public float getZ() {
		return vector[2];
	}

	public void setX(float x) {
		vector[0] = x;
	}

	public void setY(float y) {
		vector[1] = y;
	}

	public void setZ(float z) {
		vector[2] = z;
	}

	public double getLength() {
		return Math.sqrt(Math.pow(getX(), 2) + Math.pow(getY(), 2)
				+ Math.pow(getZ(), 2));
	}

	public double getLengthSquared() {
		return Math.pow(getLength(), 2);
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
		this.setX((float) (getX() * scale));
		this.setY((float) (getY() * scale));
		this.setZ((float) (getZ() * scale));
	}

	public void scaleDown(double scale) {
		this.setX((float) (getX() / scale));
		this.setY((float) (getY() / scale));
		this.setZ((float) (getZ() / scale));
	}

	public Vector3D add(Vector3D b) {
		float x = getX() + b.getX();
		float y = getY() + b.getY();
		float z = getZ() + b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D subtract(Vector3D b) {
		float x = getX() - b.getX();
		float y = getY() - b.getY();
		float z = getZ() - b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D multiply(Vector3D b) {
		float x = getX() * b.getX();
		float y = getY() * b.getY();
		float z = getZ() * b.getZ();
		return new Vector3D(x, y, z);
	}

	public Vector3D divide(Vector3D b) {
		float x = getX() / b.getX();
		float y = getY() / b.getY();
		float z = getZ() / b.getZ();
		return new Vector3D(x, y, z);
	}

	// Used to compute cosine of angle between two vectors
	public float getDotProduct(Vector3D b) {
		return getX() * b.getX() + getY() * b.getY() + getZ() + b.getZ();
	}

	// Resulting vector is perpendicular to both vectors, can be used to find
	// sine of angle between vectors
	public Vector3D getCrossProduct(Vector3D b) {
		float x = getY() * b.getZ() - getZ() * b.getY();
		float y = getZ() * b.getX() - getX() * b.getZ();
		float z = getX() * b.getY() - getY() * b.getX();
		return new Vector3D(x, y, z);
	}

}
