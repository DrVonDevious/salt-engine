package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.graphics.shader.ShaderManager;
import com.wyldersong.saltengine.graphics.shader.UniformMapper;
import com.wyldersong.saltengine.util.RGBA;
import org.joml.Vector2f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Layer {
	public LayerConfig config;
	public Tileset tileset;
	public List<List<Cell>> cells;

	public Layer() {
		tileset = new Tileset("res/terminal16x16.png");
		config = new LayerConfig();
		cells = new ArrayList<>();

		for (int row = 0; row < config.cellRows; row++) {
			cells.add(new ArrayList<>());
			for (int col = 0; col < config.cellColumns; col++) {
				cells.get(row).add(col, new Cell());
			}
		}
	}

	public void init() {
		tileset.load();
	}

	public void draw(int x, int y, Glyph glyph) {
		cells.get(y).set(x, new Cell(x, y, new RGBA(0, 0, 0, 1), new RGBA(1, 0, 0, 1), glyph));
	}

	public void render(ShaderManager shaderManager) {
		glActiveTexture(GL_TEXTURE0);
		tileset.bind();

		for (List<Cell> cell : cells) {
			for (Cell value : cell) {
				if (value.isInitialized) {
					UniformMapper.setUniform("cellOffset", new Vector2f((float) (value.glyph.id % 16) / config.cellSize, (float) (value.glyph.id / 16) / config.cellSize), shaderManager.uniforms);
					UniformMapper.setUniform("model", value.getModelMatrix(config.cellSize, config.scale), shaderManager.uniforms);
					value.draw();
				}
			}
		}
	}
}
