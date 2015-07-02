package me.jessicaalohse.raytracer.textures;

import me.jessicaalohse.raytracer.utilities.RGB;
import me.jessicaalohse.raytracer.utilities.Vector2D;
import me.jessicaalohse.raytracer.utilities.Vector3D;

public class NoiseTexture implements Texture {

	float scale;
	RGB color1;
	RGB color2;
	SolidNoise solidNoise;

	public NoiseTexture() {
		scale = 1;
		color1 = new RGB(204, 0, 0);
		color2 = new RGB(0, 0, 204);
	}

	public NoiseTexture(float scale, RGB color1, RGB color2) {
		this.scale = scale;
		this.color1 = color1;
		this.color2 = color2;
	}

	@Override
	public RGB getValue(Vector2D vector2, Vector3D vector3) {
		float t = (1.0f + solidNoise.getNoise(vector3.scaleUp(scale))) / 2.0f;
		return color1.multiplyByScalar(t)
				.add(color2.multiplyByScalar(1.0f - t));
	}

}
