package me.jessicaalohse.raytracer.utilities;

public class Frame {
	
	OrthonormalBasis uvw;
	Vector3D origin;
	
	public Frame(OrthonormalBasis uvw, Vector3D origin) {
		this.uvw = uvw;
		this.origin = origin;
	}

	public OrthonormalBasis getUVW() {
		return uvw;
	}

	public Vector3D getOrigin() {
		return origin;
	}
	
}
