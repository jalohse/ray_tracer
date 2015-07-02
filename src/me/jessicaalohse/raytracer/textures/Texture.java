package me.jessicaalohse.raytracer.textures;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector2D;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public interface Texture {
	
	public RGB getValue(Vector2D vector2, Vector3D vector3);

}
