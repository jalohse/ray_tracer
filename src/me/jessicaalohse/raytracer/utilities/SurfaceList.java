package me.jessicaalohse.raytracer.utilities;

import java.util.ArrayList;
import java.util.List;

public class SurfaceList implements Surface {

	List<Surface> surfaces;
	double t;
	Surface prim;

	public SurfaceList() {
		surfaces = new ArrayList<>();
	}

	public void add(Surface surface) {
		surfaces.add(surface);
	}

	@Override
	public boolean hit(Ray ray, double tSubZero, double tSub1) {
		boolean hitOne = false;
		t = tSub1;
		for (Surface surface : surfaces) {
			if (surface.hit(ray, tSubZero, tSub1)) {
				prim = surface;
				hitOne = true;
			}
		}
		return hitOne;
	}

	public Surface getPrim() {
		return prim;
	}

}
