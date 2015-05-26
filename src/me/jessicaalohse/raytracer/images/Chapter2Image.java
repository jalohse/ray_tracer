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

public class Chapter2Image {

	int rowsColumns = 101;

	public Chapter2Image() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(0, 0, -150, 100.1f);
		Triangle triangle = new Triangle(new double[] { 0, 0, -100 },
				new double[] { 100.1, 0, -100 },
				new double[] { 50, 100.1, -100 });
		SurfaceList list = new SurfaceList();
		list.add(sphere);
		list.add(triangle);
		RGB lightGray = new RGB(215, 215, 215);
		RGB darkGray = new RGB(112, 112, 112);
		RGB black = new RGB(0, 0, 0);
		RGB[][] pixels = new RGB[rowsColumns][rowsColumns];
		for (int i = 0; i < rowsColumns; i++) {
			for (int j = 0; j < rowsColumns; j++) {
				float[] origin = new float[] { i, j, 0 };
				Ray ray = new Ray(origin, new Vector3D(0, 0, -1));
				if (list.hit(ray, 0, 1000000)) {
					Surface hitSurface = list.getPrim();
					if (hitSurface instanceof Sphere) {
						pixels[i][j] = lightGray;
					} else if (hitSurface instanceof Triangle) {
						pixels[i][j] = darkGray;
					} else {
						pixels[i][j] = black;
					}
				}
			}
		}
		image.populateImage(pixels);
		try {
			image.printImage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
