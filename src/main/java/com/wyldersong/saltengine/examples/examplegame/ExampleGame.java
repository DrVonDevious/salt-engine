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
	Game game;
	Entity<Component> player;

	public void run() throws InterruptedException {
		KeySet keySet = new KeySet();
		WindowConfig windowConfig = new WindowConfig();

		game = new Game(windowConfig, keySet, this);

		Scene testScene = new Scene();
		testScene.attachSystem(new MovementSystem());
		game.setScene(testScene);

		Layer testLayer = new Layer();
		testScene.addLayer(testLayer);

		player = new Entity<>(
			new CellComponent(new RGBA(0, 0, 0), new RGBA(255, 255, 255), new Glyph(64)),
			new PositionComponent(4, 4)
		);
		testLayer.addEntity(player);

		game.start();
	}

	@Override
	public void update(float deltaTime) {
		PositionComponent position = (PositionComponent) player.getComponent(PositionComponent.class);

		if (game.keyHandler.isKeyPressed("up")) {
			position.y--;
		} else if (game.keyHandler.isKeyPressed("down")) {
			position.y++;
		} else if (game.keyHandler.isKeyPressed("left")) {
			position.x--;
		} else if (game.keyHandler.isKeyPressed("right")) {
			position.x++;
		}

		if (game.keyHandler.isKeyPressed("exit")) {
			game.exit();
		}
	}

	@Override
	public void render() {
	}
}
