package me.jessicaalohse.raytracer.utilities;

public interface Surface {
	
	public double getT();
	public boolean hit(Ray ray, double tSubZero, double tSub1);

}
