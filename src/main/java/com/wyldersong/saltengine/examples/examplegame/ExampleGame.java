package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.Component;
import com.wyldersong.saltengine.Entity;
import com.wyldersong.saltengine.components.CellComponent;
import com.wyldersong.saltengine.components.PositionComponent;
import com.wyldersong.saltengine.components.VelocityComponent;
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
		game.setScene(testScene);

		Layer testLayer = new Layer();
		testScene.addLayer(testLayer);

		testScene.attachSystem(new MovementSystem());

		player = new Entity<>(
			new CellComponent(new RGBA(0, 0, 0), new RGBA(255, 255, 255), new Glyph(64)),
			new PositionComponent(4, 4),
			new VelocityComponent()
		);

		testLayer.addEntity(player);

		game.start();
	}

	@Override
	public void update(float deltaTime) {
		VelocityComponent velocity = (VelocityComponent) player.getComponent(VelocityComponent.class);

		if (game.keyHandler.isKeyPressed("up")) {
			velocity.deltaY--;
		} else if (game.keyHandler.isKeyPressed("down")) {
			velocity.deltaY++;
		} else if (game.keyHandler.isKeyPressed("left")) {
			velocity.deltaX--;
		} else if (game.keyHandler.isKeyPressed("right")) {
			velocity.deltaX++;
		}

		if (game.keyHandler.isKeyPressed("exit")) {
			game.exit();
		}
	}

	@Override
	public void render() {
	}
}
