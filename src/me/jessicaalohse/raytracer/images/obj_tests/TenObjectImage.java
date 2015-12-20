package me.jessicaalohse.raytracer.images.obj_tests;

import java.io.IOException;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.shapes.Triangle;
import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;

public class TenObjectImage {

	int rowsColumns = 501;

	public TenObjectImage() {
		long start = System.currentTimeMillis();
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere head = new Sphere(230, 250, 0, 50.0f, new RGB(255, 255, 255), 0);
		Sphere body = new Sphere(330, 250, 0, 60.0f, new RGB(255, 255, 255), 0);
		Sphere bottom = new Sphere(430, 250, 0, 70.0f, new RGB(255, 255, 255), 0);
		image.addSurface(head);
		image.addSurface(body);
		image.addSurface(bottom);
		Sphere leftEye = new Sphere(220, 230, 0, 10.0f, new RGB(50, 50, 50), 0);
		Sphere rightEye = new Sphere(220, 270, 0, 10.0f, new RGB(50, 50, 50), 0);
		image.addSurface(leftEye);
		image.addSurface(rightEye);
		Triangle nose = new Triangle(new double[] { 230, 240, 0 }, new double[] { 230, 260, 0 },
				new double[] { 265, 250, 0 }, new RGB(255, 127, 0), 0);
		image.addSurface(nose);
		Sphere button1 = new Sphere(300, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
		Sphere button2 = new Sphere(320, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
		Sphere button3 = new Sphere(340, 250, 0, 5.0f, new RGB(50, 50, 50), 0);
		image.addSurface(button1);
		image.addSurface(button2);
		image.addSurface(button3);
		Triangle leftArm = new Triangle(new double[] { 260, 150, 0 }, new double[] { 270, 180, 0 },
				new double[] { 335, 210, -100 }, new RGB(153, 102, 51), 0);
		Triangle rightArm = new Triangle(new double[] { 260, 350, 0 }, new double[] { 270, 320, 0 },
				new double[] { 335, 280, -100 }, new RGB(153, 102, 51), 0);
		image.addSurface(leftArm);
		image.addSurface(rightArm);
		image.createImage();
		try {
			image.printImage("TenObject");
			System.out.println("Created TenObject.png");
			System.out.printf("Printing took %d milliseconds.\n", System.currentTimeMillis() - start);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
