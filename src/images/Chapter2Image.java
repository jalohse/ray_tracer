package images;

import java.io.FileNotFoundException;
import java.util.ArrayList;

import utilities.Image;
import utilities.RGB;
import utilities.Ray;
import utilities.Sphere;
import utilities.Surface;
import utilities.SurfaceList;
import utilities.Triangle;
import utilities.Vector3D;

public class Chapter2Image {

	int rowsColumns = 101;

	public Chapter2Image() {
		Image image = new Image(rowsColumns, rowsColumns);
		Sphere sphere = new Sphere(0, 0, -150, 100.1f);
		Triangle triangle = new Triangle(new double[] { 0, 0, -100 },
				new double[] { 100.1, 0, -100 },
				new double[] { 50, 100.1, -100 });
		SurfaceList list = new SurfaceList();
		list.add(sphere);
		list.add(triangle);
		RGB lightGray = new RGB(215, 215, 215);
		RGB darkGray = new RGB(112, 112, 112);
		RGB black = new RGB(0, 0, 0);
		ArrayList<RGB> pixels = new ArrayList<>();
		for (int i = 0; i < rowsColumns; i++) {
			for (int j = 0; j < rowsColumns; j++) {
				float[] origin = new float[] { i, j, 0 };
				Ray ray = new Ray(origin, new Vector3D(0, 0, -1));
				if (list.hit(ray, 0, 1000000)) {
					Surface hitSurface = list.getPrim();
					int index = i + j;
					if (hitSurface instanceof Sphere) {
						pixels.add(index, lightGray);
					} else if (hitSurface instanceof Triangle) {
						pixels.add(index, darkGray);
					} else {
						pixels.add(index, black);
					}
				}
			}
		}
		image.populateImage(pixels);
		try {
			image.printImage();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

}
