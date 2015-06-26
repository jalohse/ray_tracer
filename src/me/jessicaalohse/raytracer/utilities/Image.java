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
	private PinholeCamera camera;
	private float ambience;

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

	public void addCamera(PinholeCamera camera) {
		this.camera = camera;
	}

	public void addAmbientLight(float ambience) {
		this.ambience = ambience;
	}

	public void createImage() {
		RGB[][] pixels = new RGB[this.rows][this.columns];
		for (int i = 0; i < this.rows; i++) {
			for (int j = 0; j < this.columns; j++) {
				Ray ray = this.createRay(i, j);
				if (this.surfaces.hit(ray, 0, Integer.MAX_VALUE, 0)) {
					pixels[i][j] = getHitColor(ray);
				} else {
					pixels[i][j] = getAmbientBlack();
				}
			}
		}
		populateImage(pixels);
	}

	private RGB getAmbientBlack() {
		int ambientBlack = (int) (0 + (this.ambience * 255));
		return new RGB(ambientBlack, ambientBlack, ambientBlack);
	}

	private Ray createRay(int i, int j) {
		if (this.camera == null) {
			float[] origin = new float[] { i, j, 0 };
			return new Ray(origin, new Vector3D(0, 0, -1));
		} else {
			return this.camera.createViewingRay(i, j);
		}
	}

	public RGB getHitColor(Ray ray) {
		Surface hitSurface = this.surfaces.getPrim();
		RGB hitSurfaceColor = this.getAdjustedColor(hitSurface);
		if (this.light != null) {
			if (!isHitByShadowRay(ray, hitSurface)) {
				RGB multipliedLight = light.getColor()
						.multiply(hitSurfaceColor);
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
				return getAmbientBlack();
			}
		} else {
			return hitSurfaceColor;
		}
	}

	private RGB getAdjustedColor(Surface hitSurface) {
		RGB original = hitSurface.getColor();
		if (this.ambience != 0) {
			float adjustment = this.ambience * 255;
			int red = (int) (original.getRed() + adjustment);
			int green = (int) (original.getGreen() + adjustment);
			int blue = (int) (original.getBlue() + adjustment);
			return addReflectance(new int[] { red, green, blue }, hitSurface);
		} else {
			return addReflectance(
					new int[] { original.getRed(), original.getGreen(),
							original.getBlue() }, hitSurface);
		}

	}

	private RGB addReflectance(int[] rgb, Surface hitSurface) {
		double reflectance = hitSurface.getReflectance();
		if (reflectance != 0) {
			return new RGB((int) (rgb[0] * reflectance),
					(int) (rgb[1] * reflectance), (int) (rgb[2] * reflectance));
		} else {
			return new RGB(rgb[0], rgb[1], rgb[2]);
		}
	}

	private boolean isHitByShadowRay(Ray ray, Surface hitSurface) {
		float[] originOfShadowRay = ray.pointAtParameter(hitSurface.getT());
		Ray shadowRay = new Ray(originOfShadowRay, light.getLightVector());
		return this.surfaces.hit(shadowRay, 0.001, Integer.MAX_VALUE, 0);
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
