package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.RGB;

public class Chapter3SecondEdImage {

	int rowsColumns = 500;

	public Chapter3SecondEdImage() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(250, 250, -1000, 150, new RGB(0, 0, 255), 0);
		Triangle triangle = new Triangle(new double[] { 300, 600, -800 },
				new double[] { 0, 100, -1000 },
				new double[] { 450, 20, -1000 }, new RGB(255, 0, 0), 0);
		image.addSurface(sphere);
		image.addSurface(triangle);
		image.createImage();
		try {
			image.printImage("Chapter3SecondEdImage");
			System.out.println("Created Chapter3SecondEdImage.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
