package com.wyldersong.saltengine.graphics.shader;

import org.joml.Matrix4f;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.glBindFragDataLocation;

public class ShaderManager {
	public int programId;

	public Shader vertexShader;
	public Shader fragmentShader;
	public Matrix4f projection;

	public Map<String, Integer> uniforms;

	public ShaderManager() {
		programId = glCreateProgram();
		uniforms = new HashMap<>();
	}

	public Shader createShader(int shaderType, String filepath) {
		Shader shader = new Shader(shaderType);
		shader.setSource(loadShaderFile(filepath));
		shader.compile();

		if (shaderType == Shader.VERTEX_SHADER) {
			vertexShader = shader;
		} else {
			fragmentShader = shader;
		}

		return shader;
	}

	public void createUniforms() {
		projection = new Matrix4f().ortho(0f, 1f, 0f, 1f, -1f, 1f);
		UniformMapper.createUniform(programId, "projection", uniforms);
		UniformMapper.createUniform(programId, "model", uniforms);
		UniformMapper.createUniform(programId, "sampler", uniforms);
		UniformMapper.createUniform(programId, "cellOffset", uniforms);
	}

	public CharSequence loadShaderFile(String filepath) {
		StringBuilder stringBuilder = new StringBuilder();

		try (
			InputStream inputStream = new FileInputStream(filepath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		) {
			String line;

			while ((line = bufferedReader.readLine()) != null) {
				stringBuilder.append(line).append("\n");
			}
		} catch (IOException e) {
			throw new RuntimeException("Failed to load shader file: " + e);
		}

		return stringBuilder.toString();
	}

	public void attachShaders() {
		if (vertexShader == null || fragmentShader == null) {
			throw new IllegalStateException(
				"Both the vertex and fragment shaders must be initialized before attaching shaders"
			);
		}

		glAttachShader(programId, vertexShader.id);
		glAttachShader(programId, fragmentShader.id);
	}

	public void link() {
		glLinkProgram(programId);

		if (glGetProgrami(programId, GL_LINK_STATUS) != GL_TRUE) {
			throw new RuntimeException(glGetProgramInfoLog(programId));
		}
	}

	public void delete() {
		glDeleteProgram(programId);
	}

	public void bindLocation(int index, CharSequence name) {
		glBindFragDataLocation(programId, index, name);
	}

	public void setAttributeLocation(int location, int size, int step, int offset) {
		glEnableVertexAttribArray(location);
		glVertexAttribPointer(location, size, GL_FLOAT, false, step, offset);
	}

	public int getAttributeLocation(CharSequence name) {
		return glGetAttribLocation(programId, name);
	}

	public void enableVertexAttribute(int location) {}

	public void disableVertexAttribute(int location) {}

	public void getUniformLocation(CharSequence name) {}

	public void bind() {
		glUseProgram(programId);
	}

	public void unbind() {
		glUseProgram(0);
	}
}