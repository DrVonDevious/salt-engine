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

	public Game() {}

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

	public void start(GameRunnable runnable) throws InterruptedException {
		isRunning = true;

		tickHandler = new TickHandler();
		window = new Window(config.window, keyHandler);
		window.init();

		activeScene.init(window);

		runnable.run();

		float deltaTime;

		while (isRunning && !glfwWindowShouldClose(window.getGlfwWindow())) {
			deltaTime = tickHandler.deltaTime();

			window.pollEvents();

			gameLogic.update(deltaTime);
			tickHandler.updateUPS();

			render();

			tickHandler.tick();

			if (!this.config.window.isVsyncEnabled) {
				tickHandler.sync(this.config.window.targetFPS);
			}
		}

		cleanup();
	}

	public void setScene(Scene scene) {
		activeScene = scene;
	}

	public void render() {
		window.clear();

		activeScene.render();
		gameLogic.render();

		window.update();

		tickHandler.updateFPS();
	}

	public void cleanup() {}

	public void exit() {
		isRunning = false;
	}
}
