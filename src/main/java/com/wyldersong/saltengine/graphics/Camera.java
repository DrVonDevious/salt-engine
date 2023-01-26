package com.wyldersong.saltengine.graphics;

import org.joml.Matrix4f;
import org.joml.Vector2f;

public class Camera {
	public Vector2f position;

	private Matrix4f projection;

	@SuppressWarnings("unused")
	public Camera() {
		position = new Vector2f(0, 0);
	}

	public Camera(float x, float y) {
		position = new Vector2f(x, y);
	}

	public void setProjection(float originX, float width, float originY, float height) {
		projection = new Matrix4f().ortho(originX, width, originY, height, -1.0f, 1.0f);
	}

	public Matrix4f getProjection() {
		return projection;
	}
}
