package me.jessicaalohse.raytracer.utilities;

public class Ray {

	Vector origin;
	Vector distanceVector;

	public Ray(Vector origin, Vector vector) {
		this.origin = origin;
		this.distanceVector = vector;
	}

	public Vector pointAtParameter(double t) {
		Vector distance = distanceVector.scaleUp(t);
		float x = origin.getX() + distance.getX();
		float y = origin.getY() + distance.getY();
		if (distanceVector instanceof Vector2D) {
			return new Vector2D(x, y);
		} else {
			Vector3D origin3D = (Vector3D) origin;
			Vector3D vector = (Vector3D) distance;
			float z = origin3D.getZ() + vector.getZ();
			return new Vector3D(x, y, z);
		}
	}

	@Override
	public String toString() {
		return "Ray [origin=" + origin + ", distanceVector=" + distanceVector
				+ "]";
	}

	public Vector getOrigin() {
		return origin;
	}

	public Vector getDistanceVector() {
		return distanceVector;
	}

}
