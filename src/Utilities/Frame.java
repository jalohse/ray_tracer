package utilities;

public class Frame {
	
	OrthonormalBasis base;
	double[] point = new double[3];
	
	public Frame(OrthonormalBasis base, double[] point) {
		this.base = base;
		this.point = point;
	}

	public OrthonormalBasis getBase() {
		return base;
	}

	public double[] getPoint() {
		return point;
	}
	
}
