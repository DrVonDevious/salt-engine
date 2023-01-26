package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.input.KeyHandler;
import org.lwjgl.glfw.GLFWVidMode;
import org.lwjgl.opengl.GL;
import org.lwjgl.system.MemoryStack;

import java.nio.IntBuffer;

import static org.lwjgl.glfw.GLFW.*;
import static org.lwjgl.opengl.GL11C.*;
import static org.lwjgl.system.MemoryStack.stackPush;
import static org.lwjgl.system.MemoryUtil.NULL;

public class Window {
	public int width, height;

	private KeyHandler keyHandler;
	private WindowConfig config;
	private long window;

	public Window(WindowConfig config, KeyHandler keyHandler) {
		this.config = config;
		width = config.width;
		height = config.height;
		this.keyHandler = keyHandler;
		System.out.println(keyHandler);
	}

	public void init() {
		if (!glfwInit()) {
			throw new IllegalStateException("Failed to initialize GLFW");
		}

		// Set GLFW configuration https://www.glfw.org/docs/3.3/group__window.html#gaa77c4898dfb83344a6b4f76aa16b9a4a
		glfwDefaultWindowHints();
		glfwWindowHint(GLFW_VISIBLE, GLFW_FALSE);

		try {
			this.window = glfwCreateWindow(config.width, config.height, config.title, NULL, NULL);
		} catch (Exception e) {
			throw new RuntimeException("Failed to create GLFW Window. Got Error: ", e);
		}

		glfwSetKeyCallback(window, keyHandler.glfwKeyCallback());

		this.centerWindow();

		glfwMakeContextCurrent(window);
		glfwSwapInterval(1);

		glfwShowWindow(window);

		GL.createCapabilities();

		glClearColor(
			config.clearColor.r,
			config.clearColor.g,
			config.clearColor.b,
			config.clearColor.a
		);
	}

	public void centerWindow() {
		try (MemoryStack stack = stackPush()) {
			IntBuffer pWidth = stack.mallocInt(1);
			IntBuffer pHeight = stack.mallocInt(1);

			glfwGetWindowSize(window, pWidth, pHeight);

			// Get the current monitor resolution
			GLFWVidMode vidmode = glfwGetVideoMode(glfwGetPrimaryMonitor());
			assert vidmode != null;

			glfwSetWindowPos(
				window,
				(vidmode.width() - pWidth.get(0)) / 2,
				(vidmode.height() - pHeight.get(0)) / 2
			);
		}
	}

	public long getGlfwWindow() {
		return window;
	}

	public void clear() {
		glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);
	}

	public void update() {
		glfwSwapBuffers(window);
	}

	public void pollEvents() {
		glfwPollEvents();
	}
}
