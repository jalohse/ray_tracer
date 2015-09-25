package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.ImageTexture;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class GlobeImage {
	
	int rowsColumns = 1001;
	
	public GlobeImage() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(500, 500, -1500, 500f, new RGB(215, 215, 215),
				0);
		sphere.addTexture(new ImageTexture("texture/map.jpg"));
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("GlobeImage");
			System.out.println("Created GlobeImage.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
