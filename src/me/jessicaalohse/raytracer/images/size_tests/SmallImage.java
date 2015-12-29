package me.jessicaalohse.raytracer.images.size_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class SmallImage {
	public SmallImage() {
		int rowsColumns = 125;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Triangle triangle = new Triangle(new double[] { 125, 0, 0 }, new double[] { 125, 125, 0 },
				new double[] { 0, 125, 0 }, new RGB(255, 0, 255), 0);
		image.addSurface(triangle);
		image.createImage();
		try {
			image.printImage("STSmallImage");
			System.out.println("Created STSmallImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
