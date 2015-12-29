package me.jessicaalohse.raytracer.images.color_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.MarbleTexture;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class MarbleSphereImage {
	public MarbleSphereImage() {
		int rowsColumns = 500;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(250, 250, 0, 100.0f, new RGB(0, 255, 0), 0);
		sphere.addTexture(new MarbleTexture(0.15f, 5, 8));
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("MarbleSphereImage");
			System.out.println("Created MarbleSphereImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
