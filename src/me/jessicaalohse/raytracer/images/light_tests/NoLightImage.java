package me.jessicaalohse.raytracer.images.light_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class NoLightImage {
	public NoLightImage() {
		int rowsColumns = 501;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		RGB lightGray = new RGB(215, 215, 215);
		Sphere largeSphere = new Sphere(250, 120, -1000, 100, lightGray, 0);
		Sphere smallSphere = new Sphere(250, 250, -1000, 30, lightGray, 0);
		image.addSurface(largeSphere);
		image.addSurface(smallSphere);
		image.createImage();
		try {
			image.printImage("NoLightImage");
			System.out.println("Created NoLightImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
