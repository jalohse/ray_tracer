package me.jessicaalohse.raytracer.utilities;

public class Light {
	
	private Vector3D lightVector;
	private RGB color;
	
	public Light(Vector3D vector, RGB color) {
		this.lightVector = vector;
		this.color = color;
	}

	public Vector3D getLightVector() {
		return lightVector;
	}

	public RGB getColor() {
		return color;
	}

}
