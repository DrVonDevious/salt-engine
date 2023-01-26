package com.wyldersong.saltengine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler {
	private KeySet keySet;

	public KeyHandler(KeySet keySet) {
		this.keySet = keySet;
	}

	public GLFWKeyCallback glfwKeyCallback() {
		return new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				if (key == keySet.getKey("exit") && action == GLFW_RELEASE) {
					glfwSetWindowShouldClose(window, true);
				}
			}
		};
	}
}
