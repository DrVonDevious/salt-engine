package com.wyldersong.saltengine.graphics;

import com.wyldersong.saltengine.util.RGBA;

public class WindowConfig {
	public String title = "Game";
	public int width = 800;
	public int height = 600;
	public RGBA clearColor = new RGBA(0, 0, 0, 1);
	public boolean isVsyncEnabled = true;
	public int targetFPS = 60;

	public WindowConfig() {}

	public WindowConfig(String title) {
		this.title = title;
	}

	public WindowConfig(String title, int width, int height) {
		this.title = title;
		this.width = width;
		this.height = height;
	}
}
