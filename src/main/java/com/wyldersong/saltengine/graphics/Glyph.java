package com.wyldersong.saltengine.graphics;

public class Glyph {
	public int id;

	public Glyph(int id) {
		this.id = id;
	}

	public Glyph(char character) {
		this.id = 0;
		System.out.println(character);
	}
}
