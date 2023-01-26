package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.game.Game;
import com.wyldersong.saltengine.game.IGame;
import com.wyldersong.saltengine.graphics.*;
import com.wyldersong.saltengine.input.KeySet;

public class ExampleGame implements IGame {
	public void run() throws InterruptedException {
		KeySet keySet = new KeySet();
		WindowConfig windowConfig = new WindowConfig();

		Game game = new Game(windowConfig, keySet, this);

		Scene testScene = new Scene();
		game.setScene(testScene);

		Layer testLayer = new Layer();
		testScene.addLayer(testLayer);

		game.start(() -> testLayer.draw(1, 1, new Glyph(1)));
	}

	@Override
	public void update(float deltaTime) {
		System.out.println("Updated! " + deltaTime);
	}

	@Override
	public void render() {
		System.out.println("Rendered!");
	}
}
