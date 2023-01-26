package com.wyldersong.saltengine.game;

import com.wyldersong.saltengine.graphics.WindowConfig;
import com.wyldersong.saltengine.input.KeySet;

public class GameConfig {
	public WindowConfig window;
	public KeySet keys;

	public GameConfig(WindowConfig windowConfig, KeySet keySet) {
		this.window = windowConfig;
		this.keys = keySet;
	}
}
