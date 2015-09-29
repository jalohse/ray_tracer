package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.MarbleTexture;
import me.jessicaalohse.raytracer.utilities.RGB;

public class Chapter5Image {

	int rowsColumns = 101;

	public Chapter5Image() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(50, 50, -150, 50f, new RGB(215, 215, 215),
				0);
		sphere.addTexture(new MarbleTexture(0.15f, 5, 8));
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("Chapter5Image");
			System.out.println("Created Chapter5Image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
