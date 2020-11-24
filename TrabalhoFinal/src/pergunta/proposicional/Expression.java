package pergunta.proposicional;

import java.awt.Point;

import engine.graphics.Anchor;
import engine.graphics.Rectangle;

public class Expression extends Rectangle {
	public Expression(int y, Anchor anchor, int width, int height, String text) {
		super(new Point(10, y), anchor, width, height, text);
	}
}