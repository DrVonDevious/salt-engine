package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.Component;
import com.wyldersong.saltengine.Entity;
import com.wyldersong.saltengine.components.CellComponent;
import com.wyldersong.saltengine.components.PositionComponent;
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

		Entity<Component> playerEntity = new Entity<>(
			new CellComponent(new RGBA(0, 0, 0), new RGBA(255, 255, 255), new Glyph(64)),
			new PositionComponent(4, 4)
		);
		testLayer.addEntity(playerEntity);

		game.start();
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void render() {
	}
}
