package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Sphere implements Surface {

	float[] origin = new float[3];
	float radius;
	double t;
	RGB color;

	public Sphere(float x, float y, float z, float radius, RGB color) {
		this.origin[0] = x;
		this.origin[1] = y;
		this.origin[2] = z;
		this.radius = radius;
		this.color = color;
	}

	public Vector3D getNormalForPoint(Vector3D point) {
		return new Vector3D((point.getX() - this.origin[0]) / this.radius,
				(point.getY() - this.origin[1]) / this.radius,
				(point.getZ() - this.origin[2]) / this.radius);
	}

	public RGB getLitColor(RGB lightMultiplied, float[] point,
			Vector3D lightVector) {
		Vector3D vectorPoint = new Vector3D(point[0], point[1], point[2]);
		Vector3D normal = getNormalForPoint(vectorPoint);
		float nDotL = normal.getDotProduct(lightVector);
		lightMultiplied.scaleUp(nDotL);
		return lightMultiplied;
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSubOne) {
		Vector3D d = ((Vector3D) ray.getDistanceVector());
		float[] origin = ray.getOrigin();
		Vector3D originCenter = new Vector3D(origin[0] - this.origin[0],
				origin[1] - this.origin[1], origin[2] - this.origin[2]);
		float a = d.getDotProduct(d);
		float b = 2 * d.getDotProduct(originCenter);
		float c = (float) (originCenter.getDotProduct(originCenter) - Math.pow(
				radius, 2));
		float discriminant = b * b - 4 * a * c;
		if (discriminant >= Math.ulp(discriminant)) {
			double sqrtd = Math.sqrt(discriminant);
			float tVal = (float) ((-b - sqrtd) / (2 * a));
			if (tVal > tSubZero && tVal < tSubOne) {
				t = tVal;
				return true;
			}
			tVal = (float) ((-b + sqrtd) / (2 * a));
			if (tVal > tSubZero && tVal < tSubOne) {
				t = tVal;
				return true;
			}
		}
		return false;
	}

	public double getT() {
		return t;
	}

	public RGB getColor() {
		return color;
	}

}
