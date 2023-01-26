package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.game.Game;
import com.wyldersong.saltengine.game.IGame;
import com.wyldersong.saltengine.game.World;
import com.wyldersong.saltengine.game.components.NameComponent;
import com.wyldersong.saltengine.game.systems.NameCallSystem;
import com.wyldersong.saltengine.graphics.*;
import com.wyldersong.saltengine.input.KeySet;

public class ExampleGame implements IGame {
	private Scene testScene;
	private int playerX = 0;

	public void run() throws InterruptedException {
		KeySet keySet = new KeySet();
		WindowConfig windowConfig = new WindowConfig();

		Game game = new Game(windowConfig, keySet, this);
		World world = new World();

		NameCallSystem nameCallSystem = new NameCallSystem();
		world.addSystem(nameCallSystem);

		world.createEntity(new NameComponent("Test Entity"));

		testScene = new Scene();
		game.setScene(testScene);

		Layer testLayer = new Layer();
		testScene.addLayer(testLayer);

		game.start(() -> {
			testLayer.draw(playerX, 1, new Glyph(1));
		});
	}

	@Override
	public void update(float deltaTime) {
	}

	@Override
	public void render() {}
}
