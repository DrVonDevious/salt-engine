package com.wyldersong.saltengine.util;

import com.badlogic.ashley.utils.ImmutableArray;
import com.badlogic.gdx.utils.Array;
import com.wyldersong.saltengine.game.Entity;

public class EntityCollection extends ImmutableArray {
	public EntityCollection(Array<Entity> array) {
		super(array);
	}
}
