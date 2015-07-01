package me.jessicaalohse.raytracer.utilities;

public class PinholeCamera implements Camera {

	Frame frame;
	Vector3D a, b, c;
	double s;

	public PinholeCamera(Vector3D origin, Vector3D gaze, Vector3D viewUp,
			Vector2D bottomLeftCorner, Vector2D topRightCorner, double distance) {
		initReferenceFrame(origin, gaze, viewUp);
		initFilmPane(bottomLeftCorner, topRightCorner);
		this.s = distance;
	}

	private void initReferenceFrame(Vector3D origin, Vector3D gaze,
			Vector3D viewUp) {
		OrthonormalBasis uvw = OrthonormalBasis.constructFromWV(
				gaze.scaleUp(-1), viewUp);
		this.frame = new Frame(uvw, origin);
	}

	private void initFilmPane(Vector2D bottomLeft, Vector2D topRight) {
		a = this.frame.uvw.getU().scaleUp(topRight.getX() - bottomLeft.getX());
		b = this.frame.uvw.getV().scaleUp(topRight.getY() - bottomLeft.getY());

		Vector3D uPlus = this.frame.uvw.getU().scaleUp(bottomLeft.getX());
		Vector3D vPlus = this.frame.uvw.getV().scaleUp(bottomLeft.getY());
		Vector3D negativeW = this.frame.uvw.getW().scaleUp(-1).scaleUp(s);
		c = uPlus.add(vPlus).add(negativeW);
	}

	public Ray getRay(double x, double y, double nSubX, double nSubY) {
		double a = (x + 0.5) / nSubX;
		double b = (y + 0.5) / nSubY;
		Vector3D aScale = this.a.scaleUp(a);
		Vector3D bScale = this.b.scaleUp(b);
		Vector3D s = c.add(aScale).add(bScale);
		return new Ray(this.frame.origin, s.subtract(this.frame.origin));
	}

}
