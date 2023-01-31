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

	public CellComponent(Glyph glyph) {
		this.cell = new Cell(glyph);
	}

	public CellComponent(RGBA bg, RGBA fg, Glyph glyph) {
		this.cell = new Cell(bg, fg, glyph);
	}
}
