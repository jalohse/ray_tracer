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

	public void clamp() {
		if (red > 255)
			red = 255;
		if (green > 255)
			green = 255;
		if (blue > 255)
			blue = 255;
		if (red < 0)
			red = 0;
		if (green < 0)
			green = 0;
		if (blue < 0)
			blue = 0;
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

	public RGB multiplyByScalar(float scale) {
		RGB newColor = new RGB(determineScaled(this.red, scale), determineScaled(this.green, scale),
				determineScaled(this.blue, scale));
		newColor.clamp();
		return newColor;
	}

	private int determineScaled(int color, float scale) {
		int scaled = (int) (color * scale);
		if (scaled <= color) {
			return scaled;
		} else {
			return scaled / 255;
		}
	}

	public RGB add(RGB b) {
		int red = this.red + b.red;
		int green = this.green + b.green;
		int blue = this.blue + b.blue;
		RGB newColor = new RGB(red, green, blue);
		newColor.clamp();
		return newColor;
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
		RGB newColor = new RGB(red, green, blue);
		newColor.clamp();
		return newColor;
	}

	public RGB divide(RGB b) {
		int red = this.red / b.red;
		int green = this.green / b.green;
		int blue = this.blue / b.blue;
		RGB newColor = new RGB(red, green, blue);
		newColor.clamp();
		return newColor;
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
