package me.jessicaalohse.raytracer.utilities;

import java.util.Arrays;

public class Ray {

	float[] origin;
	Vector distanceVector;

	public Ray(float[] origin, Vector vector) {
		this.origin = origin;
		this.distanceVector = vector;
	}

	public float[] pointAtParameter(double t) {
		distanceVector.scaleUp(t);
		float x = origin[0] + distanceVector.getX();
		float y = origin[1] + distanceVector.getY();
		if (distanceVector instanceof Vector2D) {
			return new float[] {x, y};
		} else {
			Vector3D vector = (Vector3D) distanceVector;
			float z = origin[2] + vector.getZ();
			return new float[] {x, y, z};
		}
	}

	@Override
	public String toString() {
		return "Ray [origin=" + Arrays.toString(origin) + ", distanceVector="
				+ distanceVector + "]";
	}

	public float[] getOrigin() {
		return origin;
	}

	public Vector getDistanceVector() {
		return distanceVector;
	}

}
