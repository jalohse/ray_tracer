package me.jessicaalohse.raytracer.images.obj_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class OneObjectImage {

	int rowsColumns = 501;
	
	public OneObjectImage() {
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(250, 250, 0, 50.0f, new RGB(255, 255, 255),
				0);
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("OneObject");
			System.out.println("Created OneObject.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
