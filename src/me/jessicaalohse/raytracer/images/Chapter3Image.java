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

		Image image = new Image(rowsColumns, rowsColumns);
		RGB lightGray = new RGB(215, 215, 215);
		Sphere largeSphere = new Sphere(50, -80, -1000, 100, lightGray);
		Sphere smallSphere = new Sphere(50, 50, -1000, 30, lightGray);
		image.addSurface(largeSphere);
		image.addSurface(smallSphere);
		Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
		image.addLight(light);
		image.createImage();
		try {
			image.printImage("Chapter3Image");
			System.out.println("Created Chapter3Image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
