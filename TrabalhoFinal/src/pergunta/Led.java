package pergunta;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import engine.graphics.Rectangle;

public class Led extends Rectangle {
	private boolean solved = false;

	public Led(Point pos, int width, int height, String text) {
		super(pos, width, height, text);
	}

	public boolean isSolved() {
		return solved;
	}

	public void setSolved(boolean solved) {
		this.solved = solved;
	}
	
	@Override
	public void draw(Graphics g) {
		if (isSolved()) {
			g.setColor(Color.GREEN);
		} else {
			g.setColor(Color.RED);
		}
		g.fill3DRect(getPos().x, getPos().y, getWidth(), getHeight(), false);
	}
}
