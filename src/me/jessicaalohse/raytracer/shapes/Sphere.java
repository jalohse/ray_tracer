package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.Light;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Sphere implements Surface {

	Vector3D origin;
	float radius;
	double t;
	RGB color;
	float reflectance;

	public Sphere(float x, float y, float z, float radius, RGB color,
			float reflectance) {
		this.origin = new Vector3D(x, y, z);
		this.radius = radius;
		this.color = color;
		this.reflectance = reflectance;
	}

	public Vector3D getNormalForPoint(Vector3D point) {
		Vector3D normal = point.subtract(origin);
		normal.scaleDown(radius);
		return normal;
	}

	public float getNDotL(Vector3D point, Vector3D lightVector) {
		Vector3D normal = getNormalForPoint(point);
		return normal.getDotProduct(lightVector);
	}

	public RGB getLitColor(Light light, Vector3D point, float ambience) {
		float nDotL = getNDotL(point, light.getLightVector());
		if (ambience == 0) {
			RGB multipliedLight = light.getColor().multiply(
					getAmbientColor(ambience));
			return multipliedLight.multiplyByScalar(nDotL);
		} else {
			int color = (int) ((ambience + (getReflectance() * nDotL)) * 255);
			return new RGB(color, color, color);
		}
	}

	public RGB getAmbientColor(float ambience) {
		RGB original = getColor();
		if (ambience == 0) {
			return addReflectance(new int[] { original.getRed(),
					original.getGreen(), original.getBlue() });
		} else {
			float adjustment = ambience * 255;
			int red = (int) (original.getRed() + adjustment);
			int green = (int) (original.getGreen() + adjustment);
			int blue = (int) (original.getBlue() + adjustment);
			return addReflectance(new int[] { red, green, blue });
		}

	}

	private RGB addReflectance(int[] rgb) {
		double reflectance = getReflectance();
		if (reflectance == 0) {
			return new RGB(rgb[0], rgb[1], rgb[2]);
		} else {
			return new RGB((int) (rgb[0] * reflectance),
					(int) (rgb[1] * reflectance), (int) (rgb[2] * reflectance));
		}
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSubOne, float time) {
		Vector3D d = ((Vector3D) ray.getDistanceVector());
		Vector3D origin = (Vector3D) ray.getOrigin();
		Vector3D originCenter = new Vector3D(
				origin.getX() - this.origin.getX(), origin.getY()
						- this.origin.getY(), origin.getZ()
						- this.origin.getZ());
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
		Vector3D origin = (Vector3D) ray.getOrigin();
		Vector3D originCenter = new Vector3D(
				origin.getX() - this.origin.getX(), origin.getY()
						- this.origin.getY(), origin.getZ()
						- this.origin.getZ());
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

	public float getReflectance() {
		return reflectance;
	}

}
