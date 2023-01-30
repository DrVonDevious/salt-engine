package com.wyldersong.saltengine.graphics.shader;

import org.joml.Matrix4f;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

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

	public void createShader(int shaderType, String filepath) {
		Shader shader = new Shader(shaderType);
		shader.setSource(loadShaderFile(filepath));
		shader.compile();

		if (shaderType == Shader.VERTEX_SHADER) {
			vertexShader = shader;
		} else {
			fragmentShader = shader;
		}
	}

	public void createUniforms() {
		projection = new Matrix4f().ortho(0f, 1f, 0f, 1f, -1f, 1f);
		UniformMapper.createUniform(programId, "projection", uniforms);
		UniformMapper.createUniform(programId, "model", uniforms);
		UniformMapper.createUniform(programId, "sampler", uniforms);
		UniformMapper.createUniform(programId, "cellOffset", uniforms);
		UniformMapper.createUniform(programId, "backgroundColor", uniforms);
		UniformMapper.createUniform(programId, "foregroundColor", uniforms);
	}

	public CharSequence loadShaderFile(String filepath) {
		StringBuilder stringBuilder = new StringBuilder();

		try (
			InputStream inputStream = new FileInputStream(filepath);
			BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream))
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

	@SuppressWarnings("unused")
	public void delete() {
		glDeleteProgram(programId);
	}

	public void bind() {
		glUseProgram(programId);
	}

	public void unbind() {
		glUseProgram(0);
	}
}
