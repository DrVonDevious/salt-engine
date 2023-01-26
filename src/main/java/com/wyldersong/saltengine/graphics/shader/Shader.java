package com.wyldersong.saltengine.graphics.shader;

import static org.lwjgl.opengl.GL20.*;

public class Shader {
	public static final int VERTEX_SHADER = GL_VERTEX_SHADER;
	public static final int FRAGMENT_SHADER = GL_FRAGMENT_SHADER;

	public int id;

	public Shader(int shaderType) {
		id = glCreateShader(shaderType);
	}

	public void setSource(CharSequence source) {
		glShaderSource(id, source);
	}

	public void compile() {
		glCompileShader(id);
		isShaderValid();
	}

	public void delete() {
		glDeleteShader(id);
	}

	public void isShaderValid() {
		if (glGetShaderi(id, GL_COMPILE_STATUS) != GL_TRUE) {
			throw new RuntimeException(glGetShaderInfoLog(id));
		}
	}
}
