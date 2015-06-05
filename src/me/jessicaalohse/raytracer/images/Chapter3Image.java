package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.shapes.Surface;
import me.jessicaalohse.raytracer.shapes.SurfaceList;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Chapter3Image {

	public Chapter3Image() {

		int rowsColumns = 101;

		Image image = new Image(rowsColumns, rowsColumns);
		Sphere largeSphere = new Sphere(50, -80, -1000, 100);
		Sphere smallSphere = new Sphere(50, 50, -1000, 30);
		SurfaceList list = new SurfaceList();
		list.add(largeSphere);
		list.add(smallSphere);
		Vector3D lightVector = new Vector3D(0, 1, 0);
		RGB lightColor = new RGB(255, 255, 255);
		RGB lightGray = new RGB(215, 215, 215);
		RGB black = new RGB(0, 0, 0);
		RGB[][] pixels = new RGB[rowsColumns][rowsColumns];
		for (int i = 0; i < rowsColumns; i++) {
			for (int j = 0; j < rowsColumns; j++) {
				float[] origin = new float[] { i, j, 0 };
				Ray ray = new Ray(origin, new Vector3D(0, 0, -1));
				if (list.hit(ray, 0, Integer.MAX_VALUE)) {
					Surface hitSurface = list.getPrim();
					if (hitSurface instanceof Sphere) {
						RGB multipliedLight = lightColor.multiply(lightGray);
						RGB color = ((Sphere) hitSurface).getLitColor(
								multipliedLight, origin, lightVector);
						pixels[i][j] = color;
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
			image.printImage("Chapter3Image");
			System.out.println("Created Chapter3Image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
