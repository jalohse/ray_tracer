package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.utilities.Ray;

public interface Surface {
	
	public double getT();
	public boolean hit(Ray ray, double tSubZero, double tSub1);
	
	

}
