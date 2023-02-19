package com.wyldersong.saltengine;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public class Entity <T extends Component> {
	public UUID id;

	private void nextId() {
		id = UUID.randomUUID();
	}

	private List<T> components;

	public Entity() {
		components = new ArrayList<>();
		nextId();
	}

	public Entity(T... components) {
		this.components = List.of(components);
		nextId();
	}

	public void removeComponent(Class<? extends Component> componentClass) {
		components.removeIf(component -> component.getClass() == componentClass);
	}

	public boolean hasComponent(Class<? extends Component> componentClass) {
		Optional<T> foundComponent = components.stream()
				.filter(component -> component.getClass() == componentClass)
				.findFirst();

		return foundComponent.isPresent();
	}

	public boolean hasComponents(List<Class<? extends Component>> componentClasses) {
		for (Class<? extends Component> componentClass : componentClasses) {
			if (!hasComponent(componentClass)) {
				return false;
			}
		}

		return true;
	}

	public T getComponent(Class<? extends Component> componentClass) {
		Optional<T> foundComponent = components.stream()
				.filter(component -> component.getClass() == componentClass)
				.findFirst();

		if (foundComponent.isEmpty()) {
			throw new RuntimeException("Failed to find component of type " + componentClass.getName() + " in entity");
		}

		return  foundComponent.get();
	}
}
