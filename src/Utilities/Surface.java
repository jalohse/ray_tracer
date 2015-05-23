package utilities;

public interface Surface {
	
	public boolean hit(Ray ray, double tSubZero, double tSub1, Surface prim);

}
