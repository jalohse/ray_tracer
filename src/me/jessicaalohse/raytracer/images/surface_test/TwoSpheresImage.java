package me.jessicaalohse.raytracer.images.surface_test;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class TwoSpheresImage {
	public TwoSpheresImage() {
		int rowsColumns = 501;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphereOne = new Sphere(150, 150, 0, 100.0f, new RGB(255, 255, 255), 0);
		Sphere sphereTwo = new Sphere(300, 300, 0, 100.0f, new RGB(255, 255, 255), 0);
		image.addSurface(sphereOne);
		image.addSurface(sphereTwo);
		image.createImage();
		try {
			image.printImage("TwoSpheresImage");
			System.out.println("Created TwoSpheresImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
