package com.wyldersong.saltengine.game.systems;

import com.badlogic.ashley.core.Engine;
import com.badlogic.ashley.core.EntitySystem;
import com.wyldersong.saltengine.game.World;

public abstract class System extends EntitySystem {
	public void addedToEngine(World world) {
		super.addedToEngine(world.getAshleyEngine());
	}

	public void removedFromEngine(World world) {
		super.removedFromEngine(world.getAshleyEngine());
	}

//	@Override
//	public void update(float deltaTime) {
//		super.update(deltaTime);
//	}

	@Override
	public boolean checkProcessing() {
		return super.checkProcessing();
	}

	@Override
	public void setProcessing(boolean processing) {
		super.setProcessing(processing);
	}
}
