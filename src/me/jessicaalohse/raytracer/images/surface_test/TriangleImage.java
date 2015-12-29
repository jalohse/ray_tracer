package me.jessicaalohse.raytracer.images.surface_test;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class TriangleImage {

	public TriangleImage() {
		int rowsColumns = 500;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Triangle triangle = new Triangle(new double[] { 100, 100, 0 }, new double[] { 350, 100, 0 },
				new double[] { 100, 351.33, 0 }, new RGB(255, 255, 255), 0);
		image.addSurface(triangle);
		image.createImage();
		try {
			image.printImage("TriangleImage");
			System.out.println("Created TriangleImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
