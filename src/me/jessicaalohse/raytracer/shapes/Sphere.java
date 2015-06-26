package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Sphere implements Surface {

	float[] origin = new float[3];
	float radius;
	double t;
	RGB color;
	double reflectance;

	public Sphere(float x, float y, float z, float radius, RGB color,
			double reflectance) {
		this.origin[0] = x;
		this.origin[1] = y;
		this.origin[2] = z;
		this.radius = radius;
		this.color = color;
		this.reflectance = reflectance;

	}

	public Vector3D getNormalForPoint(Vector3D point) {
		return new Vector3D((point.getX() - this.origin[0]) / this.radius,
				(point.getY() - this.origin[1]) / this.radius,
				(point.getZ() - this.origin[2]) / this.radius);
	}

	public float getNDotL(float[] point, Vector3D lightVector) {
		Vector3D vectorPoint = new Vector3D(point[0], point[1], point[2]);
		Vector3D normal = getNormalForPoint(vectorPoint);
		return normal.getDotProduct(lightVector);
	}

	public RGB getLitColor(RGB lightMultiplied, float[] point,
			Vector3D lightVector) {
		float nDotL = getNDotL(point, lightVector);
		lightMultiplied.scaleUp(nDotL);
		return lightMultiplied;
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSubOne, float time) {
		Vector3D d = ((Vector3D) ray.getDistanceVector());
		float[] origin = ray.getOrigin();
		Vector3D originCenter = new Vector3D(origin[0] - this.origin[0],
				origin[1] - this.origin[1], origin[2] - this.origin[2]);
		float a = d.getDotProduct(d);
		float b = 2 * d.getDotProduct(originCenter);
		float c = (float) (originCenter.getDotProduct(originCenter) - (radius * radius));
		float discriminant = b * b - 4 * a * c;
		if (discriminant > 0) {
			double sqrtd = Math.sqrt(discriminant);
			float tVal = (float) ((-b - sqrtd) / (2 * a));
			if (tVal < tSubZero) {
				tVal = (float) ((-b + sqrtd) / (2 * a));
			}
			if (tVal < tSubZero || tVal > tSubOne) {
				return false;
			}
			t = tVal;
			return true;
		}
		return false;
	}

	@Override
	public boolean shadowHit(Ray ray, float tSubZero, float tSub1, float time) {
		Vector3D d = ((Vector3D) ray.getDistanceVector());
		float[] origin = ray.getOrigin();
		Vector3D originCenter = new Vector3D(origin[0] - this.origin[0],
				origin[1] - this.origin[1], origin[2] - this.origin[2]);
		float a = d.getDotProduct(d);
		float b = 2 * d.getDotProduct(originCenter);
		float c = (float) (originCenter.getDotProduct(originCenter) - (radius * radius));
		float discriminant = b * b - 4 * a * c;
		if (discriminant > 0) {
			double sqrtd = Math.sqrt(discriminant);
			float tVal = (float) ((-b - sqrtd) / (2 * a));
			if (tVal < tSubZero) {
				tVal = (float) ((-b + sqrtd) / (2 * a));
			}
			if (tVal < tSubZero || tVal > tSub1) {
				return false;
			}
			return true;
		}
		return false;
	}

	public double getT() {
		return t;
	}

	public RGB getColor() {
		return color;
	}

	public double getReflectance() {
		return reflectance;
	}

}
