package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.EntitySystem;

public class MovementSystem extends EntitySystem {
	@Override
	public void update() {
		super.update();

		System.out.println("updated!");
		// TODO: User should pass in component classes and the system should iterate over entities with those components
	}
}
