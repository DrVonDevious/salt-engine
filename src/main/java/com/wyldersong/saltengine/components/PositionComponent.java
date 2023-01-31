package com.wyldersong.saltengine.components;

import com.wyldersong.saltengine.Component;

public class PositionComponent extends Component {
	public int x = 0;
	public int y = 0;

	public PositionComponent() {}

	public PositionComponent(int x, int y) {
		this.x = x;
		this.y = y;
	}
}
