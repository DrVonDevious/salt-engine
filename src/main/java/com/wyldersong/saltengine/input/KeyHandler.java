package com.wyldersong.saltengine.input;

import org.lwjgl.glfw.GLFWKeyCallback;

import static org.lwjgl.glfw.GLFW.*;

public class KeyHandler {
	private final KeySet keySet;

	public KeyHandler(KeySet keySet) {
		this.keySet = keySet;
	}

	public GLFWKeyCallback glfwKeyCallback() {
		return new GLFWKeyCallback() {
			@Override
			public void invoke(long window, int key, int scancode, int action, int mods) {
				KeySet.KeyAlias foundKey = keySet.getKey(key);

				if (foundKey != null) {
					if (action == GLFW_PRESS) {
						foundKey.isPressed = true;
					} else if (action == GLFW_RELEASE) {
						foundKey.isPressed = false;
					}
				}
			}
		};
	}

	public boolean isKeyPressed(String alias) {
		KeySet.KeyAlias foundKey = keySet.getKey(alias);

		if (foundKey != null) {
			return foundKey.isPressed;
		}

		return false;
	}
}
