package me.jessicaalohse.raytracer.utilities;

public class RGB {

	public static final int MAX_BYTE = 255;
	public static final int BYTE_MULTIPLICATION = 256;
	public static final double MAC_GAMMA = 1.8;
	public static final double WINDOWS_GAMMA = 2.2;

	int red;
	int green;
	int blue;

	public RGB(int red, int green, int blue) {
		this.red = red;
		this.green = green;
		this.blue = blue;
	}

	public void makePositive() {
		this.setRed(Math.abs(this.red));
		this.setGreen((this.green));
		this.setRed(Math.abs(this.blue));
	}

	public void makeNegative() {
		this.setRed(this.red * -1);
		this.setGreen(this.green * -1);
		this.setBlue(this.blue * -1);
	}

	public void scaleUp(float scale) {
		if (scale < 0) {
			scale = 0;
		}
		if (scale < 1) {
			this.setRed((int) (this.red * scale));
			this.setGreen((int) (this.green * scale));
			this.setBlue((int) (this.blue * scale));
		} else {
			this.setRed((int) (this.red * scale) / 255);
			this.setGreen((int) (this.green * scale) / 255);
			this.setBlue((int) (this.blue * scale) / 255);
		}
	}

	public void scaleDown(int scale) {
		this.setRed(this.red / scale);
		this.setGreen(this.green / scale);
		this.setBlue(this.blue / scale);
	}

	public RGB add(RGB b) {
		int red = this.red + b.red;
		int green = this.green + b.green;
		int blue = this.blue + b.blue;
		return new RGB(red, green, blue);
	}

	public RGB subtract(RGB b) {
		int red = this.red - b.red;
		int green = this.green - b.green;
		int blue = this.blue - b.blue;
		return new RGB(red, green, blue);
	}

	public RGB multiply(RGB b) {
		int red = (this.red * b.red) / 255;
		int green = (this.green * b.green) / 255;
		int blue = (this.blue * b.blue) / 255;
		return new RGB(red, green, blue);
	}

	public RGB divide(RGB b) {
		int red = this.red / b.red;
		int green = this.green / b.green;
		int blue = this.blue / b.blue;
		return new RGB(red, green, blue);
	}

	public RGB getByteForm() {
		int red = colorToByte(this.red);
		int green = colorToByte(this.green);
		int blue = colorToByte(this.blue);
		return new RGB(red, green, blue);
	}

	private int colorToByte(int color) {
		int i = (int) (BYTE_MULTIPLICATION * Math.pow(color, 1 / MAC_GAMMA));
		if (i > MAX_BYTE) {
			return MAX_BYTE;
		} else {
			return i;
		}
	}

	public int getRed() {
		return red;
	}

	public void setRed(int red) {
		this.red = red;
	}

	public int getGreen() {
		return green;
	}

	public void setGreen(int green) {
		this.green = green;
	}

	public int getBlue() {
		return blue;
	}

	public void setBlue(int blue) {
		this.blue = blue;
	}

	public String toString() {
		return String.format("[%d, %d, %d]", red, green, blue);
	}

}
