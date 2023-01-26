package com.wyldersong.saltengine.graphics;

import org.lwjgl.system.MemoryStack;

import java.nio.ByteBuffer;
import java.nio.IntBuffer;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL30.glGenerateMipmap;
import static org.lwjgl.stb.STBImage.*;

public class Tileset {
	public int id;
	private final String filepath;

	public Tileset(String filepath) {
		this.filepath = filepath;
	}

	public void load() {
		try (MemoryStack stack = MemoryStack.stackPush()) {
			IntBuffer width = stack.mallocInt(1);
			IntBuffer height = stack.mallocInt(1);
			IntBuffer channels = stack.mallocInt(1);

			ByteBuffer buffer = stbi_load(filepath, width, height, channels, 4);
			if (buffer == null) {
				throw new RuntimeException(
					"Failed to load file from path " + filepath + " Got Error: " + stbi_failure_reason()
				);
			}

			id = glGenTextures();

			glBindTexture(GL_TEXTURE_2D, id);
			glPixelStorei(GL_UNPACK_ALIGNMENT, 1);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_NEAREST);
			glTexParameteri(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_NEAREST);
			glTexImage2D(
				GL_TEXTURE_2D, 0, GL_RGBA, width.get(), height.get(), 0, GL_RGBA, GL_UNSIGNED_BYTE, buffer
			);
			glGenerateMipmap(GL_TEXTURE_2D);

			stbi_image_free(buffer);
		}
	}

	public void bind() {
		glBindTexture(GL_TEXTURE_2D, id);
	}

	@SuppressWarnings("unused")
	public void delete() {
		glDeleteTextures(id);
	}
}