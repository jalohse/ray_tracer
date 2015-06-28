package me.jessicaalohse.raytracer.images;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.Light;
import me.jessicaalohse.raytracer.utilities.Camera;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class Chapter4Image {

	int rowsColumns = 101;

	public Chapter4Image() {
		Image image = new Image(rowsColumns, rowsColumns);

		Vector3D eyePoint = new Vector3D(0, 0, 2);
		Vector3D gazeVector = new Vector3D(0, 0, -2);
		Vector3D viewUpVector = new Vector3D(0, 1, 0);
		float[] a = new float[] { -2, -2 };
		float[] b = new float[] { 2, 2 };
		Camera camera = new Camera(eyePoint, gazeVector, viewUpVector, 5f,
				a[0], b[0], a[1], b[1], 2f);
		image.addCamera(camera);

		Sphere sphere = new Sphere(0, 0, 0, (float) Math.sqrt(2), new RGB(215,
				215, 215), 0.9);
		image.addSurface(sphere);

		Light light = new Light(new Vector3D(0, 1, 0), new RGB(255, 255, 255));
		image.addLight(light);
		image.addAmbientLight(0.1f);

		image.createImage();
		try {
			image.printImage("Chapter4Image");
			System.out.println("Created Chapter4Image.png");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
