package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Sphere;
import me.jessicaalohse.raytracer.utilities.Surface;
import me.jessicaalohse.raytracer.utilities.SurfaceList;
import me.jessicaalohse.raytracer.utilities.Triangle;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Chapter3Image {
	
	int rowsColumns = 101;
	
	public Chapter3Image() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere1 = new Sphere(50, 180, -1000, 100f);
		Sphere sphere2 = new Sphere(50, 50, -1000, 30f);
		SurfaceList list = new SurfaceList();
		list.add(sphere1);
		list.add(sphere2);
		RGB lightGray = new RGB(215, 215, 215);
		RGB darkGray = new RGB(112, 112, 112);
		RGB black = new RGB(0, 0, 0);
		RGB[][] pixels = new RGB[rowsColumns][rowsColumns];
		for (int i = 0; i < rowsColumns; i++) {
			for (int j = 0; j < rowsColumns; j++) {
				float[] origin = new float[] { i, j, 0 };
				Ray ray = new Ray(origin, new Vector3D(0, 0, -1));
				if (list.hit(ray, 0, Integer.MAX_VALUE)) {
					Surface hitSurface = list.getPrim();
					if (hitSurface instanceof Sphere) {
						pixels[i][j] = lightGray;
					} else if (hitSurface instanceof Triangle) {
						pixels[i][j] = darkGray;
					} else {
						pixels[i][j] = black;
					}
				} else {
					pixels[i][j] = black;
				}
			}
		}
		image.populateImage(pixels);
		try {
			image.printImage("image2.png");
			System.out.println("Created image2.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
