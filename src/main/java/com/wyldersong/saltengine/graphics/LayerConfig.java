package com.wyldersong.saltengine.graphics;

public class LayerConfig {
	public int cellSize;
	public float scale;
	public int cellRows;
	public int cellColumns;

	public LayerConfig() {
		cellSize = 16;
		scale = 1.0f;
		cellRows = 14;
		cellColumns = 14;
	}

	public LayerConfig(int width, int height) {
		cellSize = 16;
		scale = 1.0f;
		cellRows = height;
		cellColumns = width;
	}
}
