package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;
import Jama.Matrix;

public class Triangle implements Surface {

	double[] a = new double[3];
	double[] b = new double[3];
	double[] c = new double[3];
	double t;
	double aX;
	double aY;
	double aZ;
	double bX;
	double bY;
	double bZ;
	double cX;
	double cY;
	double cZ;
	Vector3D d;
	Vector3D origin;
	RGB color;
	double reflectance;

	public Triangle(double[] a, double[] b, double[] c, RGB color,
			double reflectance) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.aX = this.a[0];
		this.aY = this.a[1];
		this.aZ = this.a[2];
		this.bX = this.b[0];
		this.bY = this.b[1];
		this.bZ = this.b[2];
		this.cX = this.c[0];
		this.cY = this.c[1];
		this.cZ = this.c[2];
		this.color = color;
		this.reflectance = reflectance;
	}

	public Vector3D getNormal() {
		Vector3D p1Minusp0 = new Vector3D((float) (this.aX - this.cX),
				(float) (this.aY - this.cY), (float) (this.aZ - this.cZ));
		Vector3D p2Minusp0 = new Vector3D((float) (this.bX - this.cX),
				(float) (this.bY - this.cY), (float) (this.bZ - this.cZ));
		return p1Minusp0.multiply(p2Minusp0);
	}

	public RGB getLitColor(RGB lightMultiplied, Vector3D lightVector) {
		float nDotL = getNormal().getDotProduct(lightVector);
		lightMultiplied.multiply(nDotL);
		return lightMultiplied;
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSub1, float time) {
		this.d = ((Vector3D) ray.getDistanceVector());
		this.origin = (Vector3D) ray.getOrigin();
		Matrix matrixA = createMatrixA();
		double determinantA = matrixA.det();
		double beta = getBeta(determinantA);
		if (beta <= 0.0 || beta >= 1.0) {
			return false;
		}
		double gamma = getGamma(determinantA);
		double tVal = setT(determinantA);
		if (gamma <= 0.0 || beta + gamma >= 1.0) {
			return false;
		}
		if (tVal >= tSubZero && tVal <= tSub1) {
			this.t = tVal;
			return true;
		}
		return false;
	}

	private Matrix createMatrixA() {
		return new Matrix(
				new double[][] { { aX - bX, aX - cX, d.getX() },
						{ aY - bY, aY - cY, d.getY() },
						{ aZ - bZ, aZ - cZ, d.getZ() } });
	}

	private double getBeta(double determinantA) {
		Matrix betaNum = createMatrixBeta();
		return betaNum.det() / determinantA;
	}

	private Matrix createMatrixBeta() {
		return new Matrix(new double[][] {
				{ aX - this.origin.getX(), aX - cX, d.getX() },
				{ aY - this.origin.getY(), aY - cY, d.getY() },
				{ aZ - this.origin.getZ(), aZ - cZ, d.getZ() } });
	}

	private double getGamma(double determinantA) {
		Matrix gammaNum = createMatrixGamma();
		return gammaNum.det() / determinantA;
	}

	private Matrix createMatrixGamma() {
		return new Matrix(new double[][] {
				{ aX - bX, aX - this.origin.getX(), d.getX() },
				{ aY - bY, aY - this.origin.getY(), d.getY() },
				{ aZ - bZ, aZ - this.origin.getZ(), d.getZ() } });
	}

	private double setT(double determinantA) {
		Matrix tNum = createMatrixT();
		return tNum.det() / determinantA;
	}

	private Matrix createMatrixT() {
		return new Matrix(new double[][] {
				{ aX - bX, aX - cX, aX - this.origin.getX() },
				{ aY - bY, aY - cY, aY - this.origin.getY() },
				{ aZ - bZ, aZ - cZ, aZ - this.origin.getZ() } });
	}

	public boolean shadowHit(Ray ray, float tSubZero, float tSub1, float time) {
		this.d = ((Vector3D) ray.getDistanceVector());
		this.origin = (Vector3D) ray.getOrigin();
		Matrix matrixA = createMatrixA();
		double determinantA = matrixA.det();
		double beta = getBeta(determinantA);
		if (beta <= 0.0 || beta >= 1.0) {
			return false;
		}
		double gamma = getGamma(determinantA);
		double tVal = setT(determinantA);
		if (gamma <= 0.0 || beta + gamma >= 1.0) {
			return false;
		}
		return (tVal >= tSubZero && tVal <= tSub1);
	}

	@Override
	public double getT() {
		return t;
	}

	public RGB getColor() {
		return color;
	}

	public double getReflectance() {
		return this.reflectance;
	}
}
