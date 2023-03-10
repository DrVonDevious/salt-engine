package com.wyldersong.saltengine.game;

import com.wyldersong.saltengine.graphics.Scene;
import com.wyldersong.saltengine.graphics.Window;
import com.wyldersong.saltengine.graphics.WindowConfig;
import com.wyldersong.saltengine.input.KeyHandler;
import com.wyldersong.saltengine.input.KeySet;

import static org.lwjgl.glfw.GLFW.glfwWindowShouldClose;

public class Game {
	public KeyHandler keyHandler;
	public GameConfig config;
	public Window window;
	public TickHandler tickHandler;

	private IGame gameLogic;
	private Scene activeScene;
	private boolean isRunning = false;

	@SuppressWarnings("unused")
	public Game() {}

	@SuppressWarnings("unused")
	public Game(GameConfig config) {
		this.config = config;
		keyHandler = new KeyHandler(config.keys);
	}

	public Game(WindowConfig windowConfig, KeySet keySet, IGame logic) {
		config = new GameConfig(windowConfig, keySet);
		keyHandler = new KeyHandler(keySet);
		gameLogic = logic;
	}

	public interface GameRunnable {
		void run();
	}

	public void start() throws InterruptedException {
		start(() -> {});
	}

	public void start(GameRunnable runnable) throws InterruptedException {
		isRunning = true;

		tickHandler = new TickHandler();
		window = new Window(config.window, keyHandler);
		window.init();

		activeScene.init(window);

		runnable.run();

		float deltaTime;
		float accumulator = 0f;
		float interval = 1f / config.window.targetUPS;
		float alpha;

		while (isRunning && !glfwWindowShouldClose(window.getGlfwWindow())) {
			deltaTime = tickHandler.deltaTime();
			accumulator += deltaTime;

			window.pollEvents();

			while (accumulator >= interval) {
				gameLogic.update(deltaTime);
				tickHandler.updateUPS();
				accumulator -= interval;
			}

			alpha = accumulator / interval;

			render(alpha);
			tickHandler.updateFPS();

			tickHandler.tick();

			if (!this.config.window.isVsyncEnabled) {
				tickHandler.sync(this.config.window.targetFPS);
			}
		}

		cleanup();
		window.close();
	}

	public void setScene(Scene scene) {
		activeScene = scene;
	}

	public void render(float alpha) {
		window.clear();

		activeScene.render();
		gameLogic.render();

		window.update();
		activeScene.update();

		tickHandler.updateFPS();
	}

	public void cleanup() { /* TODO document why this method is empty */ }

	public void exit() {
		isRunning = false;
	}
}
