package com.wyldersong.saltengine.game;

import com.badlogic.ashley.core.Engine;
import com.wyldersong.saltengine.game.components.Component;
import com.wyldersong.saltengine.game.systems.System;

public class World <T> {
	private Engine ashleyEngine;

	public World() {
		ashleyEngine = new Engine();
	}

	public Entity createEntity(Component... components) {
		Entity entity = new Entity();

		for (Component component : components) {
			entity.addComponent(component);
		}

		return entity;
	}

	public void addSystem(System system) {
		ashleyEngine.addSystem(system);
	}

	public void query(Class<T>[] componentClasses) {
		for (Class<T> componentClass : componentClasses) {
			java.lang.System.out.println(componentClass.getName());
		}
	}

	public Engine getAshleyEngine() {
		return ashleyEngine;
	}
}
