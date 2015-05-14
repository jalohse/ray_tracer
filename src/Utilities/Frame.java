package Utilities;

public class Frame {
	
	OrthonormalBase base;
	double[] point = new double[2];
	
	public Frame(OrthonormalBase base, double[] point) {
		this.base = base;
		this.point = point;
	}

	public OrthonormalBase getBase() {
		return base;
	}

	public double[] getPoint() {
		return point;
	}
	
}
