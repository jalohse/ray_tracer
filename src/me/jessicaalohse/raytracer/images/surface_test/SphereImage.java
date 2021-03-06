package me.jessicaalohse.raytracer.images.surface_test;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class SphereImage {

	public SphereImage() {
		int rowsColumns = 500;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(250, 250, 0, 100.0f, new RGB(255, 255, 255), 0);
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("SuTSphereImage");
			System.out.println("Created SuTSphereImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
