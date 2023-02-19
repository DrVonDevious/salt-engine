package com.wyldersong.saltengine;

import com.wyldersong.saltengine.graphics.Layer;

import java.util.ArrayList;
import java.util.List;

public class EntitySystem {
	public List<Layer> layers = new ArrayList<>();
	public Entity entity;
	public List<Class<? extends Component>> componentClasses;

	public void getComponents(Class<? extends Component> ...componentClasses) {
		this.componentClasses = new ArrayList<>(List.of(componentClasses));
	}

	public void update() {}

	public void addedToScene() {}

	public void processEntities(List<Layer> layers) {
		for (Layer layer : layers) {
			for (Entity entity : layer.entities) {
				if (entity.hasComponents(componentClasses)) {
					this.entity = entity;
					update();
				}
			}
		}
	}

	public List<Entity> getEntities() {
		List<Entity> entities = new ArrayList<>();

		for (Layer layer : layers) {
			entities.addAll(layer.entities);
		}

		return entities;
	}
}
