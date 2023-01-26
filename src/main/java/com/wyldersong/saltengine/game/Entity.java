package com.wyldersong.saltengine.game;

import com.wyldersong.saltengine.game.components.Component;

public class Entity {
	private com.badlogic.ashley.core.Entity ashleyEntity;

	public Entity() {
		ashleyEntity = new com.badlogic.ashley.core.Entity();
	}

	public void addComponent(Component component) {
		ashleyEntity.add(component);
	}

	public int getComponent(Class<T> componentClass) {
		return (Component) ashleyEntity.getComponent(componentClass);
	}
}
