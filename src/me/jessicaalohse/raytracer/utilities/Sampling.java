package me.jessicaalohse.raytracer.utilities;

import java.util.ArrayList;

public class Sampling {

	public void random(ArrayList<Vector2D> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			samples.get(i).setX((float) Math.random());
			samples.get(i).setY((float) Math.random());
		}
	}

	// Assumes numberOfSamples is perfect square
	public void jitter(ArrayList<Vector2D> samples, int numberOfSamples) {
		int sqrSample = (int) Math.sqrt(numberOfSamples);
		for (int i = 0; i < sqrSample; i++) {
			for (int j = 0; j < sqrSample; j++) {
				float x = (float) ((i + Math.random()) / sqrSample);
				float y = (float) ((j + Math.random()) / sqrSample);
				samples.get(i * sqrSample + j).setX(x);
				samples.get(i * sqrSample + j).setX(y);
			}
		}
	}

	public void nRooks(ArrayList<Vector2D> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			samples.get(i)
					.setX((float) ((i + Math.random()) / numberOfSamples));
			samples.get(i)
					.setY((float) ((i + Math.random()) / numberOfSamples));
		}
		for (int i = numberOfSamples - 2; i >= 0; i--) {
			int target = (int) (Math.random() * i);
			float temp = samples.get(i + 1).getX();
			samples.get(i + 1).setX(samples.get(target).getX());
			samples.get(target).setX(temp);
		}
	}

	// Assumes numberOfSamples is a perfect square
	public void multiJitter(ArrayList<Vector2D> samples, int numberOfSamples) {
		int sqrSample = (int) Math.sqrt(numberOfSamples);
		float subCellWidth = 1.0f / numberOfSamples;
		// Initialize points to the "canonical" mult-jittered pattern
		for (int i = 0; i < sqrSample; i++) {
			for (int j = 0; j < sqrSample; j++) {
				samples.get(i * sqrSample + j).setX(
						(float) (i * sqrSample * subCellWidth + j
								* subCellWidth + Math.random() * subCellWidth));
				samples.get(i * sqrSample + j).setX(
						(float) (j * sqrSample * subCellWidth + i
								* subCellWidth + Math.random() * subCellWidth));
			}
		}

		// Shuffle x coordinates within each column and y coordinates within
		// each row
		for (int i = 0; i < sqrSample; i++) {
			for (int j = 0; j < sqrSample; j++) {
				int k = j + (int) (Math.random() * (sqrSample - j - 1));
				float t = samples.get(i * sqrSample + j).getX();
				samples.get(i * sqrSample + j).setX(
						samples.get(i * sqrSample + k).getX());
				samples.get(i * sqrSample + k).setX(t);

				k = (int) (Math.random() * (sqrSample - j - 1));
				t = samples.get(j * sqrSample + i).getY();
				samples.get(j * sqrSample + i).setX(
						samples.get(k * sqrSample + i).getY());
				samples.get(k * sqrSample + i).setX(t);
			}
		}
	}

	public void shuffle(ArrayList<Vector2D> samples, int numberOfSamples) {
		for (int i = numberOfSamples - 2; i >= 0; i--) {
			int target = (int) (Math.random() * i);
			Vector2D temp = samples.get(i + 1);
			samples.set(i + 1, samples.get(target));
			samples.set(target, temp);
		}
	}

	public void boxFilter(ArrayList<Vector2D> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			samples.get(i).setX(samples.get(i).getX() - 0.5f);
			samples.get(i).setY(samples.get(i).getY() - 0.5f);
		}
	}

	public void tentFilter(ArrayList<Vector2D> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			float x = samples.get(i).getX();
			float y = samples.get(i).getY();
			if (x < 0.5f) {
				samples.get(i).setX((float) (Math.sqrt(2.0 * x)) - 1.0f);
			} else {
				samples.get(i).setX(1.0f - (float) (Math.sqrt(2.0 - 2.0 * x)));
			}
			if (y < 0.5f) {
				samples.get(i).setY((float) (Math.sqrt(2.0 * y)) - 1.0f);
			} else {
				samples.get(i).setY(1.0f - (float) (Math.sqrt(2.0 - 2.0 * y)));
			}
		}
	}

	public void cubicSplineFilter(ArrayList<Vector2D> samples,
			int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			float x = samples.get(i).getX();
			float y = samples.get(i).getY();
			samples.get(i).setX(cubicFilter(x));
			samples.get(i).setY(cubicFilter(y));
		}
	}

	private float cubicFilter(float x) {
		if (x < 1.0f / 24.0f) {
			return (float) (Math.pow(24 * x, 0.25f) - 2.0f);
		} else if (x < 0.5) {
			return solve(24.0f * (x - 1.0f / 24.0f) / 11.0f) - 1.0f;
		} else if (x < 23.0f / 24.0f) {
			return 1.0f - solve(24.0f * (23.0f / 24.0f - x) / 11.0f);
		} else {
			return 2 - (float) Math.pow(24.0f * (1.0f - x), 0.25f);
		}
	}

	private float solve(float r) {
		float u = r;
		for (int i = 0; i < 5; i++) {
			u = (11.0f * r + u * u * (6.0f + u * (8.0f - 9.0f * u)))
					/ (4.0f + 12.0f * u * (1.0f + u * (1.0f - u)));
		}
		return u;
	}

	// 1D Sampling

	public void oneDimensionrandom(ArrayList<Float> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			samples.set(i, (float) Math.random());
		}
	}

	public void oneDimensionJitter(ArrayList<Float> samples, int numberOfSamples) {
		for (int i = 0; i < numberOfSamples; i++) {
			samples.set(i, (float) ((i + Math.random()) / numberOfSamples));
		}
	}

	public void oneDimensionShuffle(ArrayList<Float> samples,
			int numberOfSamples) {
		for (int i = numberOfSamples - 2; i >= 0; i--) {
			int target = (int) (Math.random() * i);
			float temp = samples.get(i + 1);
			samples.set(i + 1, samples.get(target));
			samples.set(target, temp);
		}
	}

}
