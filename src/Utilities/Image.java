package utilities;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;

public class Image {

	int rows;
	int columns;
	RGB[][] image;

	public Image(int rows, int columns) {
		this.rows = rows;
		this.columns = columns;
		this.image = new RGB[rows][columns];
	}

	public void populateImage(ArrayList<RGB> pixels) {
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < columns; j++) {
				image[i][j] = pixels.get(i + j);
			}
		}
	}
	
	public void printImage() throws FileNotFoundException{
		PrintWriter writer = new PrintWriter("image.txt");
		for(int i = 0; i < rows; i++){
			for(int j = 0; j < columns; j++){
				writer.write(image[i][j].toString());
			}
		}
		writer.close();
	}

}
