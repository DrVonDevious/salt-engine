package com.wyldersong.saltengine.graphics.shader;

import org.joml.*;
import org.lwjgl.system.MemoryStack;

import java.nio.FloatBuffer;
import java.util.Map;

import static org.lwjgl.opengl.GL20.*;

public class UniformMapper {
	private static FloatBuffer createFloatBuffer(int size) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			return stack.mallocFloat(size);
		}
	}

	public static void createUniform(int programId, String name, Map<String, Integer> target) {
		int uniformLocation = glGetUniformLocation(programId, name);

		if (uniformLocation < 0) {
			throw new RuntimeException(
				"Could not find uniform " + name + " in shader program " + programId +
				". Make sure that you are defining the uniform in the correct shader file"
			);
		}

		target.put(name, uniformLocation);
	}

	private static int checkUniform(String name, Map<String, Integer> target) {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			Integer location = target.get(name);

			if (location == null) {
				throw new RuntimeException(
					"Could not find uniform " + name +
					". Make sure that you have created the uniform with UniformMapper.createUniform"
				);
			}

			return location;
		}
	}

	public static void setUniform(String name, int value, Map<String, Integer> target) {
		int location = checkUniform(name, target);
		glUniform1i(location, value);
	}

	public static void setUniform(String name, Vector2f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(2);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniform2fv(location, buffer);
	}

	public static void setUniform(String name, Vector3f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(3);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniform3fv(location, buffer);
	}

	public static void setUniform(String name, Vector4f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(4);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniform4fv(location, buffer);
	}

	public static void setUniform(String name, Matrix2f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(4);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniformMatrix2fv(location, false, buffer);
	}

	public static void setUniform(String name, Matrix3f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(9);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniformMatrix3fv(location, false, buffer);
	}

	public static void setUniform(String name, Matrix4f value, Map<String, Integer> target) {
		FloatBuffer buffer = createFloatBuffer(16);
		value.get(buffer);
		int location = checkUniform(name, target);
		glUniformMatrix4fv(location, false, buffer);
	}
}
