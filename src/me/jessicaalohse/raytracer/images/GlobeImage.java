package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.ImageTexture;
import me.jessicaalohse.raytracer.utilities.RGB;

public class GlobeImage {
	
	int rowsColumns = 1001;
	
	public GlobeImage() {
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(500, 500, -1500, 500f, new RGB(215, 215, 215),
				0);
		sphere.addTexture(new ImageTexture("texture/map.jpg"));
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("GlobeImage");
			System.out.println("Created GlobeImage.png");
			System.out.printf("Printing took %d milliseconds.", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
