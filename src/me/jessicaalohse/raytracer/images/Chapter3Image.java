package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.Light;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Chapter3Image {

	public Chapter3Image() {

		int rowsColumns = 101;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere largeSphere = new Sphere(50, -80, -1000, 100, new RGB(0, 0, 255), 0);
		Sphere smallSphere = new Sphere(50, 50, -1000, 30, new RGB(255, 0, 255), 0);
		image.addSurface(largeSphere);
		image.addSurface(smallSphere);
		Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
		image.addLight(light);
		image.createImage();
		try {
			image.printImage("Chapter3Image");
			System.out.println("Created Chapter3Image.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
