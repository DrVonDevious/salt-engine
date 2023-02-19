package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.*;
import com.wyldersong.saltengine.components.*;
import com.wyldersong.saltengine.game.*;
import com.wyldersong.saltengine.graphics.*;
import com.wyldersong.saltengine.input.*;
import com.wyldersong.saltengine.util.*;

public class ExampleGame implements IGame {
	Game game;
	Entity<Component> player;

	public void run() throws InterruptedException {
		// Create a default Key set, so we do not have to use key codes
		KeySet keySet = new KeySet();

		// Creates a window with the title "Example Game" and with the default speed and size
		WindowConfig windowConfig = new WindowConfig("Example Game");
		windowConfig.targetUPS = 10;

		// Creates our Game object with the configuration that we set up above
		game = new Game(windowConfig, keySet, this);

		// Creates a scene where the game will take place, and then sets that scene to be the current active one
		Scene mainScene = new Scene();
		game.setScene(mainScene);

		// Creates a layer with a size of 20x20 cells to place our entities in and adding that layer to our main scene
		Layer gameLayer = new Layer(20, 20);
		mainScene.addLayer(gameLayer);

		// Adds our movement system to the scene, so we can process entity movement each update
		mainScene.attachSystem(new MovementSystem());

		// Creating the Player entity with some components, so we can see it and move around the screen
		player = new Entity<>(
			new CellComponent(new RGBA(0, 0, 0), new RGBA(255, 255, 255), new Glyph(64)),
			new PositionComponent(4, 4),
			new VelocityComponent()
		);

		// Creating a tree entity with a collision component so our player cannot move through it
		Entity tree = new Entity<>(
			new CellComponent(new RGBA(0, 0, 0), new RGBA(0, 255, 0), new Glyph(116)),
			new PositionComponent(9, 6),
			new CollisionComponent()
		);

		// Adds the entities to our layer
		gameLayer.addEntity(tree);
		gameLayer.addEntity(player);

		// Begin the game loop
		game.start();
	}

	@Override
	public void update(float deltaTime) {
		// Get the velocity component of the player entity, so we can modify it
		VelocityComponent velocity = (VelocityComponent) player.getComponent(VelocityComponent.class);

		// Add velocity to the Player character when any of the default directional keys are pressed
		if (game.keyHandler.isKeyPressed("up")) {
			velocity.deltaY--;
		} else if (game.keyHandler.isKeyPressed("down")) {
			velocity.deltaY++;
		} else if (game.keyHandler.isKeyPressed("left")) {
			velocity.deltaX--;
		} else if (game.keyHandler.isKeyPressed("right")) {
			velocity.deltaX++;
		}

		// If the Player presses the escape key we want to quit the game
		if (game.keyHandler.isKeyPressed("escape")) {
			game.exit();
		}
	}

	@Override
	public void render() {
		// You can add code that you want to be executed during the render phase here
	}
}
