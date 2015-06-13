package me.jessicaalohse.raytracer.utilities;

public class PinholeCamera {

	double[] eyepoint = new double[3];
	Vector3D gazeVector;
	Vector3D viewUpVector;
	double distance;
	double aSubU;
	double aSubV;
	double bSubU;
	double bSubV;
	Image image;
	double u;
	double v;

	public PinholeCamera(double[] eyepoint, Vector3D gazeVector,
			Vector3D viewUpVector, double distance, double[] a, double[] b,
			Image image) {
		this.eyepoint = eyepoint;
		this.gazeVector = gazeVector;
		this.viewUpVector = viewUpVector;
		this.distance = distance;
		this.aSubU = a[0];
		this.aSubV = a[1];
		this.bSubU = b[0];
		this.bSubV = b[1];
		this.image = image;
	}

	public OrthonormalBasis makeUVWFrame() {
		OrthonormalBasis frame = new OrthonormalBasis();
		Vector3D gaze = this.gazeVector;
		gaze.scaleUp(-1);
		return frame.makeONBFromWV(gaze, this.viewUpVector);
	}

}
