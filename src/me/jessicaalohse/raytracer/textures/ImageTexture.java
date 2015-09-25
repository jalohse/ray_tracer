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
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public RGB[][] getPixelsFromImage(BufferedImage image) {
		RGB[][] pixels = new RGB[image.getHeight()][image.getWidth()];
		final byte[] originalPixels = ((DataBufferByte) image.getData().getDataBuffer()).getData();
		int rows = 0;
		int columns = 0;
		for (int pixel = 0; pixel < originalPixels.length; pixel += 3) {
			int r = ((originalPixels[pixel + 2] & 0xff));
			int g = ((originalPixels[pixel + 1] & 0xff));
			int b = (originalPixels[pixel] & 0xff);
			RGB color = new RGB(r, g, b);
			color.clamp();
			pixels[rows][columns] = color;
			columns++;
			if (columns == image.getWidth()) {
				columns = 0;
				rows++;
				if (rows == image.getHeight()) {
					break;
				}
			}
		}
		return pixels;
	}

	public RGB getValue(Vector2D uv, Vector3D p) {
		float u = uv.getX() - (int) uv.getX();
		float v = uv.getY() - (int) uv.getY();
		u *= image.getRows() - 3;
		v *= image.getColumns() - 3;
		int iu = (int) u;
		int iv = (int) v;
		float tu = u - iu;
		float tv = v - iv;
		RGB[][] pixels = image.image;
		RGB c = pixels[iu][iv].multiplyByScalar(1 - tu).multiplyByScalar(1 - tv)
				.add(pixels[iu + 1][iv].multiplyByScalar(tu).multiplyByScalar(1 - tv))
				.add(pixels[iu][iv + 1].multiplyByScalar(1 - tu).multiplyByScalar(tv))
				.add(pixels[iu + 1][iv + 1].multiplyByScalar(tu).multiplyByScalar(tv));
		return c;
	}

}
