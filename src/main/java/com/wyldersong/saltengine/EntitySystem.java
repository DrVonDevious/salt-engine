package com.wyldersong.saltengine;

import com.wyldersong.saltengine.graphics.Layer;

import java.util.ArrayList;
import java.util.List;

public class EntitySystem {
	public List<Layer> layers = new ArrayList<>();
	public Entity entity;
	public List<Class<? extends Component>> componentClasses;

	private List<Entity> entities = new ArrayList<>();

	public void getComponents(Class<? extends Component> ...componentClasses) {
//		for (Class<? extends Component> componentClass : componentClasses) {
//			for (Layer layer : layers) {
//				for (Entity entity : layer.entities) {
//					if (entity.hasComponent(componentClass)) {
//						System.out.println("found entity!");
//						entities.add(entity);
//					}
//				}
//			}
//		}
		this.componentClasses = new ArrayList<>(List.of(componentClasses));
	}

	public void update() {}

	public void addedToScene() {}

	public void processEntities(List<Layer> layers) {
		for (Layer layer : layers) {
			for (Entity entity : layer.entities) {
				this.entity = entity;
				update();
			}
		}
	}
}
