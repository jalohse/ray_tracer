package me.jessicaalohse.raytracer.shapes;

import me.jessicaalohse.raytracer.textures.Texture;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public interface Surface {
	
	public double getT();
	public boolean hit(Ray ray, double tSubZero, double tSub1, float time);
	public RGB getColor();
	public float getReflectance();
	public boolean shadowHit(Ray ray, float tSubZero, float tSub1, float time);
	public RGB getAmbientColor(float ambience, Vector3D hitPoint);
	public Texture getTexture();
	

}
