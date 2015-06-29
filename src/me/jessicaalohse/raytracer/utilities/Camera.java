package me.jessicaalohse.raytracer.utilities;

public interface Camera {

	public Ray getRay(double x, double y, double nSubX, double nSubY);
}
