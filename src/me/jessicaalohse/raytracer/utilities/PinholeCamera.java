package me.jessicaalohse.raytracer.utilities;

public class PinholeCamera implements Camera {

	ReferenceFrame frame = new ReferenceFrame();
	Vector3D a, b, c;
	double s;

	public PinholeCamera(Vector3D origin, Vector3D gaze, Vector3D viewUp,
			Vector2D bottomLeftCorner, Vector2D topRightCorner, double distance) {
		initReferenceFrame(origin, gaze, viewUp);
		initFilmPane(bottomLeftCorner, topRightCorner);
		this.s = distance;
	}

	private void initReferenceFrame(Vector3D origin, Vector3D gaze,
			Vector3D viewUp)
	{
		this.frame.origin = origin;
		this.frame.uvw = OrthonormalBasis.constructFromWV(
				(Vector3D) gaze.scaleUp(-1), viewUp);
	}

	private void initFilmPane(Vector2D bottomLeft, Vector2D topRight)
	{
		a = (Vector3D) this.frame.uvw.getU().scaleUp(
				topRight.getX() - bottomLeft.getX());
		b = (Vector3D) this.frame.uvw.getV().scaleUp(
				topRight.getY() - bottomLeft.getY());

		Vector3D uPlus = (Vector3D) this.frame.uvw.getU().scaleUp(
				bottomLeft.getX());
		Vector3D vPlus = (Vector3D) this.frame.uvw.getV().scaleUp(
				bottomLeft.getY());
		Vector3D negativeW = (Vector3D) this.frame.uvw.getW().scaleUp(-1).scaleUp(s);
		c = uPlus.add(vPlus).add(negativeW);
	}

	public Ray getRay(double x, double y, double nSubX, double nSubY)
	{
		double a = (x + 0.5)/nSubX;
		double b = (y + 0.5)/nSubY;
		Vector3D aScale = (Vector3D) this.a.scaleUp(a);
		Vector3D bScale = (Vector3D) this.b.scaleUp(b);
		Vector3D s = c.add(aScale).add(bScale);
		return new Ray(this.frame.origin, s.subtract(this.frame.origin));
	}

}
