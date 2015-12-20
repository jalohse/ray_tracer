package me.jessicaalohse.raytracer.images.obj_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class ThreeObjectImage {

	int rowsColumns = 501;

	public ThreeObjectImage() {
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere head = new Sphere(230, 250, 0, 50.0f, new RGB(255, 255, 255), 0);
		Sphere body = new Sphere(330, 250, 0, 60.0f, new RGB(255, 255, 255), 0);
		Sphere bottom = new Sphere(430, 250, 0, 70.0f, new RGB(255, 255, 255), 0);
		image.addSurface(head);
		image.addSurface(body);
		image.addSurface(bottom);
		image.createImage();
		try {
			image.printImage("ThreeObject");
			System.out.println("Created ThreeObject.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
