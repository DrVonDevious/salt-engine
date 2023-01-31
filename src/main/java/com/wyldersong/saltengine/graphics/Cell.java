package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.util.RGBA;
import org.joml.Matrix4f;
import org.joml.Vector2i;
import org.lwjgl.system.MemoryStack;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL11.GL_TRIANGLES;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.glEnableVertexAttribArray;
import static org.lwjgl.opengl.GL20.glVertexAttribPointer;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Cell {
	public Vector2i position;
	public RGBA backgroundColor;
	public RGBA foregroundColor;
	public Glyph glyph;
	public float scale = 1.0f;
	public float cellSize = 16.0f;
	public boolean isInitialized = false;

	private int vao;
	private final float[] vertices = new float[] {
		0.0f, 600.0f, 0.0f,
		0.0f, 600.0f - cellSize * scale, 0.0f,
		cellSize * scale, 600.0f - cellSize * scale, 0.0f,
		cellSize * scale, 600.0f, 0.0f,
	};

	private final float[] colors = new float[] {
		1.0f, 0.0f, 0.0f,
		0.0f, 1.0f, 0.0f,
		0.0f, 0.0f, 1.0f,
		1.0f, 1.0f, 0.0f,
	};


	private final float[] uvs = new float[] {
		0.0f, 0.0f,
		0.0f, 1.0f / cellSize,
		1.0f / cellSize, 1.0f  / cellSize,
		1.0f / cellSize, 0.0f,
	};

	private final int[] indices = new int[] {
		0, 1, 3, 3, 1, 2,
	};

	public Cell() {}

	@SuppressWarnings("unused")
	public Cell(int x, int y) {
		position = new Vector2i(x, y);
		glyph = new Glyph(1);
		init();
	}

	@SuppressWarnings("unused")
	public Cell(int x, int y, RGBA bg) {
		position = new Vector2i(x, y);
		backgroundColor = bg;
		init();
	}

	public Cell(int x, int y, Glyph glyph) {
		position = new Vector2i(x, y);
		this.glyph = glyph;
	}

	public Cell(int x, int y, RGBA bg, RGBA fg, Glyph glyph) {
		position = new Vector2i(x, y);
		backgroundColor = bg;
		foregroundColor = fg;
		this.glyph = glyph;
		init();
	}

	public void init() {
		System.out.println("initializing cell!");
		isInitialized = true;

		try (MemoryStack stack = MemoryStack.stackPush()) {
			List<Integer> vbos = new ArrayList<>();

			vao = glGenVertexArrays();
			glBindVertexArray(vao);

			int vbo = glGenBuffers();
			vbos.add(vbo);
			FloatBuffer vertexBuffer = stack.callocFloat(vertices.length);
			vertexBuffer.put(0, vertices);
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glBufferData(GL_ARRAY_BUFFER, vertexBuffer, GL_STATIC_DRAW);
			glEnableVertexAttribArray(0);
			glVertexAttribPointer(0, 3, GL_FLOAT, false, 0, 0);

			vbo = glGenBuffers();
			vbos.add(vbo);
			FloatBuffer colorBuffer = stack.callocFloat(colors.length);
			colorBuffer.put(0, colors);
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glBufferData(GL_ARRAY_BUFFER, colorBuffer, GL_STATIC_DRAW);
			glEnableVertexAttribArray(1);
			glVertexAttribPointer(1, 3, GL_FLOAT, false, 0, 0);

			vbo = glGenBuffers();
			vbos.add(vbo);
			FloatBuffer uvBuffer = MemoryUtil.memAllocFloat(uvs.length);
			uvBuffer.put(0, uvs);
			glBindBuffer(GL_ARRAY_BUFFER, vbo);
			glBufferData(GL_ARRAY_BUFFER, uvBuffer, GL_STATIC_DRAW);
			glEnableVertexAttribArray(2);
			glVertexAttribPointer(2, 2, GL_FLOAT, false, 0, 0);

			vbo = glGenBuffers();
			vbos.add(vbo);
			IntBuffer indicesBuffer = stack.callocInt(indices.length);
			indicesBuffer.put(0, indices);
			glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vbo);
			glBufferData(GL_ELEMENT_ARRAY_BUFFER, indicesBuffer, GL_STATIC_DRAW);

			glBindBuffer(GL_ARRAY_BUFFER, 0);
			glBindVertexArray(0);
		}
	}

	public boolean isEmpty() {
		return this.glyph == null;
	}

	public void draw() {
		glBindVertexArray(vao);
		glDrawElements(GL_TRIANGLES, 6, GL_UNSIGNED_INT, 0);
	}

	public Matrix4f getModelMatrix(int cellSize, float scale) {
		return new Matrix4f().translation(position.x * cellSize * scale, -position.y * cellSize * scale, 0);
	}
}
