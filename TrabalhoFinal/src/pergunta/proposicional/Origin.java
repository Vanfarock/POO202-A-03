package pergunta.proposicional;

import java.awt.Point;

import engine.graphics.Anchor;
import engine.graphics.Rectangle;

public class Origin extends Rectangle {
	public Origin(int y, Anchor anchor, int width, int height, String text) {
		super(new Point(110, y), anchor, width, height, text);
	}
	
	public boolean isMovable() {
		String thisText = this.getText().getText();
		return !thisText.equals("Premissa") && !thisText.equals("Hipótese");
	}
}
