package com.wyldersong.saltengine.input;

import java.util.Arrays;
import java.util.Objects;
import java.util.Optional;

public class KeySet {
	public static class KeyAlias {
		public String alias;
		public int keycode;
		public boolean isPressed;

		public KeyAlias(String alias, int keycode) {
			this.alias = alias;
			this.keycode = keycode;
			this.isPressed = false;
		}
	}

	public KeyAlias[] keys;

	public KeySet() {
		keys = new KeyAlias[] {
				new KeyAlias("exit", 256),
				new KeyAlias("up", 87),
				new KeyAlias("down", 83),
				new KeyAlias("left", 65),
				new KeyAlias("right", 68),
		};
	}

	public KeyAlias getKey(int keycode) {
		Optional<KeyAlias> foundAlias = Arrays.stream(keys).filter(key -> key.keycode == keycode).findFirst();

		if (foundAlias.isEmpty()) {
			return null;
		}

		return foundAlias.get();
	}

	public KeyAlias getKey(String name) {
		Optional<KeyAlias> foundAlias = Arrays.stream(keys).filter(key -> Objects.equals(key.alias, name)).findFirst();

		if (foundAlias.isEmpty()) {
			throw new IllegalArgumentException(String.format("No Key Alias found with name: %s", name));
		}

		return foundAlias.get();
	}
}
