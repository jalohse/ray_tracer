package me.jessicaalohse.raytracer.utilities;

public class Ray {

	Vector3D origin;
	Vector3D distanceVector;

	public Ray(Vector3D origin, Vector3D vector) {
		this.origin = origin;
		this.distanceVector = vector;
	}

	public Vector pointAtParameter(double t) {
		return origin.add(distanceVector.scaleUp(t));
	}

	@Override
	public String toString() {
		return "Ray [origin=" + origin + ", distanceVector=" + distanceVector
				+ "]";
	}

	public Vector3D getOrigin() {
		return origin;
	}

	public Vector3D getDistanceVector() {
		return distanceVector;
	}

}
