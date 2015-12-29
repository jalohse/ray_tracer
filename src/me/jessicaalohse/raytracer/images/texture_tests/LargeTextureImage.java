package me.jessicaalohse.raytracer.images.texture_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.textures.ImageTexture;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class LargeTextureImage {
	public LargeTextureImage() {
		int rowsColumns = 500;

		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(250, 250, -1500, 250f, new RGB(215, 215, 215), 0);
		sphere.addTexture(new ImageTexture("texture/largeMap.jpg"));
		image.addSurface(sphere);
		image.createImage();
		try {
			image.printImage("TTLargeImage");
			System.out.println("Created TTLargeImage.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
