package me.jessicaalohse.raytracer.images;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.ImageTexture;
import me.jessicaalohse.raytracer.utilities.RGB;

public class GlobeImage {
	
	public GlobeImage() {
		Sphere sphere = new Sphere(50, 50, -150, 50f, new RGB(215, 215, 215),
				0);
		sphere.addTexture(new ImageTexture("texture/map.jpg"));

	}

}
