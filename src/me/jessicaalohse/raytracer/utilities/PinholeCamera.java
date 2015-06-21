package me.jessicaalohse.raytracer.utilities;

public class PinholeCamera {

	float[] eyepoint = new float[3];
	Vector3D gazeVector;
	Vector3D viewUpVector;
	double distance;
	double aSubU;
	double aSubV;
	double bSubU;
	double bSubV;
	int width;
	int height;

	public PinholeCamera(float[] eyepoint, Vector3D gazeVector,
			Vector3D viewUpVector, double distance, double[] a, double[] b) {
		this.eyepoint = eyepoint;
		this.gazeVector = gazeVector;
		this.viewUpVector = viewUpVector;
		this.distance = distance;
		this.aSubU = a[0];
		this.aSubV = a[1];
		this.bSubU = b[0];
		this.bSubV = b[1];
	}

	public void setDimensions(int nSubX, int nSubY) {
		this.width = nSubX;
		this.height = nSubY;
	}

	public Ray createViewingRay(int i, int j) {
		OrthonormalBasis frame = makeUVWFrame();
		Vector3D u = this.findUVector(frame, i);
		Vector3D v = this.findVVector(frame, j);
		Vector3D w = this.findWVector(frame);
		Vector3D rayVector = u.add(v).add(w);
		return new Ray(this.eyepoint, rayVector);
	}

	private OrthonormalBasis makeUVWFrame() {
		Vector3D gaze = this.gazeVector;
		gaze.scaleUp(-1);
		OrthonormalBasis frame = new OrthonormalBasis();
		return frame.constructFromWV(gaze, this.viewUpVector);
	}

	private Vector3D findUVector(OrthonormalBasis frame, int i) {
		double scalar = aSubU + ((bSubU - aSubU) * (i/ (this.width - 1)));
		Vector3D u = frame.getU();
		u.scaleUp(scalar);
		return u;
	}

	private Vector3D findVVector(OrthonormalBasis frame, int j) {
		double scalar = aSubV + ((bSubV - aSubV) * (j/ (this.height - 1)));
		Vector3D v = frame.getV();
		v.scaleUp(scalar);
		return v;
	}

	private Vector3D findWVector(OrthonormalBasis frame) {
		Vector3D w = frame.getW();
		w.scaleUp(this.distance * -1);
		return w;
	}
}
