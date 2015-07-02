package me.jessicaalohse.raytracer.textures;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector2D;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class MarbleTexture implements Texture {

	float frequency;
	float scale;
	int octaves;
	RGB color0, color1, color2;
	SolidNoise noise = new SolidNoise();

	public MarbleTexture(float stripesPerUnit, float scale, int octaves) {
		frequency = (float) (Math.PI * stripesPerUnit);
		this.scale = scale;
		this.octaves = octaves;
		color0 = new RGB(204, 204, 204);
		color1 = new RGB(102, 51, 26);
		color2 = new RGB(15, 10, 5);
	}

	public MarbleTexture(RGB color0, RGB color1, RGB color2,
			float stripesPerUnit, float scale, int octaves) {
		frequency = (float) (Math.PI * stripesPerUnit);
		this.scale = scale;
		this.octaves = octaves;
		this.color0 = color0;
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public RGB getValue(Vector2D vector2, Vector3D vector3) {
		float temp = scale
				* noise.createTurbulence(vector3.multiply(frequency), octaves);
		float t = (float) (2.0f * Math.abs(Math.sin(frequency * vector3.getX()
				+ temp)));
		if (t < 1.0f) {
			return color1.multiplyByScalar(t).add(
					color2.multiplyByScalar(1.0f - t));
		} else {
			t -= 1.0f;
			return color0.multiplyByScalar(t).add(
					color1.multiplyByScalar(1.0f - t));
		}
	}

}
