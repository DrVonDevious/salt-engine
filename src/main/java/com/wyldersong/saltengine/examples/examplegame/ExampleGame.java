package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.game.Game;
import com.wyldersong.saltengine.game.IGame;
import com.wyldersong.saltengine.graphics.*;
import com.wyldersong.saltengine.input.KeySet;
import com.wyldersong.saltengine.util.RGBA;

public class ExampleGame implements IGame {
	public void run() throws InterruptedException {
		KeySet keySet = new KeySet();
		WindowConfig windowConfig = new WindowConfig();

		Game game = new Game(windowConfig, keySet, this);

		Scene testScene = new Scene();
		game.setScene(testScene);

		Layer testLayer = new Layer();
		testScene.addLayer(testLayer);

		game.start(() -> {
			testLayer.draw(0, 0, new RGBA(0, 100, 0), new RGBA(0, 255, 0), new Glyph(7));
		});
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void render() {
	}
}
