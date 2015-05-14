package Utilities;

import Jama.Matrix;

public class TransformMatrix {

	Matrix matrix;
	Matrix inverse;

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

	public Vector3D transformAsLocation(double[] point) {
		Matrix pointMatrix = new Matrix(new double[][] { { point[0] },
				{ point[1] }, { point[2] }, { 1 } });
		Matrix result = matrix.times(pointMatrix);
		double[][] array = result.getArray();
		double scale = array[3][0];
		double x = array[0][0];
		double y = array[1][0];
		double z = array[2][0];
		return new Vector3D(x / scale, y / scale, z / scale);
	}
}
