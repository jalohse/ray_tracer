package me.jessicaalohse.raytracer.utilities;

public class OrthonormalBasis {
	Vector3D u;
	Vector3D w;
	Vector3D v;

	final static Vector3D n = new Vector3D(1, 0, 0);
	final static Vector3D m = new Vector3D(0, 1, 0);
	final static double EPSILON = 0.01;

	public OrthonormalBasis() {

	}

	public OrthonormalBasis(Vector3D u, Vector3D v, Vector3D w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public static OrthonormalBasis constructFromUV(Vector3D a, Vector3D b) {
		Vector3D u = a.makeUnitVector();
		Vector3D w = a.getCrossProduct(b).makeUnitVector();
		Vector3D v = w.getCrossProduct(u);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromVU(Vector3D a, Vector3D b) {
		Vector3D v = a.makeUnitVector();
		Vector3D w = b.getCrossProduct(a).makeUnitVector();
		Vector3D u = v.getCrossProduct(w);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromVW(Vector3D a, Vector3D b) {
		Vector3D v = a.makeUnitVector();
		Vector3D u = a.getCrossProduct(b).makeUnitVector();
		Vector3D w = u.getCrossProduct(v);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromWV(Vector3D a, Vector3D b) {
		Vector3D w = a.makeUnitVector();
		Vector3D u = b.getCrossProduct(a).makeUnitVector();
		Vector3D v = w.getCrossProduct(u);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromUW(Vector3D a, Vector3D b) {
		Vector3D u = a.makeUnitVector();
		Vector3D v = b.getCrossProduct(a).makeUnitVector();
		Vector3D w = u.getCrossProduct(v);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromWU(Vector3D a, Vector3D b) {
		Vector3D w = a.makeUnitVector();
		Vector3D v = a.getCrossProduct(b).makeUnitVector();
		Vector3D u = v.getCrossProduct(w);
		return new OrthonormalBasis(u, v, w);
	}

	public static OrthonormalBasis constructFromU(Vector3D a) {
		Vector3D u = a.makeUnitVector();
		Vector3D v = u.getCrossProduct(n);
		if (v.getLengthSquared() < EPSILON) {
			v = u.getCrossProduct(m);
		}
		Vector3D w = u.getCrossProduct(v);
		return new OrthonormalBasis(u, v, w);
	}

	public OrthonormalBasis constructFromV(Vector3D a) {
		Vector3D v = a.makeUnitVector();
		Vector3D u = v.getCrossProduct(n);
		if (u.getLengthSquared() < EPSILON) {
			u = v.getCrossProduct(m);
		}
		Vector3D w = u.getCrossProduct(v);
		return new OrthonormalBasis(u, v, w);
	}

	public OrthonormalBasis constructFromW(Vector3D a) {
		Vector3D w = a.makeUnitVector();
		Vector3D u = w.getCrossProduct(n);
		if (u.getLengthSquared() < EPSILON) {
			u = w.getCrossProduct(m);
		}
		Vector3D v = w.getCrossProduct(u);
		return new OrthonormalBasis(u, v, w);
	}

	public Vector3D getU() {
		return u;
	}

	public void setU(Vector3D u) {
		this.u = u;
	}

	public Vector3D getW() {
		return w;
	}

	public void setW(Vector3D w) {
		this.w = w;
	}

	public Vector3D getV() {
		return v;
	}

	public void setV(Vector3D v) {
		this.v = v;
	}

}
