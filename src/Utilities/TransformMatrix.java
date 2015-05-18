package Utilities;

import Jama.Matrix;

public class TransformMatrix {

	Matrix matrix;
	Matrix inverse;
	Matrix identity = Matrix
			.constructWithCopy(Matrix.identity(4, 4).getArray());

	public TransformMatrix(double[] scalars) {
		double[][] arrayMatrix = new double[4][4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				arrayMatrix[i][j] = scalars[i + j];
			}
		}
		arrayMatrix[3][0] = arrayMatrix[3][1] = arrayMatrix[3][2] = 0;
		arrayMatrix[3][3] = 1;

		matrix = new Matrix(new double[][] { arrayMatrix[0], arrayMatrix[1],
				arrayMatrix[2], arrayMatrix[3] });
		inverse = matrix.inverse();
	}

	/**
	 * Constructs a transformation matrix to translate by a vector
	 * 
	 * @param translation
	 */
	public TransformMatrix(Vector3D translation) {
		identity.set(0, 3, translation.getX());
		identity.set(1, 3, translation.getY());
		identity.set(2, 3, translation.getZ());
		this.matrix = identity;
	}

	/**
	 * Constructs a transformation matrix to scale
	 * 
	 * @param scaleX
	 * @param scaleY
	 * @param scaleZ
	 */
	public TransformMatrix(double scaleX, double scaleY, double scaleZ) {
		identity.set(0, 0, scaleX);
		identity.set(1, 1, scaleY);
		identity.set(2, 2, scaleZ);
		this.matrix = identity;
	}

	public TransformMatrix() {
		// Used to get identity and rotations
	}

	public Matrix getIdentity() {
		return Matrix.identity(4, 4);
	}

	public Matrix rotateXBy(double theta) {
		double cosine = Math.cos(theta);
		double sine = Math.sin(theta);
		identity.set(1, 1, cosine);
		identity.set(1, 2, sine * -1);
		identity.set(2, 1, sine);
		identity.set(2, 2, cosine);
		return identity;
	}

	public Matrix rotateYBy(double theta) {
		double cosine = Math.cos(theta);
		double sine = Math.sin(theta);
		identity.set(0, 0, cosine);
		identity.set(2, 0, sine * -1);
		identity.set(0, 2, sine);
		identity.set(2, 2, cosine);
		return identity;
	}

	public Matrix rotateZBy(double theta) {
		double cosine = Math.cos(theta);
		double sine = Math.sin(theta);
		identity.set(0, 0, cosine);
		identity.set(0, 1, sine * -1);
		identity.set(1, 0, sine);
		identity.set(1, 1, cosine);
		return identity;
	}

	public Vector3D transformMatrixAsLocation(double[] point) {
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix result = matrix.times(pointMatrix);
		double[][] array = result.getArray();
		double scale = array[3][0];
		return new Vector3D(result.get(0, 0) / scale, result.get(1, 0) / scale,
				result.get(2, 0) / scale);
	}

	public Vector3D transformMatrixAsOffset(Vector3D vector) {
		Matrix vectorMatrix = new Matrix(new double[][] { { vector.getX() },
				{ vector.getY() }, { vector.getZ() }, { 0 } });
		Matrix result = matrix.times(vectorMatrix);
		return new Vector3D(result.get(0, 0), result.get(1, 0),
				result.get(2, 0));
	}

	public Vector3D transformMatrixAsNormal(Vector3D vector) {
		Matrix vectorMatrix = new Matrix(new double[][] { { vector.getX() },
				{ vector.getY() }, { vector.getZ() }, { 0 } });
		Matrix result = inverse.transpose().times(vectorMatrix);
		return new Vector3D(result.get(0, 0), result.get(1, 0),
				result.get(2, 0));
	}

	public Vector3D transformInverseAsLocation(double[] point) {
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix result = inverse.times(pointMatrix);
		double[][] array = result.getArray();
		double scale = array[3][0];
		return new Vector3D(result.get(0, 0) / scale, result.get(1, 0) / scale,
				result.get(2, 0) / scale);
	}

	public Vector3D transformInverseAsOffset(Vector3D vector) {
		Matrix vectorMatrix = new Matrix(new double[][] { { vector.getX() },
				{ vector.getY() }, { vector.getZ() }, { 0 } });
		Matrix result = inverse.times(vectorMatrix);
		return new Vector3D(result.get(0, 0), result.get(1, 0),
				result.get(2, 0));
	}

	public Vector3D transformInverseAsNormal(Vector3D vector) {
		Matrix vectorMatrix = new Matrix(new double[][] { { vector.getX() },
				{ vector.getY() }, { vector.getZ() }, { 0 } });
		Matrix result = matrix.transpose().times(vectorMatrix);
		return new Vector3D(result.get(0, 0), result.get(1, 0),
				result.get(2, 0));
	}

	public Matrix rotateUVWToXYZ(OrthonormalBasis uvw) {
		Vector3D u = uvw.getU();
		Vector3D v = uvw.getV();
		Vector3D w = uvw.getW();
		return new Matrix(new double[][] { { u.getX(), u.getY(), u.getZ(), 0 },
				{ v.getX(), v.getY(), v.getZ(), 0 },
				{ w.getX(), w.getY(), w.getZ(), 0 }, { 0, 0, 0, 1 } });
	}

	public Matrix rotateXYZToUVW(OrthonormalBasis uvw) {
		Vector3D u = uvw.getU();
		Vector3D v = uvw.getV();
		Vector3D w = uvw.getW();
		return new Matrix(new double[][] { { u.getX(), v.getX(), w.getX(), 0 },
				{ u.getY(), v.getY(), w.getY(), 0 },
				{ u.getZ(), v.getZ(), w.getZ(), 0 }, { 0, 0, 0, 1 } });
	}

	public double[] moveXYZPointToNewOrigin(double[] point, double[] newOrigin) {
		identity.set(0, 3, newOrigin[0] * -1);
		identity.set(1, 3, newOrigin[1] * -1);
		identity.set(2, 3, newOrigin[2] * -1);
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix newPoint = identity.times(pointMatrix);
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}

	public double[] moveXYZPointBackToOriginalOrigin(double[] point,
			double[] newOrigin) {
		identity.set(0, 3, newOrigin[0]);
		identity.set(1, 3, newOrigin[1]);
		identity.set(2, 3, newOrigin[2]);
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix newPoint = identity.times(pointMatrix);
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}

	public double[] getXYZinUVWCoordinate(OrthonormalBasis uvw, double[] point) {
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix newPoint = rotateXYZToUVW(uvw).times(pointMatrix);
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}

	public double[] getUVWinXYZCoordinate(OrthonormalBasis uvw, double[] point) {
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix newPoint = rotateUVWToXYZ(uvw).times(pointMatrix);
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}

	public double[] moveUVWToNewOrigin(OrthonormalBasis uvw, double[] point,
			double[] newOrigin) {
		identity.set(0, 3, newOrigin[0]);
		identity.set(1, 3, newOrigin[1]);
		identity.set(2, 3, newOrigin[2]);
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix xyz = rotateXYZToUVW(uvw).times(pointMatrix);
		Matrix newPoint = identity.times(xyz);
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}

	public double[] moveUVWBackToOrigin(OrthonormalBasis uvw, double[] point,
			double[] newOrigin) {
		identity.set(0, 3, newOrigin[0] * -1);
		identity.set(1, 3, newOrigin[1] * -1);
		identity.set(2, 3, newOrigin[2] * -1);
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix newPoint = rotateUVWToXYZ(uvw)
				.times(identity.times(pointMatrix));
		return new double[] { newPoint.get(0, 0), newPoint.get(1, 0),
				newPoint.get(2, 0) };
	}
}
