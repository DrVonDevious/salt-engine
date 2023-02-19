package com.wyldersong.saltengine;

import com.wyldersong.saltengine.components.PositionComponent;

import java.util.List;

public class Collision {
	public static boolean checkCollision(int targetX, int targetY, List<Entity> entityCollection, Class<? extends Component> collisionComponent, Class<? extends Component> positionComponent) {
		boolean isColliding = false;

		for (Entity entity : entityCollection) {
			if (!entity.hasComponents(List.of(collisionComponent, positionComponent))) {
				continue;
			}

			PositionComponent entityPosition = (PositionComponent) entity.getComponent(positionComponent);

			isColliding = targetX == entityPosition.x && targetY == entityPosition.y;
		}

		return isColliding;
	}
}
