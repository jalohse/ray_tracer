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
	float[] origin;
	RGB color;

	public Triangle(double[] a, double[] b, double[] c, RGB color) {
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
		lightMultiplied.scaleUp(nDotL);
		return lightMultiplied;
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSub1) {
		this.d = ((Vector3D) ray.getDistanceVector());
		this.origin = ray.getOrigin();
		Matrix matrixA = createMatrixA();
		double determinantA = matrixA.det();
		double beta = getBeta(determinantA);
		double rho = getRho(determinantA);
		setT(determinantA);
		if (beta > 0 && rho > 0 && beta + rho < 1 && t < tSub1 && t > tSubZero) {
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
				{ aX - origin[0], aX - cX, d.getX() },
				{ aY - origin[1], aY - cY, d.getY() },
				{ aZ - origin[2], aZ - cZ, d.getZ() } });
	}

	private double getRho(double determinantA) {
		Matrix rhoNum = createMatrixRho();
		return rhoNum.det() / determinantA;
	}

	private Matrix createMatrixRho() {
		return new Matrix(new double[][] {
				{ aX - bX, aX - origin[0], d.getX() },
				{ aY - bY, aY - origin[1], d.getY() },
				{ aZ - bZ, aZ - origin[0], d.getZ() } });
	}

	private void setT(double determinantA) {
		Matrix tNum = createMatrixT();
		this.t = tNum.det() / determinantA;
	}

	private Matrix createMatrixT() {
		return new Matrix(new double[][] {
				{ aX - bX, aX - cX, aX - origin[0] },
				{ aY - bY, aY - cY, aY - origin[1] },
				{ aZ - bZ, aZ - cZ, aZ - origin[2] } });
	}

	@Override
	public double getT() {
		return t;
	}
	
	public RGB getColor() {
		return color;
	}
}
