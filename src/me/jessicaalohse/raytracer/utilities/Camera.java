package me.jessicaalohse.raytracer.utilities;

public class Camera {

	Vector3D eyePoint;
	Vector3D gazeVector;
	Vector3D vUpVector;
	float uSub0;
	float vSub0;
	float uSub1;
	float vSub1;
	float distance;
	float lensRadius;
	OrthonormalBasis uvw;
	Vector3D corner;
	Vector3D across;
	Vector3D up;

	public Camera(Vector3D eyePoint, Vector3D gazeVector, Vector3D vUpVector,
			float aperature, float left, float right, float bottom, float top,
			float distance) {
		this.eyePoint = eyePoint;
		this.gazeVector = gazeVector;
		this.vUpVector = vUpVector;
		this.uSub0 = left;
		this.uSub1 = right;
		this.vSub0 = bottom;
		this.vSub1 = top;
		this.distance = distance;
		this.lensRadius = aperature / 2.0f;
		createUVWSystem();
	}

	private void createUVWSystem() {
		Vector3D negativeGaze = (Vector3D) this.gazeVector.scaleUp(-1);
		this.uvw = new OrthonormalBasis().constructFromWV(negativeGaze,
				this.vUpVector);

		Vector3D cornerU = (Vector3D) this.uvw.getU().scaleUp(this.uSub0);
		Vector3D cornerV = (Vector3D) this.uvw.getV().scaleUp(this.vSub0);
		Vector3D cornerW = (Vector3D) this.uvw.getW().scaleUp(distance * -1);
		this.corner = this.eyePoint.add(cornerU).add(cornerV).add(cornerW);

		this.across = (Vector3D) this.uvw.getU().scaleUp(uSub0 - uSub1);
		this.up = (Vector3D) this.uvw.getV().scaleUp(vSub0 - vSub1);
	}

	public Ray getRay(float xPixel, float yPixel, float lensSample1,
			float lensSample2) {
		Vector3D lensSample1Calc = (Vector3D) this.uvw.getU().scaleUp(
				2.0f * (lensSample1 - 0.5f) * this.lensRadius);
		Vector3D lensSample2Calc = (Vector3D) this.uvw.getV().scaleUp(
				2.0f * (lensSample2 - 0.5f) * this.lensRadius);
		Vector3D origin = this.eyePoint.add(lensSample1Calc).add(
				lensSample2Calc);
		Vector3D target = this.corner.add(
				(Vector3D) this.across.scaleUp(xPixel)).add(
				(Vector3D) this.up.scaleUp(yPixel));
		return new Ray(origin, target.subtract(origin).makeUnitVector());

	}
}
