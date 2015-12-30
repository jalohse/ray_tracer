package me.jessicaalohse.raytracer.images;

import me.jessicaalohse.raytracer.images.color_tests.BlackAndWhiteImage;
import me.jessicaalohse.raytracer.images.color_tests.ColoredSphereImage;
import me.jessicaalohse.raytracer.images.color_tests.MarbleSphereImage;
import me.jessicaalohse.raytracer.images.color_tests.NoiseSphereImage;
import me.jessicaalohse.raytracer.images.light_tests.NoLightImage;
import me.jessicaalohse.raytracer.images.light_tests.OneLightImage;
import me.jessicaalohse.raytracer.images.obj_tests.SixteenObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.OneObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.SnowImage;
import me.jessicaalohse.raytracer.images.obj_tests.ElevenObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.ThreeObjectImage;
import me.jessicaalohse.raytracer.images.obj_tests.TwentyOneObjectImage;
import me.jessicaalohse.raytracer.images.size_tests.LargeImage;
import me.jessicaalohse.raytracer.images.size_tests.MediumImage;
import me.jessicaalohse.raytracer.images.size_tests.SmallImage;
import me.jessicaalohse.raytracer.images.surface_test.SphereImage;
import me.jessicaalohse.raytracer.images.surface_test.TriangleImage;
import me.jessicaalohse.raytracer.images.surface_test.TwoSpheresImage;
import me.jessicaalohse.raytracer.images.surface_test.TwoTrianglesImage;
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
		 new OneObjectImage();
		 new ThreeObjectImage();
		 new ElevenObjectImage();
		 new SixteenObjectImage();
		 new TwentyOneObjectImage();
		 new SnowImage();

		// SURFACE TESTS
		 new SphereImage();
		 new TriangleImage();
		 new TwoSpheresImage();
		 new TwoTrianglesImage();

		// COLOR TESTS
		 new BlackAndWhiteImage();
		 new ColoredSphereImage();
		 new MarbleSphereImage();
		 new NoiseSphereImage();

		// LIGHT TESTS
		 new NoLightImage();
		 new OneLightImage();
		
		// TEXTURE TESTS
		new XSmallImage();
		new SmallTextureImage();
		new MediumTextureImage();
		new LargeTextureImage();
		
		// SIZE TESTS
		new LargeImage();
		new MediumImage();
		new SmallImage();

	}

}
