package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class Chapter2Image {

	int rowsColumns = 101;

	public Chapter2Image() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(0, 0, -150, 100.1f, new RGB(215, 215, 215));
		Triangle triangle = new Triangle(new double[] { 0, 0, -100 },
				new double[] { 100.1, 0, -100 },
				new double[] { 50, 100.1, -100 }, new RGB(112, 112, 112));
		image.addSurface(sphere);
		image.addSurface(triangle);
		image.createImage();
		try {
			image.printImage("Chapter2Image");
			System.out.println("Created Chapter2Image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
