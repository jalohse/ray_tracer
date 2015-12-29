package me.jessicaalohse.raytracer.images.surface_test;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class TwoTrianglesImage {
	public TwoTrianglesImage() {
		int rowsColumns = 500;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Triangle triangleOne = new Triangle(new double[] { 100, 100, 0 }, new double[] { 350, 100, 0 },
				new double[] { 100, 351.33, 0 }, new RGB(255, 255, 255), 0);
		Triangle triangleTwo = new Triangle(new double[] { 400, 100, 0 }, new double[] { 400, 351.33, 0 },
				new double[] { 150, 351.33, 0 }, new RGB(255, 255, 255), 0);
		image.addSurface(triangleOne);
		image.addSurface(triangleTwo);
		image.createImage();
		try {
			image.printImage("TwoTrianglesImage");
			System.out.println("Created TwoTrianglesImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
