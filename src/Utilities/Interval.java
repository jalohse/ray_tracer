package Utilities;

public class Interval {

	double[] pair = new double[2];

	public Interval(double a, double b) {
		pair[0] = a;
		pair[1] = b;
	}

	public double getA() {
		return pair[0];
	}

	public double getB() {
		return pair[1];
	}

	public boolean isScalarInInterval(int scalar) {
		if (getA() <= scalar && scalar <= getB()) {
			return true;
		} else {
			return false;
		}
	}

	public boolean doScalarsOverlap(Interval otherInterval) {
		double otherA = otherInterval.getA();
		if ((getA() <= otherA && otherA <= getB())
				|| (otherA <= getA() && getA() <= otherInterval.getB())) {
			return true;
		} else {
			return false;
		}
	}

	public Interval getOverlap(Interval otherInterval) {
		if (this.doScalarsOverlap(otherInterval)) {
			double maxA = Math.max(getA(), otherInterval.getA());
			double minB = Math.min(getB(), otherInterval.getB());
			return new Interval(maxA, minB);
		} else {
			return new Interval(0, 0);
		}
	}

}
