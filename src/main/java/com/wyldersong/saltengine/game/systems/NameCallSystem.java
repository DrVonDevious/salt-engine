package com.wyldersong.saltengine.game.systems;

import com.wyldersong.saltengine.game.World;
import com.wyldersong.saltengine.game.components.NameComponent;
import com.wyldersong.saltengine.util.EntityCollection;

public class NameCallSystem extends System {
	private EntityCollection entities;

	public void addedToEngine(World world) {
		entities = world.query(NameComponent.class);
	}

	public void update(float deltaTime) {
		for (Object entity : entities) {

		}
	}
}
