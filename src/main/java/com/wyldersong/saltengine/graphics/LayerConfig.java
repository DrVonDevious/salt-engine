package com.wyldersong.saltengine.graphics;

public class LayerConfig {
	public int cellSize = 16;
	public float scale = 1.0f;
	public int cellRows = 14;
	public int cellColumns = 14;

	public float[] cellVertices = new float[] {
			0.0f, 600.0f, 0.0f,
			0.0f, 600.0f - cellSize * scale, 0.0f,
			cellSize * scale, 600.0f - cellSize * scale, 0.0f,
			cellSize * scale, 600.0f, 0.0f,
	};

	public float[] uvs = new float[] {
			0.0f, 0.0f,
			0.0f, 1.0f / cellSize,
			1.0f / cellSize, 1.0f  / cellSize,
			1.0f / cellSize, 0.0f,
	};

	public int[] indices = new int[] {
			0, 1, 3, 3, 1, 2,
	};
}
