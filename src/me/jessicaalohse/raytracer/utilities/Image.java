package me.jessicaalohse.raytracer.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.jessicaalohse.raytracer.shapes.Sphere;
import me.jessicaalohse.raytracer.shapes.Surface;
import me.jessicaalohse.raytracer.shapes.SurfaceList;
import me.jessicaalohse.raytracer.shapes.Triangle;

public class Image {

	int rows;
	int columns;
	RGB[][] image;
	private SurfaceList surfaces = new SurfaceList();
	private Light light;

	public Image(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.image = new RGB[rows][columns];
	}

	public void addSurface(Surface surface) {
		surfaces.add(surface);
	}

	public void addLight(Light light) {
		this.light = light;
	}

	public void createImage() {
		RGB black = new RGB(0, 0, 0);
		RGB[][] pixels = new RGB[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				float[] origin = new float[] { i, j, 0 };
				Ray ray = new Ray(origin, new Vector3D(0, 0, -1));
				if (this.surfaces.hit(ray, 0, Integer.MAX_VALUE)) {
					pixels[i][j] = getHitColor(ray);
				} else {
					pixels[i][j] = black;
				}
			}
		}
		populateImage(pixels);
	}

	public RGB getHitColor(Ray ray) {
		Surface hitSurface = this.surfaces.getPrim();
		if (light != null) {
			if (!isHitByShadowRay(ray, hitSurface)) {
				RGB multipliedLight = light.getColor().multiply(
						hitSurface.getColor());
				if (hitSurface instanceof Sphere) {
					Sphere sphere = (Sphere) hitSurface;
					return sphere.getLitColor(multipliedLight, ray.getOrigin(),
							light.getLightVector());
				} else {
					Triangle triangle = (Triangle) hitSurface;
					return triangle.getLitColor(multipliedLight,
							light.getLightVector());
				}
			} else {
				return new RGB(0, 0, 0);
			}
		} else {
			return hitSurface.getColor();
		}
	}

	private boolean isHitByShadowRay(Ray ray, Surface hitSurface) {
		float[] originOfShadowRay = ray.pointAtParameter(hitSurface.getT());
		Ray shadowRay = new Ray(originOfShadowRay, light.getLightVector());
		return this.surfaces.hit(shadowRay, 0.001, Integer.MAX_VALUE);
	}

	public void populateImage(RGB[][] pixels) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				image[i][j] = pixels[i][j];
			}
		}
	}

	public void printImage(String imageName) throws IOException {
		BufferedImage img = new BufferedImage(rows, columns,
				BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				RGB rgb = image[i][j];
				int color = (rgb.red << 16) | (rgb.green << 8) | rgb.blue;
				img.setRGB(i, j, color);
			}
		}
		File file;
		if (imageName == null || imageName.isEmpty()) {
			file = new File("image.png");
		} else {
			file = new File(imageName.concat(".png"));
		}
		ImageIO.write(img, "PNG", file);
	}

	public void printImage() throws IOException {
		this.printImage("");
	}

}
