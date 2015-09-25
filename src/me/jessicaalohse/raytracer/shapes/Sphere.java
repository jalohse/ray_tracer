package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.textures.ImageTexture;
import me.jessicaalohse.raytracer.textures.Texture;
import me.jessicaalohse.raytracer.utilities.Light;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector2D;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Sphere implements Surface {

	Vector3D origin;
	float radius;
	double t;
	RGB color;
	float reflectance;
	Texture texture;

	public Sphere(float x, float y, float z, float radius, RGB color, float reflectance) {
		this.origin = new Vector3D(x, y, z);
		this.radius = radius;
		this.color = color;
		this.reflectance = reflectance;
	}

	public void addTexture(Texture texture) {
		this.texture = texture;
	}

	public Vector3D getNormalForPoint(Vector3D point) {
		Vector3D normal = point.subtract(origin);
		return normal.scaleDown(radius);
	}

	public float getNDotL(Vector3D point, Vector3D lightVector) {
		Vector3D normal = getNormalForPoint(point);
		return normal.getDotProduct(lightVector);
	}

	public RGB getLitColor(Light light, Vector3D point, float ambience) {
		float nDotL = getNDotL(point, light.getLightVector());
		if (ambience == 0) {
			RGB multipliedLight = light.getColor().multiply(getAmbientColor(ambience, point));
			return multipliedLight.multiplyByScalar(nDotL);
		} else {
			int color = (int) ((ambience + (getReflectance() * nDotL)) * 255);
			return new RGB(color, color, color);
		}
	}

	public RGB getAmbientColor(float ambience, Vector3D hitPoint) {
		RGB original = getColor();
		double reflectance = getReflectance();
		if (texture != null) {
			if (texture instanceof ImageTexture) {
				float theta = (float) Math.acos((hitPoint.getZ() - this.origin.getZ()) / this.radius);
				float phi = (float) Math.atan2(hitPoint.getY() - this.origin.getY(),
						hitPoint.getX() - this.origin.getX());
				if(phi < 0){
					phi += 2*Math.PI;
				}
				float u = (float) (phi / (2 * Math.PI));
				float v = (float) ((Math.PI - theta) / Math.PI);
				original = texture.getValue(new Vector2D(u, v), hitPoint);
			} else {
				original = texture.getValue(null, hitPoint);
			}
		}
		if (reflectance == 0) {
			return original;
		} else {
			return new RGB((int) (original.getRed() * reflectance), (int) (original.getGreen() * reflectance),
					(int) (original.getBlue() * reflectance));
		}
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSubOne, float time) {
		Vector3D d = ((Vector3D) ray.getDistanceVector());
		Vector3D originCenter = ray.getOrigin().subtract(origin);

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
		Vector3D direction = ((Vector3D) ray.getDistanceVector());
		Vector3D temp = ray.getOrigin().subtract(origin);

		float a = direction.getDotProduct(direction);
		float b = 2 * direction.getDotProduct(temp);
		float c = (float) (temp.getDotProduct(temp) - (radius * radius));

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

	@Override
	public Texture getTexture() {
		return texture;
	}

}
