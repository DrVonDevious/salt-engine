package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.graphics.shader.Shader;
import com.wyldersong.saltengine.graphics.shader.ShaderManager;
import com.wyldersong.saltengine.graphics.shader.UniformMapper;
import org.lwjgl.system.MemoryUtil;

import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL30.glBindVertexArray;
import static org.lwjgl.opengl.GL30.glGenVertexArrays;

public class Scene {
	private List<Layer> layers;
	private ShaderManager shaderManager;

	private Window window;
	private int vao;
	private int vbo;

	public Camera camera;

	public Scene() {
		layers = new ArrayList<>();
		camera = new Camera(0, 0);
	}

	public void init(Window window) {
		this.window = window;
		shaderManager = new ShaderManager();

		camera.setProjection(0, window.width, 0, window.height);

		vao = glGenVertexArrays();
		glBindVertexArray(vao);

		vbo = glGenBuffers();
		glBindBuffer(GL_ARRAY_BUFFER, vbo);

		FloatBuffer vertices = MemoryUtil.memAllocFloat(4096);
		long size = (long) vertices.capacity() * Float.BYTES;

		glBufferData(GL_ARRAY_BUFFER, size, GL_DYNAMIC_DRAW);

		shaderManager.createShader(Shader.VERTEX_SHADER, "res/vertex.glsl");
		shaderManager.createShader(Shader.FRAGMENT_SHADER, "res/fragment.glsl");
		shaderManager.attachShaders();
		shaderManager.link();
		shaderManager.bind();

		shaderManager.createUniforms();

		shaderManager.fragmentShader.delete();
		shaderManager.vertexShader.delete();

		for (Layer layer : layers) {
			layer.init();
		}
	}

	public void addLayer(Layer layer) {
		layers.add(layer);
	}

	public void render() {
		shaderManager.bind();

		UniformMapper.setUniform("projection", camera.getProjection(), shaderManager.uniforms);
		UniformMapper.setUniform("sampler", 0, shaderManager.uniforms);

		for (Layer layer : layers) {
			layer.render(shaderManager);
		}

		glBindVertexArray(0);

		shaderManager.unbind();
	}
}
