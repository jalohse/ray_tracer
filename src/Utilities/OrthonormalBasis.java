package utilities;

public class OrthonormalBasis {
	Vector3D a;
	Vector3D b;
	Vector3D u;
	Vector3D w;
	Vector3D v;

	public OrthonormalBasis(Vector3D a, Vector3D b) {
		this.a = a;
		this.b = b;
	}

	public OrthonormalBasis(Vector3D u, Vector3D v, Vector3D w) {
		this.u = u;
		this.v = v;
		this.w = w;
	}

	public void constructFromUV() {
		construct(this.u, this.v, this.w);
	}

	public void constructFromVU() {
		construct(this.v, this.u, this.w);
	}

	public void constructFromVW() {
		construct(this.v, this.w, this.u);
	}

	public void constructFromWV() {
		construct(this.w, this.v, this.u);
	}

	public void constructFromUW() {
		construct(this.u, this.w, this.v);
	}

	public void constructFromWU() {
		construct(this.w, this.u, this.v);
	}

	public void constructFromW() {
		a.scaleDown(a.getLength());
		this.w = a;
		double absX = Math.abs(w.getX());
		double absY = Math.abs(w.getY());
		double absZ = Math.abs(w.getZ());
		Vector3D newVector;
		if (absX < absY && absX < absZ) {
			newVector = new Vector3D(0, w.getZ(), w.getY() * -1);
			newVector.scaleDown(newVector.getLength());
		} else if (absY < absZ) {
			newVector = new Vector3D(w.getZ(), 0, w.getX() * -1);
			newVector.scaleDown(newVector.getLength());
		} else {
			newVector = new Vector3D(w.getY(), w.getX() * -1, 0);
			newVector.scaleDown(newVector.getLength());
		}
		this.v = newVector;
		this.u = v.getCrossProduct(w);
	}

	private void construct(Vector3D parallel, Vector3D inPlane,
			Vector3D parallelToCrossProduct) {
		a.scaleDown(a.getLength());
		parallel = a;
		Vector3D crossProduct = a.getCrossProduct(b);
		crossProduct.scaleDown(crossProduct.getLength());
		parallelToCrossProduct = crossProduct;
		inPlane = parallelToCrossProduct.getCrossProduct(parallel);
	}

	public double getASubU() {
		return a.getDotProduct(u);
	}

	public double getASubV() {
		return a.getDotProduct(v);
	}

	public double getASubW() {
		return a.getDotProduct(w);
	}

	public Vector3D getAXYZ() {
		u.scaleUp(getASubU());
		v.scaleUp(getASubV());
		w.scaleUp(getASubW());
		return u.add(v).add(w);
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
