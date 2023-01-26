package com.wyldersong.saltengine.input;

import java.util.Arrays;
import java.util.Optional;

public class KeySet {
	public class KeyAlias {
		public String alias;
		public int keycode;

		public KeyAlias(String alias, int keycode) {
			this.alias = alias;
			this.keycode = keycode;
		}
	}

	public KeyAlias[] keys;

	public KeySet() {
		keys = new KeyAlias[] {
				new KeyAlias("exit", 256),
		};
	}

	public int getKey(String name) {
		Optional<KeyAlias> foundAlias = Arrays.stream(keys).filter(key -> key.alias == name).findFirst();

		if (foundAlias.isEmpty()) {
			throw new IllegalArgumentException(String.format("No Key Alias found with name: %s", name));
		}

		return foundAlias.get().keycode;
	}
}
