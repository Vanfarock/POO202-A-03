package pergunta.raciocinio;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.Window;
import engine.graphics.Rectangle;

public class Button extends Rectangle {
	
	public Button(Point pos, int width, int height, String text) {
		super(pos, width, height, text);
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
		boat.flipSide();
	}
	
	@Override
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		super.draw(g);
	}
}
