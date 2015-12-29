package me.jessicaalohse.raytracer.images;

import javafx.scene.media.Media;
import me.jessicaalohse.raytracer.images.light_tests.NoLightImage;
import me.jessicaalohse.raytracer.images.light_tests.OneLightImage;
import me.jessicaalohse.raytracer.images.texture_tests.LargeTextureImage;
import me.jessicaalohse.raytracer.images.texture_tests.MediumTextureImage;
import me.jessicaalohse.raytracer.images.texture_tests.SmallTextureImage;
import me.jessicaalohse.raytracer.images.texture_tests.XSmallImage;

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
		// new SphereImage();
		// new TriangleImage();
		// new TwoSpheresImage();
		// new TwoTrianglesImage();

		// COLOR TESTS
		// new BlackAndWhiteImage();
		// new ColoredSphereImage();

		// LIGHT TESTS
		// new NoLightImage();
		// new OneLightImage();
		
		// TEXTURE TESTS
		new XSmallImage();
		new SmallTextureImage();
		new MediumTextureImage();
		new LargeTextureImage();

	}

}
