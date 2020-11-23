package pergunta.raciocinio;

import java.awt.Point;

import engine.Window;
import engine.graphics.Anchor;
import engine.graphics.Rectangle;

public class Button extends Rectangle {
	
	public Button(Point pos, Anchor anchor, int width, int height, String text) {
		super(pos, anchor, width, height, text);
	}
	
	public void crossRiver(Window window, Boat boat) {
		int crossingSpeed = 1;
		if (boat.getCurrentSide() == Side.Left) {
			while (boat.getPos().x + boat.getWidth() < 250) {
				boat.move(crossingSpeed, 0);
				window.repaint();
			}			
		} else if (boat.getCurrentSide() == Side.Right) {
			while (boat.getPos().x > 50) {
				boat.move(-crossingSpeed, 0);
				window.repaint();
			}			
		}
		Point b = new Point();
		boat.flipSide();
	}
}
