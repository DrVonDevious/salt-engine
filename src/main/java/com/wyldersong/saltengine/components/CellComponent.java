package com.wyldersong.saltengine.components;

import com.wyldersong.saltengine.Component;
import com.wyldersong.saltengine.graphics.Cell;
import com.wyldersong.saltengine.graphics.Glyph;
import com.wyldersong.saltengine.util.RGBA;

public class CellComponent extends Component {
	public Cell cell;

	public CellComponent() {
		this.cell = new Cell();
	}

	public CellComponent(int x, int y) {
		this.cell = new Cell(x, y);
	}

	public CellComponent(int x, int y, Glyph glyph) {
		this.cell = new Cell(x, y, glyph);
	}

	public CellComponent(int x, int y, RGBA fg, RGBA bg, Glyph glyph) {
		this.cell = new Cell(x, y, fg, bg, glyph);
	}
}
