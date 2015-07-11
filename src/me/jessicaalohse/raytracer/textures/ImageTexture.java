package me.jessicaalohse.raytracer.textures;

import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import me.jessicaalohse.raytracer.utilities.Image;
import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector2D;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class ImageTexture implements Texture {

	Image image;

	public ImageTexture(String fileName) {
		try {
			File imgFile = new File(fileName);
			BufferedImage imageFile = ImageIO.read(imgFile);
			this.image = new Image(imageFile.getHeight(), imageFile.getWidth());
			this.image.populateImage(getPixelsFromImage(imageFile));
			this.image.printImage("imagetest");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RGB[][] getPixelsFromImage(BufferedImage image) {
		RGB[][] pixels = new RGB[image.getHeight()][image.getWidth()];
		final byte[] originalPixels = ((DataBufferByte) image.getRaster()
				.getDataBuffer()).getData();
		int rows = 0;
		int columns = 0;
		for (int pixel = 0; pixel < originalPixels.length; pixel += 3) {
			int r = ((originalPixels[pixel + 2] & 0xff) << 16);
			int g = ((originalPixels[pixel + 1] & 0xff) << 8);
			int b = (originalPixels[pixel] & 0xff);
			RGB color = new RGB(r, g, b);
			color.clamp();
			pixels[rows][columns] = color;
			columns++;
			if (columns == image.getWidth()) {
				columns = 0;
				rows++;
				if(rows == image.getHeight()){
					break;
				}
			}
		}
		return pixels;
	}

	public RGB getValue(Vector2D uv, Vector3D p) {
		float u = uv.getX() - (int) uv.getX();
		float v = uv.getY() - (int) uv.getY();
		return new RGB(0, 0, 0);
	}

}
