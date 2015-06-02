package me.jessicaalohse.raytracer.utilities;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Image {

	int rows;
	int columns;
	RGB[][] image;

	public Image(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.image = new RGB[rows][columns];
	}

	public void populateImage(RGB[][] pixels) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				image[i][j] = pixels[i][j];
			}
		}
	}

	public void printImage(String imageName) throws IOException {
		BufferedImage img = new BufferedImage(rows, columns, BufferedImage.TYPE_INT_RGB);
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				RGB rgb = image[i][j];
				int color = (rgb.red << 16) | (rgb.green << 8) | rgb.blue;
				img.setRGB(i, j, color);
			}
		}
		File file;
		if(imageName == null || imageName.isEmpty()){
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
