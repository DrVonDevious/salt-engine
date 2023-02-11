package com.wyldersong.saltengine.examples.examplegame;

import com.wyldersong.saltengine.EntitySystem;
import com.wyldersong.saltengine.components.PositionComponent;
import com.wyldersong.saltengine.components.VelocityComponent;

public class MovementSystem extends EntitySystem {
	@Override
	public void addedToScene() {
		getComponents(PositionComponent.class);
	}

	@Override
	public void update() {
		PositionComponent position = (PositionComponent) entity.getComponent(PositionComponent.class);
		VelocityComponent velocity = (VelocityComponent) entity.getComponent(VelocityComponent.class);

		position.x += velocity.deltaX;
		position.y += velocity.deltaY;

		velocity.deltaX = 0;
		velocity.deltaY = 0;
	}
}
