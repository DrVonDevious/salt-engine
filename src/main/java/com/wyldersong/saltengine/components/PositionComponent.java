package com.wyldersong.saltengine.components;

import com.wyldersong.saltengine.Component;

public class PositionComponent extends Component {
	public int x;
	public int y;

	public PositionComponent() {
		x = 0;
		y = 0;
	}

	public PositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
