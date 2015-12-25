package me.jessicaalohse.raytracer.images;

import me.jessicaalohse.raytracer.images.obj_tests.FifteenObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.TenObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.OneObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.SnowImage;
import me.jessicaalohse.raytracer.images.obj_tests.ThreeObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.TwentyOneObjectImage;
import me.jessicaalohse.raytracer.images.surface_test.SphereImage;
import me.jessicaalohse.raytracer.images.surface_test.TriangleImage;
import me.jessicaalohse.raytracer.images.surface_test.TwoSpheresImage;
import me.jessicaalohse.raytracer.images.surface_test.TwoTrianglesImage;

public class Tests {

	public static void main(String[] args) {
		// new Chapter2Image();
		// new Chapter3SecondEdImage();
		// new Chapter3Image();
		// new Chapter4Image();
		// new Chapter5Image();
		// new GlobeImage();

		// OBJ TESTS
		// new OneObjectImage();
		// new ThreeObjectImage();
		// new TenObjectImage();
		// new FifteenObjectImage();
		// new TwentyOneObjectImage();
		// new SnowImage();

		// SURFACE TESTS
		new SphereImage();
		new TriangleImage();
		new TwoSpheresImage();
		new TwoTrianglesImage();
	}

}
