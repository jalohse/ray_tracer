package Utilities;
public class Ray {

	double[] origin;
	Vector distanceVector;

	public Ray(double[] origin, Vector vector) {
		this.origin = origin;
		this.distanceVector = vector;
	}

	public double[] pointAtParameter(int scalar) {
		distanceVector.scaleUp(scalar);
		double x = origin[0] + distanceVector.getX();
		double y = origin[1] + distanceVector.getY();
		if (distanceVector instanceof Vector2D) {
			return new double[] {x, y};
		} else {
			Vector3D vector = (Vector3D) distanceVector;
			double z = origin[2] + vector.getZ();
			return new double[] {x, y, z};
		}
	}

}
