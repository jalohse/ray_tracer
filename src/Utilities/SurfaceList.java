package utilities;

import java.util.ArrayList;
import java.util.List;

public class SurfaceList implements Surface {

	List<Surface> surfaces;
	double t;

	public SurfaceList() {
		surfaces = new ArrayList<>();
	}

	public void add(Surface surface) {
		surfaces.add(surface);
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSub1, Surface prim) {
		boolean hitOne = false;
		t = tSub1;
		for (Surface surface : surfaces) {
			if (surface.hit(ray, tSubZero, tSub1, prim)) {
				hitOne = true;
			}
		}
		return hitOne;
	}

}
