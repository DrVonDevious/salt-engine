package com.wyldersong.saltengine.game;

import static org.lwjgl.glfw.GLFW.glfwGetTime;

public class TickHandler {
	private double lastTickTime;
	private float frameCount;
	private int fps;
	private int fpsCount;

	private int ups;
	private int upsCount;

	public TickHandler() {
		this.lastTickTime = this.getCurrentTime();
	}

	public double getCurrentTime() {
		return glfwGetTime();
	}

	public float deltaTime() {
		double currentTime = this.getCurrentTime();
		float delta = (float) (currentTime - this.lastTickTime);
		this.lastTickTime = currentTime;
		this.frameCount += delta;

		return delta;
	}

	public void updateFPS() {
		this.fpsCount++;
	}

	public void updateUPS() {
		this.upsCount++;
	}

	@SuppressWarnings("unused")
	public int getFPS() {
		return fps > 0 ? fps : fpsCount;
	}

	@SuppressWarnings("unused")
	public int getUPS() {
		return ups > 0 ? ups : upsCount;
	}

	public void tick() {
		if (this.frameCount > 1f) {
			this.fps = fpsCount;
			this.fpsCount = 0;

			this.ups = upsCount;
			this.upsCount = 0;

			this.frameCount -= 1f;
		}
	}

	public void sync(int fps) throws InterruptedException {
		double lastTick = lastTickTime;
		double currentTime = getCurrentTime();
		float targetTime = 1f / fps;

		while (currentTime - lastTick < targetTime) {
			try {
				Thread.sleep(1);
			} catch (InterruptedException e) {
				throw new InterruptedException("Failed to sync fps: {}");
			}

			currentTime = getCurrentTime();
		}
	}
}
