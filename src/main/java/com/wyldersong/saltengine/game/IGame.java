package com.wyldersong.saltengine.game;

public interface IGame {
	/**
	 * Runs at the beginning of a game tick
	 */
	void update(float deltaTime);


	/**
	 * Runs during the render phase of a game tick
	 */
	void render();
}
