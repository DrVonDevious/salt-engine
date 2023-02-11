package com.wyldersong.saltengine.components;

import com.wyldersong.saltengine.Component;

public class VelocityComponent extends Component {
	public int deltaX;
	public int deltaY;

	public VelocityComponent() {
		deltaX = 0;
		deltaY = 0;
	}

	public VelocityComponent(int deltaX, int deltaY) {
		this.deltaX = deltaX;
		this.deltaY = deltaY;
	}
}
