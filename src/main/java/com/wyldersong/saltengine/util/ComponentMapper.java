package com.wyldersong.saltengine.util;

import com.badlogic.ashley.core.Component;
import com.badlogic.ashley.core.ComponentType;
import com.wyldersong.saltengine.game.Entity;

public final class ComponentMapper<T extends Component> {
	private final ComponentType componentType;

	public static <T extends Component> ComponentMapper<T> getFor (Class<T> componentClass) {
		return new ComponentMapper<T>(componentClass);
	}

	public T get (Entity entity) {
		return entity.getComponent(componentType);
	}

	public boolean has (Entity entity) {
		return entity.hasComponent(componentType);
	}

	private ComponentMapper (Class<T> componentClass) {
		componentType = ComponentType.getFor(componentClass);
	}
}