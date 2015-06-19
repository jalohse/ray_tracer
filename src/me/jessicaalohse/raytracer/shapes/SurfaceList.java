package me.jessicaalohse.raytracer.shapes;

import java.util.ArrayList;
import java.util.List;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Ray;

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
		prim = null;
		for (Surface surface : surfaces) {
			if (surface.hit(ray, tSubZero, t)) {
				prim = surface;
				t = surface.getT();
				hitOne = true;
			}
		}
		return hitOne;
	}

	public Surface getPrim() {
		return prim;
	}

	@Override
	public double getT() {
		return t;
	}

	@Override
	public RGB getColor() {
		return prim.getColor();
	}

	@Override
	public double getReflectance() {
		return prim.getReflectance();
	}

}
