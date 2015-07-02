package me.jessicaalohse.raytracer.textures;

import me.jessicaalohse.raytracer.utilities.Vector3D;

public class SolidNoise {

	public static final float SOLID_NOISE = 1;

	private Vector3D[] grad = new Vector3D[16];
	private int[] phi = new int[16];

	public SolidNoise() {
		populateGrad();
		for (int i = 0; i < 16; i++) {
			phi[i] = i;
		}
		// shuffle phi
		for (int i = 14; i >= 0; i--) {
			int target = (int) (Math.random() * i);
			int temp = phi[i + 1];
			phi[i + 1] = phi[target];
			phi[target] = temp;
		}
	}

	private void populateGrad() {
		grad[0] = new Vector3D(1, 1, 0);
		grad[1] = new Vector3D(-1, 1, 0);
		grad[2] = new Vector3D(1, -1, 0);
		grad[3] = new Vector3D(-1, -1, 0);

		grad[4] = new Vector3D(1, 0, 1);
		grad[5] = new Vector3D(-1, 0, 1);
		grad[6] = new Vector3D(1, 0, -1);
		grad[7] = new Vector3D(-1, 0, -1);

		grad[8] = new Vector3D(0, 1, 1);
		grad[9] = new Vector3D(0, -1, 1);
		grad[10] = new Vector3D(0, 1, -1);
		grad[11] = new Vector3D(0, -1, -1);

		grad[12] = new Vector3D(1, 0, 1);
		grad[13] = new Vector3D(-1, 0, 1);
		grad[14] = new Vector3D(1, 0, -1);
		grad[15] = new Vector3D(-1, 0, -1);
	}

	private float getOmega(float t) {
		if (t <= 0.0f) {
			t = t * -1;
		}
		return (-6.0f * t * t * t * t * t + 15.0f * t * t * t * t - 10.0f * t
				* t * t + 1.0f);
	}

	private Vector3D getGamma(int i, int j, int k) {
		int index;
		index = phi[Math.abs(k) % 16];
		index = phi[Math.abs(j + index) % 16];
		index = phi[Math.abs(i + index) % 16];
		return grad[index];
	}

	private float getKnot(int i, int j, int k, Vector3D v) {
		return getOmega(v.getX()) * getOmega(v.getY()) * getOmega(v.getZ())
				* getGamma(i, j, k).getDotProduct(v);
	}

	private int getIntGamma(int i, int j) {
		int index;
		index = phi[Math.abs(j) % 16];
		index = phi[Math.abs(i + index) % 16];
		return index;
	}

	public float createTurbulence(Vector3D p, int depth) {
		float sum = 0.0f;
		float weight = 1.0f;
		Vector3D temp = p;
		sum = Math.abs(getNoise(temp));

		for (int i = 1; i < depth; i++) {
			weight = weight * 2;
			temp.setX(p.getX() * weight);
			temp.setY(p.getY() * weight);
			temp.setZ(p.getZ() * weight);
			sum += Math.abs(getNoise(temp) / weight);
		}
		return sum;
	}

	public float getDTurbulence(Vector3D p, int depth, float d) {
		float sum = 0.0f;
		float weight = 1.0f;
		Vector3D temp = p;
		sum += Math.abs(getNoise(temp)) / d;

		for (int i = 1; i < depth; i++) {
			weight = weight * 2;
			temp.setX(p.getX() * weight);
			temp.setY(p.getY() * weight);
			temp.setZ(p.getZ() * weight);
			sum += Math.abs(getNoise(temp)) / d;
		}
		return sum;
	}

	private float getNoise(Vector3D temp) {
		int fi = (int) Math.floor(temp.getX());
		int fj = (int) Math.floor(temp.getY());
		int fk = (int) Math.floor(temp.getZ());
		float fu = temp.getX() - fi;
		float fv = temp.getY() - fj;
		float fw = temp.getZ() - fk;
		float sum = 0.0f;

		Vector3D vector = new Vector3D(fu, fv, fw);
		sum += getKnot(fi, fj, fk, vector);

		vector = new Vector3D(fu - 1, fv, fw);
		sum += getKnot(fi + 1, fj, fk, vector);

		vector = new Vector3D(fu, fv - 1, fw);
		sum += getKnot(fi, fj + 1, fk, vector);

		vector = new Vector3D(fu, fv, fw - 1);
		sum += getKnot(fi, fj, fk + 1, vector);

		vector = new Vector3D(fu - 1, fv - 1, fw);
		sum += getKnot(fi + 1, fj + 1, fk, vector);

		vector = new Vector3D(fu - 1, fv, fw - 1);
		sum += getKnot(fi + 1, fj, fk + 1, vector);

		vector = new Vector3D(fu, fv - 1, fw - 1);
		sum += getKnot(fi, fj + 1, fk + 1, vector);

		vector = new Vector3D(fu - 1, fv - 1, fw - 1);
		sum += getKnot(fi + 1, fj + 1, fk + 1, vector);

		return sum;

	}
}
