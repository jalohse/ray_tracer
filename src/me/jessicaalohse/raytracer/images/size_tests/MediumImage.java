package me.jessicaalohse.raytracer.images.size_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class MediumImage {
	public MediumImage() {
		int rowsColumns = 250;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Triangle triangle = new Triangle(new double[] { 250, 0, 0 }, new double[] { 250, 250, 0 },
				new double[] { 0, 250, 0 }, new RGB(255, 0, 255), 0);
		image.addSurface(triangle);
		image.createImage();
		try {
			image.printImage("STMediumImage");
			System.out.println("Created STMediumImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
