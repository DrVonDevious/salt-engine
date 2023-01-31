package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.Entity;
import com.wyldersong.saltengine.components.CellComponent;
import com.wyldersong.saltengine.components.PositionComponent;
import com.wyldersong.saltengine.graphics.shader.ShaderManager;
import com.wyldersong.saltengine.graphics.shader.UniformMapper;
import org.joml.Vector2f;
import org.joml.Vector2i;
import org.joml.Vector4f;

import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL13.GL_TEXTURE0;
import static org.lwjgl.opengl.GL13.glActiveTexture;

public class Layer {
	public LayerConfig config;
	public Tileset tileset;
	public List<Entity> entities;

	public Layer() {
		tileset = new Tileset("res/terminal16x16.png");
		config = new LayerConfig();
		entities = new ArrayList<>();
	}

	public void init() {
		tileset.load();
		initCells();
	}

	public void initCells() {
		for (Entity entity: entities) {
			if (entity.hasComponent(CellComponent.class)) {
				CellComponent cellComponent = (CellComponent) entity.getComponent(CellComponent.class);
				Cell cell = cellComponent.cell;

				if (!cell.isEmpty()) cell.init();
			}
		}
	}

	public void addEntity(Entity entity) {
		entities.add(entity);
	}

	public void removeEntity(Entity entity) {
		entities.remove(entity);
	}

	public void render(ShaderManager shaderManager) {
		glActiveTexture(GL_TEXTURE0);
		tileset.bind();

		for (Entity entity: entities) {
			if (entity.hasComponent(CellComponent.class) && entity.hasComponent(PositionComponent.class)) {
				CellComponent cellComponent = (CellComponent) entity.getComponent(CellComponent.class);
				PositionComponent position = (PositionComponent) entity.getComponent(PositionComponent.class);

				Cell cell = cellComponent.cell;
				cell.position = new Vector2i(position.x, position.y);

				if (cell.isInitialized) {
					if (cell.backgroundColor != null) {
						UniformMapper.setUniform("backgroundColor", cell.backgroundColor.getVector4f(), shaderManager.uniforms);
					} else {
						UniformMapper.setUniform("backgroundColor", new Vector4f(0, 0, 0, 255), shaderManager.uniforms);
					}

					if (cell.foregroundColor != null) {
						UniformMapper.setUniform("foregroundColor", cell.foregroundColor.getVector4f(), shaderManager.uniforms);
					} else {
						UniformMapper.setUniform("foregroundColor", new Vector4f(255, 255, 255, 255), shaderManager.uniforms);
					}

					UniformMapper.setUniform("cellOffset", new Vector2f((float) (cell.glyph.id % 16) / config.cellSize, (float) (cell.glyph.id / 16) / config.cellSize), shaderManager.uniforms);
					UniformMapper.setUniform("model", cell.getModelMatrix(config.cellSize, config.scale), shaderManager.uniforms);

					cell.draw();
				}
			}
		}
	}
}
