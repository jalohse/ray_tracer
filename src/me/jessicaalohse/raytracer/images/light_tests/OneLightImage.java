package me.jessicaalohse.raytracer.images.light_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.Light;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class OneLightImage {
	public OneLightImage() {
		int rowsColumns = 500;
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		RGB lightGray = new RGB(215, 215, 215);
		Sphere largeSphere = new Sphere(250, 120, -1000, 100, new RGB(0, 0, 255), 0);
		Sphere smallSphere = new Sphere(250, 250, -1000, 30, new RGB(255, 255, 0), 0);
		image.addSurface(largeSphere);
		image.addSurface(smallSphere);
		Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
		image.addLight(light);
		image.createImage();
		try {
			image.printImage("LTOneLightImage");
			System.out.println("Created LTOneLightImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
