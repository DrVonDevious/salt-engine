package com.wyldersong.saltengine.util;

import org.joml.Vector4f;

public class RGBA {
	public int r = 0;
	public int g = 0;
	public int b = 0;
	public int a = 255;

	public RGBA(int r, int g, int b) {
		this.r = r;
		this.g = g;
		this.b = b;

		validateColorRanges();
	}

	public RGBA(int r, int g, int b, int a) {
		this.r = r;
		this.g = g;
		this.b = b;
		this.a = a;

		validateColorRanges();
	}

	private void validateColorRanges() {
		int[] values = new int[] {r, g, b, a};
		for (int value : values) {
			if (value > 255 || value < 0) {
				throw new IllegalArgumentException("RGBA values must be between 0 and 255");
			}
		}
	}

	public Vector4f getVector4f() {
		return new Vector4f(r, g, b, a);
	}
}
