package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;

public interface Surface {
	
	public double getT();
	public boolean hit(Ray ray, double tSubZero, double tSub1, float time);
	public RGB getColor();
	public double getReflectance();
	public boolean shadowHit(Ray ray, float tSubZero, float tSub1, float time);
	

}
